package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.UserDto;
import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.util.AppResponse;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/registration")
	public ResponseEntity<AppResponse> registration(@Valid @RequestBody UserDto user){
		return userService.createUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AppResponse> login(@Valid @RequestBody UserDto user){
		return userService.login(user);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<AppResponse> getUserById(@PathVariable Long id){
		return userService.findUserById(id);
	}
	
	@PostMapping("/addbalance/{id}")
	public ResponseEntity<AppResponse> addBalance(@RequestBody User user, @PathVariable Long id){
		return userService.addBalance(user.getBalance(), id);
	}
}
