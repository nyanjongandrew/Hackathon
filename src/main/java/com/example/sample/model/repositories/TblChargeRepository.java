package com.example.sample.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.sample.model.TblCharge;

public interface TblChargeRepository extends CrudRepository<TblCharge, Integer> {
	List<TblCharge> findByMaxLessThanEqual(int amount);
	
	@Query(nativeQuery = true, value = "SELECT * FROM tbl_charges WHERE ? BETWEEN min AND max")
	TblCharge findChargePerCohort(@Param("amount") Integer amount);
}
