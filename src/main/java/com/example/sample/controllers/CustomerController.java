package com.example.sample.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.config.security.PinEncoder;
import com.example.sample.facade.TransactionsFacade;
import com.example.sample.facade.UtilityFacade;
import com.example.sample.model.TblMainTransaction;
import com.example.sample.pojo.SubTrans;
import com.example.sample.pojo.TransactionHistoryFilter;
import com.example.sample.pojo.TransferRequest;
import com.example.sample.pojo.TransferResponse;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private UtilityFacade utilityFacade;
	@Autowired
	private TransactionsFacade transFacade;
	//@Autowired
	//private PinEncoder pinEncoder;

	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ResponseEntity<TransferResponse> c2ctransfer(@Valid @RequestBody TransferRequest filter,
			BindingResult bindingResult) throws Exception {
		utilityFacade.validateRequest(bindingResult);
		return transFacade.transferFundsBetweenUsers(filter);
	}

	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public ResponseEntity<TransferRequest> filter() throws Exception {
		// System.out.println(pinEncoder.encode("1234"));
		return new ResponseEntity<>(new TransferRequest(), HttpStatus.OK);
	}

	@RequestMapping(value = "/subtransactions/{receiptNo}", method = RequestMethod.GET)
	public ResponseEntity<List<SubTrans>> getSubtransactions(@PathVariable(name = "receiptNo") String receiptNo)
			throws Exception {

		return transFacade.subtransactions(receiptNo);
	}

	@RequestMapping(value = "/transaction/history", method = RequestMethod.POST)
	public ResponseEntity<List<TblMainTransaction>> transactionHistory(
			@Valid @RequestBody TransactionHistoryFilter filter, BindingResult bindingResult) throws Exception {
		utilityFacade.validateRequest(bindingResult);
		return transFacade.transactionHistory(filter);
	}

	@RequestMapping(value = "/histfilter", method = RequestMethod.GET)
	public ResponseEntity<TransactionHistoryFilter> histfilter() throws Exception {
		TransactionHistoryFilter fil = new TransactionHistoryFilter();
		fil.setEnd_date(new Date());
		fil.setStart_date(new Date());
		fil.setPhone("0789846474");
		return new ResponseEntity<>(fil, HttpStatus.OK);
	}

}
