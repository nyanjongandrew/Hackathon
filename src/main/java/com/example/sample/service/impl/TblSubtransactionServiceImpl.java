package com.example.sample.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.sample.model.TblSubtransaction;
import com.example.sample.pojo.RequestReceipt;
import com.example.sample.pojo.TransferRequest;
import com.example.sample.service.TblSubtransactionService;
@Service
public class TblSubtransactionServiceImpl implements TblSubtransactionService<TblSubtransaction> {

	@Override
	public TblSubtransaction prepareSubtransactionForInsert(TransferRequest filter,RequestReceipt requestReceipt) {
		TblSubtransaction tblSubtransaction = new TblSubtransaction();
		tblSubtransaction.setAmount(filter.getAmount());
		tblSubtransaction.setPhone(filter.getPhoneno());
		tblSubtransaction.setReceipt(requestReceipt.getReceipt());
		tblSubtransaction.setRequestId(requestReceipt.getRequest());
		//tblSubtransaction.setTransactionType(1);
		tblSubtransaction.setUuid(UUID.randomUUID().toString());
		return tblSubtransaction;
	}

}
