package com.jsp.OnlinePharmacy.exception;

public class StaffIdNotFoundException extends RuntimeException {
	
	private String message;

	public StaffIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

		

}
