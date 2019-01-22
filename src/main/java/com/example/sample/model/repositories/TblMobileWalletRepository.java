package com.example.sample.model.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.sample.model.TblMobileWallet;

public interface TblMobileWalletRepository extends CrudRepository<TblMobileWallet, String> {
	TblMobileWallet findByPhoneNumber(String phoneNo);
}
