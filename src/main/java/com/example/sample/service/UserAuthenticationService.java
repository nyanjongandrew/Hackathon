package com.example.sample.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.sample.config.security.User;
import com.example.sample.model.AppUser;
import com.example.sample.pojo.LoginInfo;


/**
 * This interface is used for all user authentication services - these functions
 * will be shared by all the clients of this product
 *
 * @author smaina
 *
 */
public interface UserAuthenticationService {

	/**
	 * This function is used to change user's passwords
	 *
	 * @param userEmail
	 * @param newPassword
	 * @return
	 */
	Boolean changePassword(final String userEmail, final String newPassword);

	/**
	 * checks if the password given by the user corresponds to the pasword in
	 * the database
	 *
	 * @param databasePassword
	 * @param userProvicedPassword
	 * @return
	 * @throws Exception
	 */
	public boolean isCorrectPassword(String databasePassword, String userProvicedPassword) throws Exception;

	/**
	 * used to load User by username
	 *
	 * @param username
	 *            the username to get user;
	 * @return
	 * @throws UsernameNotFoundException
	 */
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	/**
	 * refresh the user Token
	 *
	 * @param token
	 * @param refreshedToken
	 * @param refreshTime
	 */
	void refreshUserLoginToken(String token, String refreshedToken, String refreshTime);

	/**
	 * Used to check if the credetials given are valid
	 *
	 * @param loginPOJO
	 * @return
	 * @throws Exception
	 */
	User isValidUser(LoginInfo loginPOJO) throws Exception;

	/**
	 * Used to generate password reset tokens
	 *
	 * @param selfServiceUser
	 * @return
	 */
	//PasswordResetToken generateToken(AppUser selfServiceUser);

	/**
	 * creates UserLoginToken
	 *
	 * @param user
	 * @param token
	 * @param refreshTime
	 * @param createdTime
	 */
	void createUserLoginToken(AppUser user, String token, String refreshTime, String createdTime);

}
