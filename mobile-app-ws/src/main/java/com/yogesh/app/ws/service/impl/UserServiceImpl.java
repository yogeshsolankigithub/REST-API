package com.yogesh.app.ws.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogesh.app.ws.io.entity.UserEntity;
import com.yogesh.app.ws.repository.UserRepository;
import com.yogesh.app.ws.service.UserService;
import com.yogesh.app.ws.shared.Utils;
import com.yogesh.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	Utils util;

	@Override
	public UserDto createUser(UserDto userDto) {

		UserEntity storedUser = userRepository.findByEmail(userDto.getEmail());
		if (storedUser != null) {
			throw new RuntimeException("Record already Exist");
		}

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		String publicUserId = util.generateUserId(30);// generate random userid
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));// encode password using
																								// Bcrypt
		UserEntity createdEntity = userRepository.save(userEntity);

		UserDto userDto2 = new UserDto();
		BeanUtils.copyProperties(createdEntity, userDto2);
		try {
			logger.info("Return Value=" + new ObjectMapper().writeValueAsString(userDto2));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return userDto2;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		logger.info("loadByUsername() called...");
		UserEntity userEntity = userRepository.findByEmail(email);
		System.out.println("USer Enity=" + userEntity);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User is not availabe with this username");
		}

		else {
			UserDetails user = new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
			return user;
		}

	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User is not availabe with this username");
		}
		UserDto userDto2 = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto2);
		return userDto2;
	}

}
