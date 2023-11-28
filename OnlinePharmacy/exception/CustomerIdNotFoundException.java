package com.jsp.OnlinePharmacy.exception;



public class CustomerIdNotFoundException extends RuntimeException {
	
	private String Message;
	

	public CustomerIdNotFoundException(String message) {
		super();
		Message = message;
	}


	public String getMessage() {
		return Message;
	}

	
	
	
	
	

}
