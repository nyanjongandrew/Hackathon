package com.example.sample.facade;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.sample.model.TblMainTransaction;
import com.example.sample.pojo.SubTrans;
import com.example.sample.pojo.TransactionHistoryFilter;
import com.example.sample.pojo.TransferRequest;
import com.example.sample.pojo.TransferResponse;



public interface TransactionsFacade {
	ResponseEntity<TransferResponse> transferFundsBetweenUsers(TransferRequest filter) throws Exception;
	ResponseEntity<List<SubTrans>> subtransactions(String receiptNo) throws Exception;
	ResponseEntity<List<TblMainTransaction>> transactionHistory(TransactionHistoryFilter filter) throws Exception;
}
