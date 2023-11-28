package com.jsp.OnlinePharmacy.exception;

public class AddressAlreadyMappedToOtherCustomer extends RuntimeException {
	
	private String message;

	public AddressAlreadyMappedToOtherCustomer(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	

}
