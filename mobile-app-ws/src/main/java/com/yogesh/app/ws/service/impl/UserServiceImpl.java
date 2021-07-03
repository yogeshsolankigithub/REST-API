package com.yogesh.app.ws.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogesh.app.ws.io.entity.UserEntity;
import com.yogesh.app.ws.repository.UserRepository;
import com.yogesh.app.ws.service.UserService;
import com.yogesh.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	
	@Override
	public UserDto createUser(UserDto userDto) {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setUserId("userid");
		userEntity.setEncryptedPassword("test");
		UserEntity createdEntity = userRepository.save(userEntity);

		UserDto userDto2 = new UserDto();
		BeanUtils.copyProperties(createdEntity, userDto2);
		try {
			logger.info("Return Value="+new ObjectMapper().writeValueAsString(userDto2));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return userDto2;
	}

}
