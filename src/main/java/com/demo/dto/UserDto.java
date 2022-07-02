package com.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDto {
	@NotNull
	@NotBlank
	private String studentNumber;
	
	@NotNull
	@NotBlank
	private String password;
}
