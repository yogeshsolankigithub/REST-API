package com.yogesh.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.yogesh.app.ws.shared.dto.UserDto;

import java.util.List;

public interface UserService extends UserDetailsService{
	
	public UserDto createUser(UserDto userDto);
	public UserDto getUser(String email);

    public UserDto getUserByID(String id);
    public UserDto updateUser(UserDto userDto,String userId);

	List<UserDto> getUsers(int page, int limit);
}
