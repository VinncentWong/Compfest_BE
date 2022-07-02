package com.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.demo.security.filter.CustomUsernamePasswordFilter;
import com.demo.security.filter.JwtFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().mvcMatchers("/login", "/registration");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.addFilterAt(new CustomUsernamePasswordFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterAfter(new JwtFilter(), CustomUsernamePasswordFilter.class);
		http.cors(cors -> {
			CorsConfigurationSource source = (request) -> {
				CorsConfiguration configuration = new CorsConfiguration();
				configuration.addAllowedOrigin("http://127.0.0.1:3000");
				configuration.addAllowedMethod("*");
				configuration.addAllowedHeader("*");
				return configuration;
			};
		});
	}
	
	
}
