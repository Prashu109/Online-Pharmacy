package com.jsp.OnlinePharmacy.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.AddressDao;
import com.jsp.OnlinePharmacy.dao.CustomerDao;
import com.jsp.OnlinePharmacy.dto.AddressDto;
import com.jsp.OnlinePharmacy.dto.CustomerDto;
import com.jsp.OnlinePharmacy.entity.Address;
import com.jsp.OnlinePharmacy.entity.Customer;
import com.jsp.OnlinePharmacy.exception.AddressAlreadyMappedToOtherCustomer;
import com.jsp.OnlinePharmacy.exception.AddressIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.CustomerIdNotFoundException;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao  customerDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(int addressId, Customer customer) {

		Address dbAddress =	addressDao.findAddressById(addressId);
//			checking addressId is present or not
		if(dbAddress != null) {
 			
			
			if(dbAddress.getCustomer() != null) {
				throw new AddressAlreadyMappedToOtherCustomer("sorry");
				
			}
			dbAddress.setCustomer(customer);
//			yes addressId is present
			
			
			List<Address> addresses = new ArrayList<Address>();
				addresses.add(dbAddress);
//			customer =only the own attributes not a relationship attributes
			customer.setAddress(addresses);
//			now customer is having address also
			Customer dbCustomer = customerDao.saveCustomer(customer);
//			dbCustomer is having its own attributes then relationship attributes that is list address and list of bookings but its null
//			but list of address is present
//			copy Customer to CustomerDto
//			but this customer dto is having ListofaddressDto and ListOfBooking dto but still it is null
//			copy List of Addressto a List of addressDto
			CustomerDto customerDto= this.modelMapper.map(dbCustomer,CustomerDto.class);
			List<AddressDto> addressDtos;
			for(Address address:dbCustomer.getAddress()) {
				AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
				addressDtos = new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			}
			customerDto.setBookingDtos(null);
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("customer added successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>> (structure,HttpStatus.CREATED);
		}else {
			throw new AddressIdNotFoundException("sorry failed to add customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(int customerId, Customer customer) {
		
		Customer dbCustomer= customerDao.updateCustomer(customerId,customer);
		if(dbCustomer != null) {
			CustomerDto customerDto= this.modelMapper.map(dbCustomer,CustomerDto.class);
			List<AddressDto> addressDtos;
			for(Address address:dbCustomer.getAddress()) {
				AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
				addressDtos = new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			}
			customerDto.setBookingDtos(null);
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("customer updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>> (structure,HttpStatus.OK);
		
			
		}else {
			throw new CustomerIdNotFoundException("sorry failed to update customer");
		}
	}
	
	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(int customerId){
		
		Customer dbCustomer= customerDao.deleteCustomer(customerId);
	
		if(dbCustomer != null) {
			CustomerDto customerDto= this.modelMapper.map(dbCustomer,CustomerDto.class);
			List<AddressDto> addressDtos;
			for(Address address:dbCustomer.getAddress()) {
				AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
				addressDtos = new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			}
			customerDto.setBookingDtos(null);
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("customer deleted successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>> (structure,HttpStatus.GONE);
		
			
		}else {
			throw new CustomerIdNotFoundException("sorry failed to delete customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomerById(int customerId) {

		Customer dbCustomer = customerDao.getCustomerById(customerId);
		if(dbCustomer != null) {
			CustomerDto customerDto= this.modelMapper.map(dbCustomer,CustomerDto.class);
			List<AddressDto> addressDtos;
			for(Address address:dbCustomer.getAddress()) {
				AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
				addressDtos = new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddresses(addressDtos);
			}
			customerDto.setBookingDtos(null);
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("customer fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>> (structure,HttpStatus.FOUND);
		
			
		}else {
			throw new CustomerIdNotFoundException("sorry failed to fetch the customer");
		}
	}

}
