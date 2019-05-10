package com.example.sample.facade;

import org.springframework.http.ResponseEntity;

import com.example.sample.pojo.Created;
import com.example.sample.pojo.LoginInfo;
import com.example.sample.pojo.TokenInfo;
import com.example.sample.pojo.UserInfo;

/**
 * Groups all user related services definitions
 * 
 * @author user
 *
 */
public interface UserFacade {
	ResponseEntity<Created> createUser(UserInfo filter) throws Exception;
	
	ResponseEntity<TokenInfo> loginUser (LoginInfo filter) throws Exception;
}
