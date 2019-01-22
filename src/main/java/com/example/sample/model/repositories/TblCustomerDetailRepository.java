package com.example.sample.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.sample.model.TblCustomerDetail;

public interface TblCustomerDetailRepository extends CrudRepository<TblCustomerDetail, Integer> {

	TblCustomerDetail findByPhoneNumber(String phoneNo);
}
