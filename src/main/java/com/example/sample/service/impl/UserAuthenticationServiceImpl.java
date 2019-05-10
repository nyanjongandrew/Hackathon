package com.example.sample.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sample.config.security.User;
import com.example.sample.exception.ExpectationFailedException;
import com.example.sample.exception.ObjectNotFoundException;
import com.example.sample.model.AppUser;
import com.example.sample.model.Userlogintoken;
import com.example.sample.model.repositories.AppUserRepository;
import com.example.sample.model.repositories.UserLoginTokenRepository;
import com.example.sample.pojo.LoginInfo;
import com.example.sample.service.UserAuthenticationService;
import com.example.sample.util.Validators;



/**
 * This class will be used for all user authentication services
 *
 * @author andrew
 *
 */
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
	@Value("${login.error}")
	private String LoginError;
	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);
	@Autowired
	private AppUserRepository selfServiceUserRepository;
	@Autowired
	private PasswordEncoder encoder;
//	@Autowired
//	private PasswordResetTokenRepository tokenRepository;

	@Autowired
	private Environment env;
	private static final Integer MILISECONDS_PER_SECOND = 1000;

	private static final Integer SECONDS_PER_MINUTE = 60;

	@Autowired
	private UserLoginTokenRepository userLoginTokenRepository;
	@Value("${web.portal.token.expiration}")
	private String tokenExpiryTime;
	@Value("${user.notexist.error}")
	private String userNotExistError;

	@Override
	public User isValidUser(LoginInfo loginPOJO) throws Exception {
		final User user = (User) loadUserByUsername(loginPOJO.getUsername());
		final AppUser selfServiceUser = user.getSelfServiceUser();
		if (selfServiceUser == null) {
			// throw new PreconditionFailedException(userNotExistError);
			throw new ObjectNotFoundException(env.getProperty("login.error"));
		}
		final String password = selfServiceUser.getPassword();
		if (isCorrectPassword(password, loginPOJO.getPassword())) {
			return user;
		}
		throw new ExpectationFailedException(LoginError);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		final List<GrantedAuthority> authorities = new ArrayList<>();
		try {
			userDetails = loadClient(email, authorities);
			if (userDetails == null) {
				throw new UsernameNotFoundException("User " + email + " not found");
			}
		} catch (final Exception e) {
			//throw new CustomUserException(env.getProperty("login.error"));
			throw new RuntimeException(e);
		}
		return userDetails;
	}

	@Override
	public boolean isCorrectPassword(String databasePassword, String userProvicedPassword) throws Exception {
		return encoder.matches(userProvicedPassword, databasePassword);
	}

	/**
	 * Loads a client from the database
	 *
	 * @param username
	 * @param authorities
	 * @return
	 * @throws Exception
	 */
	private UserDetails loadClient(final String email, final List<GrantedAuthority> authorities) throws Exception {
		logger.debug("Load Client");
		User userDetails = null;

		if (Validators.isValidEmail(email)) {
			final AppUser selfServiceUser = selfServiceUserRepository.findByUsername(email);
			if (selfServiceUser != null) {
				userDetails = new User(selfServiceUser, selfServiceUser.getUsername(), selfServiceUser.getPassword(),
						authorities);
			}
		}

		return userDetails;
	}

	/*@Override
	public PasswordResetToken generateToken(final SelfServiceUser selfServiceUser) {
		// CREATE NEW TOKEN FOR THE USER
		if (selfServiceUser != null) {
			final Long expireTimeInMinutes = new Long(tokenExpiryTime);
			deleteExistingUsernameTokens(selfServiceUser);
			final PasswordResetToken token = new PasswordResetToken();
			token.setToken(encoder.encode(selfServiceUser.getPassword()));
			final Date expireDate = new Date();
			expireDate
			.setTime(expireDate.getTime() + expireTimeInMinutes * SECONDS_PER_MINUTE * MILISECONDS_PER_SECOND);
			token.setExpireDate(String.valueOf(expireDate.getTime()));
			token.setCustomerId(selfServiceUser.getCustomerId());
			token.setTokenId(String.valueOf(System.currentTimeMillis()));
			tokenRepository.save(token);
			return token;
		}
		return null;
	} */

	/**
	 * This function is used to delete already existig tokens when a new one is
	 * generated
	 *
	 * @param selfServiceUser
	 */
	/*private void deleteExistingUsernameTokens(final SelfServiceUser selfServiceUser) {
		// DELETE PREVIOUS EXISTING TOKENS FOR THE USER
		final List<PasswordResetToken> tokens = tokenRepository
				.findPasswordResetTokenByCustomerId(selfServiceUser.getCustomerId());
		if (tokens != null && tokens.size() > 0) {
			tokenRepository.deleteAll(tokens);
		} 
	}*/

	@Override
	public Boolean changePassword(String userEmail, String newPassword) {
		final AppUser selfServiceUser = selfServiceUserRepository.findByUsername(userEmail);
		if (selfServiceUser != null) {
			// delete reset token
			//deleteExistingUsernameTokens(selfServiceUser);
			// change password
			selfServiceUser.setPassword(encoder.encode(newPassword));
			selfServiceUserRepository.save(selfServiceUser);
			return true;
		}
		return false;
	}

	@Override
	public void createUserLoginToken(AppUser user, String token, String refreshTime, String createdTime) {
		Userlogintoken userLoginToken = userLoginTokenRepository.findUserLoginTokenByUserid(user.getId());
		if (userLoginToken == null) {
			userLoginToken = new Userlogintoken();
			userLoginToken.setTokenId(createdTime);
			//userLoginToken.setUserId(user.getCustomerId());
		}
		userLoginToken.setToken(token);
		userLoginToken.setRefreshtime(refreshTime);
		userLoginTokenRepository.save(userLoginToken);
	}

	@Override
	public void refreshUserLoginToken(String token, String refreshedToken, String refreshTime) {
		final Userlogintoken userLoginToken = userLoginTokenRepository.findUserLoginTokenByToken(token);
		userLoginToken.setTokenId(String.valueOf(System.currentTimeMillis()));
		userLoginToken.setToken(refreshedToken);
		userLoginToken.setRefreshtime(refreshTime);
		userLoginTokenRepository.save(userLoginToken);
	}
}
