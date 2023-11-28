package com.jsp.OnlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.AddressDao;
import com.jsp.OnlinePharmacy.dao.AdminDao;
import com.jsp.OnlinePharmacy.dao.MedicalstoreDao;
import com.jsp.OnlinePharmacy.dto.AddressDto;
import com.jsp.OnlinePharmacy.dto.AdminDto;
import com.jsp.OnlinePharmacy.dto.MedicalStoreDto;
import com.jsp.OnlinePharmacy.entity.Address;
import com.jsp.OnlinePharmacy.entity.Admin;
import com.jsp.OnlinePharmacy.entity.MedicalStore;
import com.jsp.OnlinePharmacy.exception.AddressIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.AdminIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class MedicalstoreService {
	
	@Autowired
	private MedicalstoreDao storeDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> saveMedicalstore(int adminId, int addressId,
			MedicalStoreDto medicalStoreDto) {
		
		MedicalStore medicalStore = this.modelMapper.map(medicalStoreDto,MedicalStore.class);
	// this medical store is not having the admin and address yet
	// so first i need to get that admin
		Admin dbAdmin = adminDao.findAdminById(adminId);
	// i am checking weather the admin is present or not 
		if(dbAdmin != null) {
			
			medicalStore.setAdmin(dbAdmin);
			
		Address dbAddress =	addressDao.findAddressById(addressId);
		if(dbAddress != null) {
			
//			medicalStore.setAddress(dbAddress);
//			 MedicalStore dbMedicalStore = storeDao.saveMedicalStore(medicalStore);
//			 
//			 ResponseStructure<MedicalStore> structure = new ResponseStructure<MedicalStore>();
//			 structure.setMessage("medicalstore added successfully");
//			 structure.setStatus(HttpStatus.CREATED.value());
//			 structure.setData(dbMedicalStore);
			// by using above method we are getting password in output sensitive data should be hidden 
			// so we are using below method to hide that sensitive data
			
			 medicalStore.setAddress(dbAddress);
			 MedicalStore dbMedicalStore = storeDao.saveMedicalStore(medicalStore);
			 Address dbMedicalStoreAddress = dbMedicalStore.getAddress();
			 AddressDto addressDto = this.modelMapper.map(dbMedicalStoreAddress,AddressDto.class);
			 
			 Admin dbMedicalStoreAdmin = dbMedicalStore.getAdmin();
			 MedicalStoreDto dto= this.modelMapper.map(dbMedicalStore,MedicalStoreDto.class);
			 dto.setAddressDto(addressDto);
			 dto.setAdminDto(this.modelMapper.map(dbMedicalStoreAdmin, AdminDto.class));
			 
			 ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			 structure.setMessage("medicalstore added successfully");
			 structure.setStatus(HttpStatus.CREATED.value());
			 structure.setData(dto);
			 return new ResponseEntity<ResponseStructure<MedicalStoreDto>> (structure,HttpStatus.CREATED);
			
		}else {
			throw new AddressIdNotFoundException("sorry failed to add medicalstore");
		}
			
		}else {
			throw new AdminIdNotFoundException("sorry failed to add medicalstore");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalstore(int storeId,
			MedicalStore medicalStore) {
		// medicalstore=name,manager name,phone
		
		MedicalStore dbMedicalStore = storeDao.updateMedicalStore(storeId, medicalStore);
		if(dbMedicalStore != null) {
			
			MedicalStoreDto storeDto = this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(),AdminDto.class));
			
			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("medical store updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(storeDto);
			
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>> (structure,HttpStatus.OK);
			
		}else {
			
				//when that id is not present
				throw new  MedicalStoreIdNotFoundException("sorry failed to update medical store");
			
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalstore(int storeId) {
		
		MedicalStore dbMedicalStore = storeDao.deleteMedicalstore(storeId);
		if(dbMedicalStore != null) {
			
			MedicalStoreDto storeDto = this.modelMapper.map(dbMedicalStore,MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(),AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(), AdminDto.class));
			
			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("medicalstore deleted successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(storeDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>> (structure,HttpStatus.GONE);
		}
		else {
			throw new MedicalStoreIdNotFoundException("sorry failed to delete medical store");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> getMedicalstoreById(int storeId) {
		
		MedicalStore dbMedicalStore = storeDao.getMedicalstoreById(storeId);
		
		if(dbMedicalStore != null) {
			
		MedicalStoreDto storeDto =	this.modelMapper.map(dbMedicalStore,MedicalStoreDto.class);
		storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(),AddressDto.class));
		storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(),AdminDto.class));
		
		ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
		structure.setMessage("medicalstore fetched successfully");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(storeDto);
		return new ResponseEntity<ResponseStructure<MedicalStoreDto>> (structure,HttpStatus.FOUND);
			
		}else {
			throw new MedicalStoreIdNotFoundException("sorry failed to fetch the medicalstore");
		}
	}

}
