package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/*
	 * When something is not found, we should return 404 
	 * for this, use @ResponseStatus annotation.
	 * If you are getting a huge stack trace in the browser and dont want it, 
	 * just comment devtools dependency in pom & restart the application.
	 */	
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	/*
	 * Note : 
	 * If we access a non existing user (eg : /users/412), 
	 * in the web browser, 
	 * We are getting a UserNotFoundException with status code 404 (NOT FOUND), in String format.
	 * -------------------------------------------------------------------------------------------
	 * If we access a non existing user (eg : /users/412), 
	 * in POSTMAN / Talend API Tester,
	 * We are getting a UserNotFoundException with status code 404 (NOT FOUND), in JSON format.
	 * */
	
}