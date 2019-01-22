package com.example.sample.service;

import com.example.sample.pojo.RequestReceipt;
import com.example.sample.pojo.TransferRequest;

public interface TblSubtransactionService<T> {
  T prepareSubtransactionForInsert(TransferRequest filter,RequestReceipt requestReceipt);
}
