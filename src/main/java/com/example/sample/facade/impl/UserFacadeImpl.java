package com.example.sample.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import com.example.sample.exception.DataIntegrityException;
import com.example.sample.facade.UserFacade;
import com.example.sample.model.AppUser;
import com.example.sample.model.repositories.AppUserRepository;
import com.example.sample.pojo.Created;
import com.example.sample.pojo.LoginInfo;
import com.example.sample.pojo.TokenInfo;
import com.example.sample.pojo.UserInfo;
import com.example.sample.service.AppUserService;
import com.example.sample.util.annotation.Facade;
/**
 *  Implementation of user related services
 *  
 * @author user
 *
 */
@Facade
public class UserFacadeImpl implements UserFacade{
 @Autowired
 private AppUserService<AppUser> userService;
 @Autowired
 private AppUserRepository appUserRepository;
 @Value("${user.exists}")
 private String userExists;
 //autowire password encoder
	@Override
	public ResponseEntity<Created> createUser(UserInfo filter) throws Exception {
		//check if the username exists
		AppUser exists = appUserRepository.findByUsername(filter.getUsername());
		if(exists!=null){
			throw new DataIntegrityException(userExists);
		}
		AppUser user = userService.prepareAppUserForInsert(filter);
		appUserRepository.save(user);
		Created created = new Created();
		created.setCreated(true);
		return ResponseEntity.ok(created);
	}

	@Override
	public ResponseEntity<TokenInfo> loginUser(LoginInfo filter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
