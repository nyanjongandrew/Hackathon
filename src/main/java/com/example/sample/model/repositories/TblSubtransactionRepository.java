package com.example.sample.model.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.sample.model.TblSubtransaction;

public interface TblSubtransactionRepository extends CrudRepository<TblSubtransaction, UUID> {
  Long countByRequestId(int reqId);
  
  List<TblSubtransaction> findByReceipt(String receiptNo);
}
