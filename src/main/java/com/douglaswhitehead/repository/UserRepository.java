package com.douglaswhitehead.repository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository {

	public UserDetails loadUserByUsername(String username);
	
}