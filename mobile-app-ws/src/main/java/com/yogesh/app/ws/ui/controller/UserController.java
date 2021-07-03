package com.yogesh.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yogesh.app.ws.shared.dto.UserDto;
import com.yogesh.app.ws.ui.model.request.UserDetailsRequestModel;
import com.yogesh.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	
	@GetMapping
	public String getUser()
	{
		return "get user api called";
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest returnValue=new UserRest();
		UserDto userDto=new UserDto();
		
		BeanUtils.copyProperties(userDetails, userDto);
		//UserDto createdUser=userService.createUser(userDto);
		BeanUtils.copyProperties(returnValue, createdUser);
		return returnValue;
	}
	
	@PutMapping
	public String updateUser()
	{
		return "update user called";
	}
	@DeleteMapping
	public String deleteUser()
	{
		return "delete user called";
	}
	
}
