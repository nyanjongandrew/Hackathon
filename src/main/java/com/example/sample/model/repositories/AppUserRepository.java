package com.example.sample.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.sample.model.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, String> {
  AppUser findByUsername(String username);
}
