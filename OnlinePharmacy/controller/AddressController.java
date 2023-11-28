package com.jsp.OnlinePharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlinePharmacy.dto.AddressDto;
import com.jsp.OnlinePharmacy.entity.Address;
import com.jsp.OnlinePharmacy.service.AddressService;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@RestController // we are creating rest API's so we create rest controller
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(@RequestBody Address address){
		
		return service.saveAddress(address);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<AddressDto>> findAddressById(@RequestParam int addressId){
		return service.findAddressById(addressId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<AddressDto>> updateAddress(@RequestParam int addressId, @RequestBody Address address){
		return service.updateAddress(addressId, address);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddress(@RequestParam int addressId){
		
		return service.deleteAddress(addressId);
	}

}
