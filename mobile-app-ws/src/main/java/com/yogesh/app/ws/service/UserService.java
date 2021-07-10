package com.yogesh.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.yogesh.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	public UserDto createUser(UserDto userDto);

}
