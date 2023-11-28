package com.jsp.OnlinePharmacy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.OnlinePharmacy.entity.Address;
import com.jsp.OnlinePharmacy.entity.Customer;
import com.jsp.OnlinePharmacy.repository.CustomerRepo;

@Repository
public class CustomerDao {
	
	@Autowired
	private CustomerRepo repo;

	public Customer saveCustomer(Customer customer) {

		return repo.save(customer);
	}

	public Customer updateCustomer(int customerId, Customer customer) {
		Optional<Customer> optional= repo.findById(customerId);
	
		if(optional.isPresent()) {
			
			customer.setCustomerId(customerId);
			customer.setAddress(optional.get().getAddress());
			customer.setBookings(optional.get().getBookings());
			
			return repo.save(customer);
		}else {
			return null;
		}
	}
	
	public Customer deleteCustomer(int customerId) {
		
		Optional<Customer> optional = repo.findById(customerId);
		if(optional.isPresent()) {
			
			Customer customer = optional.get();
			List<Address> address =  customer.getAddress();
			for(Address address2 : address) {
				
				address2.setCustomer(null);
				
			}
			System.out.println(customer);
			repo.delete(customer);
			
			return customer;
		}else {
			return null;
		}
	}

	public Customer getCustomerById(int customerId) {

		Optional<Customer> optional = repo.findById(customerId);
		if(optional.isPresent()) {
			
			 Customer  customer = optional.get();
			 return customer;
		}
		else {
			return  null;
		}
	}

	

}
