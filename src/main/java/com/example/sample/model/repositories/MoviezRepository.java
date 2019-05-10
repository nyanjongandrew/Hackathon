package com.example.sample.model.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.sample.model.Moviez;

public interface MoviezRepository extends CrudRepository<Moviez, String>, MoviezRepositoryCustom {
	Moviez findByTitle(String title);

	List<Moviez> findAll();
}
