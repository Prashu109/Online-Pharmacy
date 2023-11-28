package com.jsp.OnlinePharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.AddressDao;
import com.jsp.OnlinePharmacy.dto.AddressDto;
import com.jsp.OnlinePharmacy.entity.Address;
import com.jsp.OnlinePharmacy.exception.AddressIdNotFoundException;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(Address address) {

		Address dbAddress = addressDao.saveAddress(address);
		AddressDto dto = new AddressDto();
		dto.setAddressId(dbAddress.getAddressId());
		dto.setCity(dbAddress.getCity());
		dto.setPincode(dbAddress.getPincode());
		dto.setState(dbAddress.getState());
		dto.setStreetName(dbAddress.getStreetName());

		ResponseStructure<AddressDto> structure = new ResponseStructure<>();
		structure.setMessage("Address saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);

		return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AddressDto>> findAddressById(int addressId) {
		Address dbAddress = addressDao.findAddressById(addressId);
		if (dbAddress != null) {
			AddressDto dto = new AddressDto();
			dto.setAddressId(dbAddress.getAddressId());
			dto.setCity(dbAddress.getCity());
			dto.setPincode(dbAddress.getPincode());
			dto.setState(dbAddress.getState());
			dto.setStreetName(dbAddress.getStreetName());

			ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
			structure.setMessage("AddressData saved successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.FOUND);
		} else {
			 throw new AddressIdNotFoundException("sorry failed to fetch the data");
		}
		
	}
	
		public ResponseEntity<ResponseStructure<AddressDto>>  updateAddress(int addressId,Address address) {
			
			 Address dbAddress =addressDao.updateAddress(addressId, address);
			 if(dbAddress != null) {
				 
				 AddressDto dto = new AddressDto();
					dto.setAddressId(dbAddress.getAddressId());
					dto.setCity(dbAddress.getCity());
					dto.setPincode(dbAddress.getPincode());
					dto.setState(dbAddress.getState());
					dto.setStreetName(dbAddress.getStreetName());
					
					ResponseStructure<AddressDto> structure = new ResponseStructure<>();
					structure.setMessage("updated successfully");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dto);
					return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.OK);
				
			 }else {
				 throw new AddressIdNotFoundException("sorry failed to fetch the data");

				 // raise the exception addressidnotfoundexception
			 }
			
		}
		
		public ResponseEntity<ResponseStructure<AddressDto>> deleteAddress(int addressId){
			
			 Address dbAddress =addressDao.deleteAddressById(addressId);
			 if(dbAddress != null) {
				AddressDto dto = new AddressDto();
				dto.setAddressId(dbAddress.getAddressId());
				dto.setCity(dbAddress.getCity());
				dto.setPincode(dbAddress.getPincode());
				dto.setState(dbAddress.getState());
				dto.setStreetName(dbAddress.getStreetName());
				
				ResponseStructure<AddressDto> structure = new ResponseStructure<>();
				structure.setMessage("deleted successfully");
				structure.setStatus(HttpStatus.GONE.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.GONE);
		}else {
			// raise exception
			 throw new AddressIdNotFoundException("sorry failed to fetch the data");

		}
	}
}
