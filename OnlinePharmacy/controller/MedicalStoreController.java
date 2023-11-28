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

import com.jsp.OnlinePharmacy.dto.MedicalStoreDto;
import com.jsp.OnlinePharmacy.entity.MedicalStore;
import com.jsp.OnlinePharmacy.service.MedicalstoreService;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/store")
public class MedicalStoreController {
	
	@Autowired
	private MedicalstoreService service;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> saveMedicalstore(@RequestParam int adminId, @RequestParam int addressId ,@RequestBody MedicalStoreDto medicalStoreDto){
		
		return service.saveMedicalstore(adminId,addressId,medicalStoreDto);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalstore(@RequestParam int storeId, @RequestBody MedicalStore medicalStore){
		return service.updateMedicalstore(storeId, medicalStore);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalstore(@RequestParam int storeId){
		return service.deleteMedicalstore(storeId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> getMedicalstoreById(@RequestParam int storeId){
		return service.getMedicalstoreById(storeId);
	}

}
