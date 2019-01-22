package com.example.sample.facade.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.example.sample.exception.MaxAllowableTransferLimitException;
import com.example.sample.exception.PreconditionFailedException;
import com.example.sample.exception.WrongPinException;
import com.example.sample.facade.TransactionsFacade;
import com.example.sample.model.TblBusinessAccount;
import com.example.sample.model.TblCharge;
import com.example.sample.model.TblCustomerDetail;
import com.example.sample.model.TblMainTransaction;
import com.example.sample.model.TblMobileWallet;
import com.example.sample.model.TblSubtransaction;
import com.example.sample.model.repositories.TblBusinessAccountRepository;
import com.example.sample.model.repositories.TblChargeRepository;
import com.example.sample.model.repositories.TblCustomerDetailRepository;
import com.example.sample.model.repositories.TblMainTransactionRepository;
import com.example.sample.model.repositories.TblMobileWalletRepository;
import com.example.sample.model.repositories.TblSubtransactionRepository;
import com.example.sample.pojo.RequestReceipt;
import com.example.sample.pojo.SubTrans;
import com.example.sample.pojo.TransactionHistoryFilter;
import com.example.sample.pojo.TransferRequest;
import com.example.sample.pojo.TransferResponse;
import com.example.sample.service.TblMainTransactionService;
import com.example.sample.service.TblSubtransactionService;
import com.example.sample.util.Validators;
import com.example.sample.util.annotation.Facade;

/**
 * 
 * @author user Handles all the transactions operations
 */
