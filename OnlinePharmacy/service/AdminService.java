package com.jsp.OnlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.AdminDao;
import com.jsp.OnlinePharmacy.dto.AdminDto;
import com.jsp.OnlinePharmacy.entity.Admin;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin) {
		
		Admin dbAdmin = adminDao.saveAdmin(admin);
		
		AdminDto adminDto = this.modelMapper.map(dbAdmin,AdminDto.class);
		
		ResponseStructure<AdminDto> structure = new ResponseStructure<>();
		structure.setMessage("admin saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(adminDto);
		
		return new ResponseEntity<ResponseStructure<AdminDto>> (structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(int adminId, Admin admin) {
		
		 Admin dbAdmin = adminDao.updateAdmin(adminId,admin);
		
		if(dbAdmin != null) {
			AdminDto adminDto = this.modelMapper.map(dbAdmin,AdminDto.class);
			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setMessage("admin updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(adminDto);
			
			return new ResponseEntity<ResponseStructure<AdminDto>> (structure,HttpStatus.OK);
		
		}else {
			// raise adminId not found exception
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(int adminId) {
			
			Admin dbAdmin = adminDao.deleteAdmin(adminId);
			if(dbAdmin != null) {
				AdminDto adminDto = this.modelMapper.map(dbAdmin,AdminDto.class);
				ResponseStructure<AdminDto> structure = new ResponseStructure<>();
				structure.setMessage("admin deleted  successfully");
				structure.setStatus(HttpStatus.GONE.value());
				structure.setData(adminDto);
				
				return new ResponseEntity<ResponseStructure<AdminDto>> (structure,HttpStatus.GONE);
				
			}
			else {
			// raise adminId not found exception
				return null;
			}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> findAdminById(int adminId) {

		 Admin dbAdmin = adminDao.findAdminById(adminId);
		
		 if(dbAdmin != null) {
			AdminDto adminDto =  this.modelMapper.map(dbAdmin,AdminDto.class);
			 ResponseStructure<AdminDto> structure = new ResponseStructure<>();
				structure.setMessage("admin found successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(adminDto);
				
			 return new ResponseEntity<ResponseStructure<AdminDto>> (structure,HttpStatus.FOUND);
		 }else {
			 //  raise adminId not found exception 
			 return null;
		 }

	}

}
