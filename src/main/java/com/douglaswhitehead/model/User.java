package com.douglaswhitehead.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User extends org.springframework.security.core.userdetails.User implements Serializable, UserDetails {

	private static final long serialVersionUID = 3514441411872786674L;
	
	private UUID id;

	public User(UUID id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id = id;
	}
	
	public User(UUID id, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", getAuthorities()=");
		builder.append(getAuthorities());
		builder.append("Password: [PROTECTED] ");
		builder.append(", getUsername()=");
		builder.append(getUsername());
		builder.append(", isEnabled()=");
		builder.append(isEnabled());
		builder.append(", isAccountNonExpired()=");
		builder.append(isAccountNonExpired());
		builder.append(", isAccountNonLocked()=");
		builder.append(isAccountNonLocked());
		builder.append(", isCredentialsNonExpired()=");
		builder.append(isCredentialsNonExpired());
		builder.append("]");
		return builder.toString();
	}	

}