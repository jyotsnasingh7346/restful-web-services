package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

/**
 * This class defines the fields we want in our exceptions to be specified when an exception is thrown
 */
public class ErrorDetails {

	private LocalDateTime Timestamp;
	private String message;
	private String details;

	public ErrorDetails(LocalDateTime timestamp, String message, String details) {
		super();
		Timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return Timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
