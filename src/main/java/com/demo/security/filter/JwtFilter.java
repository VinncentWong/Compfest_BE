package com.demo.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.security.authentication.JwtAuthentication;
import com.demo.service.UserService;
import com.demo.util.JwtUtil;

public class JwtFilter extends OncePerRequestFilter{
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("Masuk ke jwt filter");
		String header = request.getHeader("Authorization");
		log.info("header = " + header);
		if(!header.startsWith("Bearer") || header == null) {
			throw new ServletException("Token doesn't valid");
		}
		String token = header.substring(7);
		log.info("validasi jwt filter = " + new JwtUtil().validateToken(token));
		if(new JwtUtil().validateToken(token)) {
			List<GrantedAuthority> list = new ArrayList<>();
			list.add(new SimpleGrantedAuthority("USER"));
			SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(null, null, list));
			filterChain.doFilter(request, response);
		} else {
			SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(null, null));
			filterChain.doFilter(request, response);
		}
	}
	
}
