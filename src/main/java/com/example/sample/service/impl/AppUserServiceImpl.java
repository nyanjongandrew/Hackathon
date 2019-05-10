package com.example.sample.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sample.model.AppUser;
import com.example.sample.pojo.UserInfo;
import com.example.sample.service.AppUserService;
/**
 * Handles all user related dao
 * @author user
 *
 */
@Service
public class AppUserServiceImpl implements AppUserService<AppUser> {
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public AppUser prepareAppUserForInsert(UserInfo filter) throws Exception {
		AppUser user = new AppUser();
		user.setFirstname(filter.getFirstname());
		user.setLastname(filter.getLastname());
		user.setPassword(encoder.encode(filter.getPassword()));
		user.setUsername(filter.getUsername());
		user.setId(UUID.randomUUID().toString());
		return user;
	}

}
