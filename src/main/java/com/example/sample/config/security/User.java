package com.example.sample.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.example.sample.model.AppUser;


/**
 * Class that represents an User of the system.
 *
 * @author andrew
 *
 */
public class User extends GenericUser {
	private static final long serialVersionUID = 1L;
	private final AppUser selfServiceUser;

	public User(AppUser selfServiceUser, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.selfServiceUser = selfServiceUser;
	}

	public User(AppUser selfServiceUser, String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.selfServiceUser = selfServiceUser;
	}

	public AppUser getSelfServiceUser() {
		return selfServiceUser;
	}

}
