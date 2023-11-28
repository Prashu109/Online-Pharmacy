package com.jsp.OnlinePharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class BookingIdNotFoundException extends RuntimeException {

	
	private String message;

	public BookingIdNotFoundException(String message) {
		
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
}
