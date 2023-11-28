package com.jsp.OnlinePharmacy.repository;



import org.springframework.data.jpa.repository.JpaRepository;



import com.jsp.OnlinePharmacy.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{


}
