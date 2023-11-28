package com.jsp.OnlinePharmacy.exception;

public class MedicalStoreIdNotFoundException extends RuntimeException {
	
	private String message;

	public MedicalStoreIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	
	

}
