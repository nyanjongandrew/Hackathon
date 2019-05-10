package com.example.sample.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.facade.UserFacade;
import com.example.sample.facade.UtilityFacade;
import com.example.sample.pojo.Created;
import com.example.sample.pojo.LoginInfo;
import com.example.sample.pojo.TokenInfo;
import com.example.sample.pojo.UserInfo;

@RestController
@RequestMapping("/api/user")
public class UserAuthenticationController {

	@Autowired
	private UtilityFacade utilityFacade;
	
	@Autowired
	private UserFacade userFacade;
	
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public ResponseEntity<UserInfo> filter() throws Exception {
		
		return new ResponseEntity<>(new UserInfo(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Created> registerUser(@Valid @RequestBody UserInfo filter,
			BindingResult bindingResult) throws Exception {
		utilityFacade.validateRequest(bindingResult);
		return userFacade.createUser(filter);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<TokenInfo> userLogin(@Valid @RequestBody LoginInfo filter,
			BindingResult bindingResult) throws Exception {
		utilityFacade.validateRequest(bindingResult);
		return userFacade.loginUser(filter);
	}
}
