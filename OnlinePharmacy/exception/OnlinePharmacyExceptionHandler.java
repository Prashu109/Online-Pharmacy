package com.jsp.OnlinePharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.OnlinePharmacy.util.ResponseStructure;

@RestControllerAdvice
public class OnlinePharmacyExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	ResponseEntity<ResponseStructure<String>> addressIdNotFoundException(AddressIdNotFoundException ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		
		structure.setMessage("AddressId Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>> (structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	ResponseEntity<ResponseStructure<String>> adminIdNotFoundException(AdminIdNotFoundException ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		
		structure.setMessage("AdminId Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>> (structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	ResponseEntity<ResponseStructure<String>> medicalstoreIdNotFoundException(MedicalStoreIdNotFoundException ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		
		structure.setMessage("MedicalStoreId Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>> (structure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> medicineIdNotFoundException(MedicineIdNotFoundException  ex){
		 ResponseStructure<String> structure=new  ResponseStructure<String>();
		 structure.setMessage("Medicine Id Not Found");
		 structure.setStatus(HttpStatus.NOT_FOUND.value());
		 structure.setData(ex.getMessage());
		 return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> customerIdNotFoundException(CustomerIdNotFoundException  ex){
		 ResponseStructure<String> structure=new  ResponseStructure<String>();
		 structure.setMessage("Customer Id Not Found");
		 structure.setStatus(HttpStatus.NOT_FOUND.value());
		 structure.setData(ex.getMessage());
		 return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressAlreadyMapped(AddressAlreadyMappedToOtherCustomer  ex){
		 ResponseStructure<String> structure=new  ResponseStructure<String>();
		 structure.setMessage("Address Already Mapped to Exception");
		 structure.setStatus(HttpStatus.NOT_FOUND.value());
		 structure.setData(ex.getMessage());
		 return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	ResponseEntity<ResponseStructure<String>> staffIdNotFoundException(StaffIdNotFoundException ex){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		
		structure.setMessage("StaffId Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>> (structure,HttpStatus.NOT_FOUND);
	}
	
	

}
