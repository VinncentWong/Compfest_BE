package com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.dto.UserDto;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.util.AppResponse;
import com.demo.util.BcryptUtil;
import com.demo.util.JwtUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AppResponse response;
	
	@Autowired
	private BcryptUtil bcrypt;
	
	@Autowired
	private JwtUtil jwt;
	
	public ResponseEntity<AppResponse> createUser(UserDto tempUser){
		User user = new User();
		user.setStudentId(tempUser.getUserId());
		user.setPassword(user.getPassword());
		user.setPassword(bcrypt.bcrypt().encode(user.getPassword()));
		userRepository.save(user);
		response.getMap().put("message", "Success");
		response.getMap().put("data", user);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	public ResponseEntity<AppResponse> login(UserDto tempUser) {
		Optional<User> user = userRepository.findByStudentId(tempUser.getUserId());
		if(bcrypt.bcrypt().matches(user.get().getPassword(), user.get().getPassword())) {
			String token = jwt.generateToken(user.get().getId(), user.get().getStudentId());
			response.getMap().put("message", "Success");
			response.getMap().put("data", user);
			response.getMap().put("token", token);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		response.getMap().put("message", "Error");
		response.getMap().put("data", null);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
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
