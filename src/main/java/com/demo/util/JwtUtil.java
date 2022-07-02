package com.demo.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private static final String SECRET_KEY = "JWT_SECRET_FOR_A_WHILE";
	
	public String generateToken(Long id, String userId) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		String token = Jwts.builder()
							.signWith(SignatureAlgorithm.HS256, Base64.encode(SECRET_KEY.getBytes()))
							.setSubject(userId)
							.setClaims(map)
							.compact();
		return token;
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
			.setSigningKey(Base64.encode(SECRET_KEY.getBytes()))
			.parseClaimsJws(token);
			return true;
		}
		catch(Exception exception) {
			return false;
		}
					
	}
}
