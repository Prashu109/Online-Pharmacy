package com.jsp.OnlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.AdminDao;
import com.jsp.OnlinePharmacy.dao.MedicalstoreDao;
import com.jsp.OnlinePharmacy.dao.StaffDao;
import com.jsp.OnlinePharmacy.dto.AdminDto;
import com.jsp.OnlinePharmacy.dto.MedicalStoreDto;
import com.jsp.OnlinePharmacy.dto.StaffDto;
import com.jsp.OnlinePharmacy.entity.Admin;
import com.jsp.OnlinePharmacy.entity.MedicalStore;
import com.jsp.OnlinePharmacy.entity.Staff;
import com.jsp.OnlinePharmacy.exception.AdminIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.StaffIdNotFoundException;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class StaffService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private MedicalstoreDao medicalstoreDao;
	
	@Autowired 
	private AdminDao adminDao;
	
	public ResponseEntity<ResponseStructure<StaffDto>> saveStaff(int adminId, int storeId, Staff staff){
		
		MedicalStore dbMedicalStore = medicalstoreDao.getMedicalstoreById(storeId);
		if(dbMedicalStore != null) {
			
			staff.setMedicalStore(dbMedicalStore);
			Admin dbAdmin = adminDao.findAdminById(adminId);
			
			if(dbAdmin != null) {
				staff.setAdmin(dbAdmin);
				Staff dbStaff = staffDao.saveStaff(staff);
				
			StaffDto dbstaffDto =	this.modelMapper.map(dbStaff,StaffDto.class);
			dbstaffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(),AdminDto.class));
			dbstaffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(),MedicalStoreDto.class));
			
			ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
			structure.setMessage("staff saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbstaffDto);
			
			return new ResponseEntity<ResponseStructure<StaffDto>> (structure,HttpStatus.CREATED);
			
			}else {
				throw new AdminIdNotFoundException("sorry failed to add Staff");
			}
			
		}else {
			throw new MedicalStoreIdNotFoundException("sorry failed to add Staff");
		}
		
	}

	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(int staffId, Staff staff) {
		
	Staff dbStaff =	staffDao.updateStaff(staffId, staff);
	if(dbStaff != null) {
		
		StaffDto dbstaffDto =	this.modelMapper.map(dbStaff,StaffDto.class);
		dbstaffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(),AdminDto.class));
		dbstaffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(),MedicalStoreDto.class));
		
		ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
		structure.setMessage("staff updated successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(dbstaffDto);
		
		return new ResponseEntity<ResponseStructure<StaffDto>> (structure,HttpStatus.OK);
		
		
	}else {
		throw new StaffIdNotFoundException("sorry failed to update staff");
	}
	}

	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaffById(int staffId) {

		Staff dbStaff =  staffDao.deleteStaffById(staffId);
		if(dbStaff != null) {
			
			StaffDto dbstaffDto =	this.modelMapper.map(dbStaff,StaffDto.class);
			dbstaffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(),AdminDto.class));
			dbstaffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(),MedicalStoreDto.class));
			
			ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
			structure.setMessage("staff deleted successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(dbstaffDto);
			
			return new ResponseEntity<ResponseStructure<StaffDto>> (structure,HttpStatus.GONE);
			
			
		}else {
			throw new StaffIdNotFoundException("sorry failed to delete staff");
		}
		
	}
	public ResponseEntity<ResponseStructure<StaffDto>> getStaffById(int staffId){
		
		Staff dbStaff = staffDao.getStaffById(staffId);
			if(dbStaff != null) {
			
			StaffDto dbstaffDto =	this.modelMapper.map(dbStaff,StaffDto.class);
			dbstaffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(),AdminDto.class));
			dbstaffDto.setMedicalStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(),MedicalStoreDto.class));
			
			ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
			structure.setMessage("staff data fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbstaffDto);
			
			return new ResponseEntity<ResponseStructure<StaffDto>> (structure,HttpStatus.FOUND);
			
			
		}else {
			throw new StaffIdNotFoundException("sorry failed to fetch the staff");
		}
	}

}
