package com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.dto.UserDto;
import com.demo.entity.User;
import com.demo.repository.UserRepository;

import util.AppResponse;
import util.Bcrypt;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AppResponse response;
	
	@Autowired
	private Bcrypt bcrypt;
	
	public ResponseEntity<AppResponse> createUser(UserDto tempUser){
		User user = new User();
		user.setStudentID(tempUser.getUserId());
		user.setPassword(user.getPassword());
		user.setPassword(bcrypt.bcrypt().encode(user.getPassword()));
		userRepository.save(user);
		response.getMap().put("message", "Success");
		response.getMap().put("data", user);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	public ResponseEntity<AppResponse> login(UserDto tempUser) {
		Optional<User> user = userRepository.findByUserId(tempUser.getUserId());
		if(bcrypt.bcrypt().matches(user.get().getPassword(), user.get().getPassword())) {
			
		}
	}
	
	public ResponseEntity<AppResponse> findUserById(Long id){
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			response.getMap().put("message", "Error");
			response.getMap().put("data", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		response.getMap().put("message", "Success");
		response.getMap().put("data", user.get());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
