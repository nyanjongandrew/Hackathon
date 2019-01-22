package com.example.sample.model.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.sample.model.TblBusinessAccount;

public interface TblBusinessAccountRepository extends CrudRepository<TblBusinessAccount, String> {
	TblBusinessAccount findByAccountNo(int accountNo);
}
