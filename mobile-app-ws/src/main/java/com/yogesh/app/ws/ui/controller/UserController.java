package com.yogesh.app.ws.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogesh.app.ws.service.UserService;
import com.yogesh.app.ws.shared.dto.UserDto;
import com.yogesh.app.ws.ui.model.request.UserDetailsRequestModel;
import com.yogesh.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@GetMapping
	public String getUser() {
		return "get user api called";
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws JsonProcessingException {
		UserRest returnValue = new UserRest();// to return the value
		UserDto userDto = new UserDto();// to transfer the user

		BeanUtils.copyProperties(userDetails, userDto);// (source,target)
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		logger.info("Return Value=" + new ObjectMapper().writeValueAsString(returnValue));
		return returnValue;
	}

	@PutMapping
	public String updateUser() {
		return "update user called";
	}

	@DeleteMapping
	public String deleteUser() {
		return "delete user called";
	}

}
