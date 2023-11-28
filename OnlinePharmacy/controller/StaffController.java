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

import com.jsp.OnlinePharmacy.dto.StaffDto;
import com.jsp.OnlinePharmacy.entity.Staff;
import com.jsp.OnlinePharmacy.service.StaffService;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@RestController 
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private StaffService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<StaffDto>> saveStaff(@RequestParam int adminId, @RequestParam int storeId, @RequestBody Staff staff){
		
		return service.saveStaff(adminId, storeId, staff);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(@RequestParam int staffId, @RequestBody Staff staff){
		return service.updateStaff(staffId, staff);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaff(@RequestParam int staffId){
		return service.deleteStaffById(staffId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<StaffDto>> getStaffById(@RequestParam int staffId){
		return service.getStaffById(staffId);
	}
	

}
