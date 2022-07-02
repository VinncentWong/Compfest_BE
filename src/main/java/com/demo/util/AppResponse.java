package com.demo.util;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AppResponse {
	private Map<String, Object> map;
}