@Facade
public class TransactionsFacadeImpl implements TransactionsFacade {
	@Autowired
	private Environment env;
	@Autowired
	private TblChargeRepository tblChargeRepository;
	@Autowired
	private TblCustomerDetailRepository tblCustomerDetailRepository;
	@Autowired
	private TblMobileWalletRepository tblMobileWalletRepository;
	@Value("${max.amount.limit}")
	private String allowableAmountError;
	@Autowired
	private TblMainTransactionService<TblMainTransaction> tblMainTransactionService;
	@Autowired
	private TblMainTransactionRepository tblMainTransactionRepository;
	@Autowired
	private TblBusinessAccountRepository tblBusinessAccountRepository;
	@Autowired
	private TblSubtransactionService<TblSubtransaction> tblSubTransactionService;
	@Autowired
	private TblSubtransactionRepository tblSubtransactionRepository;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public ResponseEntity<TransferResponse> transferFundsBetweenUsers(TransferRequest filter) throws Exception {
		// Check the amount from the requestor + charge
		// if not enough through insufficient funds exception

		Integer chargeAmount = 0;
		if (!Validators.isValidPhoneNumber(filter.getPhoneno())) {
			throw new PreconditionFailedException(env.getProperty("invalid.phone"));
		}
		if (filter.getAmount() > 70000) {
			throw new MaxAllowableTransferLimitException(allowableAmountError);
		}
		if (filter.getAmount() < 0) {
			throw new PreconditionFailedException("Amount cannot be 0 and below");
		}
		// System.out.println(UUID.randomUUID().toString());
		TblMobileWallet sender = tblMobileWalletRepository.findByPhoneNumber(filter.getSenderPhoneNo());
		if (sender == null) {
			throw new PreconditionFailedException(env.getProperty("sender.not.found"));
		}
		// validate pin
		String userPin = Base64.getEncoder().encodeToString(filter.getPin().getBytes());
		if (!userPin.equals(sender.getPin())) {
			throw new WrongPinException(env.getProperty("incorrect.pin"));
		}

		if (filter.getAmount() == null && filter.getAmount() != 0) {
			throw new PreconditionFailedException("Invalid amount specified");
		}
		String receiptNo = generateReceipt();

		TblMainTransaction tbl = tblMainTransactionService.prepareTblMainTransactionForInsert(filter, receiptNo);
		TblCharge charge = tblChargeRepository.findChargePerCohort(filter.getAmount());
		// TblCharge charge =
		// tblChargeRepository.findByMaxLessThanEqual(filter.getAmount()).get(0);
		// check the user the money is being transfered to if registered
		TblCustomerDetail receiver = tblCustomerDetailRepository.findByPhoneNumber(filter.getPhoneno());
		if (receiver != null && filter.getAmount() > 30000) {
			throw new PreconditionFailedException(env.getProperty("unregisterd.max.limit"));
		}
		chargeAmount = receiver != null ? charge.getSendtoregistered() : charge.getSendtounregistred();

		TblBusinessAccount businessAccount = tblBusinessAccountRepository.findByAccountNo(12345);
		TblMainTransaction savedRow = tblMainTransactionRepository.save(tbl);
		// debit first from cust
		int totalAmount = Integer.sum(filter.getAmount(), chargeAmount);
		int currentBalance = sender.getAccountBal();
		sender.setAccountBal(currentBalance - totalAmount);
		sender.setLastActivity(env.getProperty("debit.msg"));
		tblMobileWalletRepository.save(sender);
		TblSubtransaction debit = new TblSubtransaction();
		debit.setAmount(totalAmount);
		debit.setPhone(sender.getPhoneNumber());
		debit.setTransactionType(env.getProperty("debit.msg"));
		debit.setUuid(UUID.randomUUID().toString());
		debit.setRequestId((int) savedRow.getRequestId());
		debit.setReceipt(receiptNo);
		tblSubtransactionRepository.save(debit);
		// credit collections
		int currentBizBalance = businessAccount.getAccountBalance();
		businessAccount.setAccountBalance(Integer.sum(currentBizBalance, chargeAmount));
		businessAccount.setLastActivity("Credit transaction charges!!!!!");
		businessAccount.setAccountBalance(
				chargeAmount != null && chargeAmount != 0 ? chargeAmount : businessAccount.getAccountBalance());
		tblBusinessAccountRepository.save(businessAccount);
		TblSubtransaction credit = new TblSubtransaction();
		credit.setAmount(chargeAmount);
		credit.setPhone(sender.getPhoneNumber());
		credit.setTransactionType(env.getProperty("charges.msg"));
		credit.setRequestId((int) savedRow.getRequestId());
		credit.setUuid(UUID.randomUUID().toString());
		credit.setReceipt(receiptNo);
		tblSubtransactionRepository.save(credit);

		savedRow.setPartya(sender.getPhoneNumber());
		savedRow.setPartyb(receiver.getPhoneNumber());
		savedRow.setStatus(1);
		savedRow.setCharge(chargeAmount);
		// TblMainTransaction savedRow = tblMainTransactionRepository.save(tbl);
		tblMainTransactionRepository.save(savedRow);
		RequestReceipt rec = tblMainTransactionService.prepareRequestReceiptFromMainTransaction(savedRow);
		TblSubtransaction sub = tblSubTransactionService.prepareSubtransactionForInsert(filter, rec);
		sub.setTransactionType(env.getProperty("credit.msg"));
		tblSubtransactionRepository.save(sub);
		// credit receiver

		TransferResponse res = new TransferResponse();
		res.setReceipt(rec.getReceipt());
		return ResponseEntity.ok(res);
	}

	private String generateReceipt() {

		return String.valueOf(System.nanoTime());
	}

	@Override
	public ResponseEntity<List<SubTrans>> subtransactions(String receiptNo) throws Exception {
		List<TblSubtransaction> subtransactions = tblSubtransactionRepository.findByReceipt(receiptNo);
		List<SubTrans> subTransList = new ArrayList<>();
		subtransactions.stream().filter(Objects::nonNull).forEach(subtransaction -> {
			SubTrans subtran = new SubTrans();
			subtran.setAmount(subtransaction.getAmount());
			subtran.setPhone(subtransaction.getPhone());
			// subtran.setTransType(subtransaction.getTransactionType() == 0 ?
			// "DEBIT" : "CREDIT");
			subTransList.add(subtran);
		});
		return ResponseEntity.ok().body(subTransList);
	}

	@Override
	public ResponseEntity<List<TblMainTransaction>> transactionHistory(TransactionHistoryFilter filter)
			throws Exception {
		List<TblMainTransaction> transHist = tblMainTransactionRepository.findTransactionsRequests(filter);
		return ResponseEntity.ok(transHist);
	}
}
