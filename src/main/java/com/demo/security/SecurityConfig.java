package com.demo.security;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
		CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        
		http
		.authorizeRequests()
		.anyRequest()
		.permitAll()
		.and()
		.addFilterAt(new CustomUsernamePasswordFilter(), UsernamePasswordAuthenticationFilter.class)
		.addFilterAfter(new JwtFilter(), CustomUsernamePasswordFilter.class)
		.csrf().disable()
		.cors().configurationSource(request -> corsConfiguration);
//		.cors(cors -> {
//			CorsConfigurationSource source = (request) -> {
//				CorsConfiguration configuration = new CorsConfiguration();
//				configuration.setAllowCredentials(true);
//				configuration.setAllowedOrigins(List.of("http://192.168.1.20:3000"));
//				configuration.setAllowedHeaders(List.of("*"));
//				configuration.setAllowedMethods(List.of("*"));
//				configuration.setExposedHeaders(List.of("Access-Control-Allow-Origin", "Access-Control-Request-Headers", "Access-Control-Allow-Credentials", "Access-Control-Request-Method"));
//				return configuration;
//			};
//			cors.configurationSource(source);
//		});
	}
//	@Bean
//	public CorsFilter corsFilter() {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowCredentials(true);
//		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
//		corsConfiguration.setExposedHeaders(Arrays.asList("*"));
//		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
//		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//		return new CorsFilter(urlBasedCorsConfigurationSource);
//		
//	}
}
