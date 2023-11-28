package com.jsp.OnlinePharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressMappedToMedicalstore extends RuntimeException{
 
	private String message;

	public String getMessage() {
		return message;
	}

	
	
}
