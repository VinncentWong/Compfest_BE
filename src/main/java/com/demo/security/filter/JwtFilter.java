package com.demo.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.security.authentication.JwtAuthentication;
import com.demo.util.JwtUtil;

public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil util;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		if(!header.startsWith("Bearer") || header == null) {
			throw new ServletException("Token doesn't valid");
		}
		String token = header.substring(7);
		if(util.validateToken(token)) {
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
