package com.example.sample.model.repositories;

import java.util.List;

import com.example.sample.model.TblMainTransaction;
import com.example.sample.pojo.TransactionHistoryFilter;

public interface TblMainTransactionRepositoryCustom {
	List<TblMainTransaction> findTransactionsRequests(TransactionHistoryFilter filter) throws Exception;
}
