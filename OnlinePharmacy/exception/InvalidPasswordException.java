package com.jsp.OnlinePharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class InvalidPasswordException extends RuntimeException {

	
	private String message;

	public String getMessage() {
		return message;
	}

	
	
}
