package com.example.sample.service;

import com.example.sample.pojo.RequestReceipt;
import com.example.sample.pojo.TransferRequest;

public interface TblMainTransactionService<T> {
  T prepareTblMainTransactionForInsert(TransferRequest filter,String receiptNo) throws Exception;
  
  RequestReceipt prepareRequestReceiptFromMainTransaction(T t);
}
