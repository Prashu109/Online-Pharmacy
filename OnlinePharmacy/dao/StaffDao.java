package com.jsp.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.OnlinePharmacy.entity.Staff;
import com.jsp.OnlinePharmacy.repository.StaffRepo;

@Repository
public class StaffDao {
	
	@Autowired
	private StaffRepo repo;
	
	public Staff saveStaff(Staff staff) {
		
		return repo.save(staff);
	}

	public Staff updateStaff(int staffId, Staff staff) {

		Optional<Staff> optional = repo.findById(staffId);
		if(optional.isPresent()) {
			
			Staff oldStaff = optional.get();	
			staff.setStaffId(staffId);
			staff.setAdmin(oldStaff.getAdmin());
			staff.setMedicalStore(oldStaff.getMedicalStore());
			
			return repo.save(staff);
			
		}else {
			return null;
		}
	}
	
	public Staff deleteStaffById(int staffId) {
		Optional<Staff> optional = repo.findById(staffId);
		if(optional.isPresent()) {
			
			Staff staff = optional.get();
			repo.delete(staff);
			
			return staff;
		}else {
			return null;
		}
	}
	
	public Staff getStaffById(int staffId) {
		
	Optional<Staff> optional =	repo.findById(staffId);

	if(optional.isPresent()) {
		
		Staff staff = optional.get();
		
		return staff;
	}else {
		return null;
	}
	}
	

}
