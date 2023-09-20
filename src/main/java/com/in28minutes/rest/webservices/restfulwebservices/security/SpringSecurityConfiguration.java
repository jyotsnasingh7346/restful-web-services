package com.in28minutes.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration	//Indicates that a class declares one or more @Bean methods and 
// may be processed by the Spring container to generate bean definitions and service requests 
// for those beans at runtime
public class SpringSecurityConfiguration {
	
	/*
	 * SecurityFilterChain is used to define a filter chain 
	 * which is capable of being matched against an HttpServletRequest. 
	 * in order to decide whether it applies to that request. 
	 * */
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 3 steps
		
		// 1. Authenticate All requests
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		
		// 2.If not, a web page is shown
		http.httpBasic(withDefaults());
		
		//3.Disable CSRF for POST & PUT
		http.csrf().disable();
		
		return http.build();
	}
	

}
