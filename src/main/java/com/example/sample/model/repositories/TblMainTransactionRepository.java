package com.example.sample.model.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.sample.model.TblMainTransaction;

public interface TblMainTransactionRepository extends CrudRepository<TblMainTransaction, String>,TblMainTransactionRepositoryCustom{

}
