package com.example.sample.model.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.sample.model.Userlogintoken;

public interface UserLoginTokenRepository extends CrudRepository<Userlogintoken, String> {
	/**
	 * find UserLoginToken by the parameters
	 *
	 * @param token
	 * @return
	 */
	Userlogintoken findUserLoginTokenByToken(String token);

	/**
	 * find UserLoginToken by the parameters
	 *
	 * @param userId
	 * @return
	 */
	Userlogintoken findUserLoginTokenByUserid(String userId);
}
