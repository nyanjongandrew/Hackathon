package com.example.sample.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.model.TblMainTransaction;
import com.example.sample.model.repositories.TblMainTransactionRepository;
import com.example.sample.pojo.RequestReceipt;
import com.example.sample.pojo.TransferRequest;
import com.example.sample.service.TblMainTransactionService;
import com.example.sample.service.UtilService;
@Service
public class TblMainTransactionServiceImpl implements TblMainTransactionService<TblMainTransaction> {
	@Autowired
	private TblMainTransactionRepository tblMainTransactionRepository;
	@Autowired
	private UtilService utilService;

	@Override
	public TblMainTransaction prepareTblMainTransactionForInsert(TransferRequest filter, String receiptNo)
			throws Exception {
		TblMainTransaction tblMainTransaction = new TblMainTransaction();
		tblMainTransaction.setAmount(filter.getAmount());
		tblMainTransaction.setReceipt(receiptNo);
		tblMainTransaction.setRequestId(tblMainTransactionRepository.count()+1);
		tblMainTransaction.setDate(utilService.getCurrentTimeStamp());
		tblMainTransaction.setUuid(UUID.randomUUID().toString());
		return tblMainTransaction;
	}

	@Override
	public RequestReceipt prepareRequestReceiptFromMainTransaction(TblMainTransaction t) {
		RequestReceipt requestReceipt = new RequestReceipt();
		requestReceipt.setReceipt(t.getReceipt());
		requestReceipt.setRequest((int)t.getRequestId());
		return requestReceipt;
	}

}
