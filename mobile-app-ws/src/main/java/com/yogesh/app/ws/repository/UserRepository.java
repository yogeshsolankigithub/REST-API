package com.yogesh.app.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yogesh.app.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	//UserEntity findUserByEmail(String email);

}
