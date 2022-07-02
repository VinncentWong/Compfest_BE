package com.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDto {
	@NotNull
	@NotBlank
	private String userID;
	
	@NotNull
	@NotBlank
	private String password;
}
