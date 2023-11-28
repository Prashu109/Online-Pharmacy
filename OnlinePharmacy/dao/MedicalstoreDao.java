package com.jsp.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.OnlinePharmacy.entity.MedicalStore;
import com.jsp.OnlinePharmacy.repository.MedicalstoreRepo;

@Repository
public class MedicalstoreDao {
	
	@Autowired
	private MedicalstoreRepo repo;

	public MedicalStore saveMedicalStore(MedicalStore medicalStore) {
		return repo.save(medicalStore);
	}

	public MedicalStore updateMedicalStore(int storeId, MedicalStore medicalStore) {
		// medicalstore=name,managername ,address
		Optional<MedicalStore> optional = repo.findById(storeId);
		
		if(optional.isPresent()) {
		MedicalStore oldMedicalStore=optional.get();
    	medicalStore.setStoreId(storeId);
//    	medicalstore=id,name,managername ,address
    	medicalStore.setAdmin(oldMedicalStore.getAdmin());
    	medicalStore.setAddress(oldMedicalStore.getAddress());
//    	medicalstore is havig id,name,managername,phone,admin address
    	return repo.save(medicalStore);
//		if(optional.isPresent()) {
//			repo.findById(storeId).get();
//			medicalStore.setStoreId(storeId);
//			 return repo.save(medicalStore);
//		}
		}else {
			return null;
		}
		
	}
	public MedicalStore deleteMedicalstore(int storeId) {
		
		Optional<MedicalStore> optional = repo.findById(storeId);
		if(optional.isPresent()) {
			MedicalStore medicalStore = optional.get();
			repo.delete(medicalStore);
			return  medicalStore;
		
	}else {
		return null;
	 }
	}
	
	public MedicalStore getMedicalstoreById(int storeId) {
		
		Optional<MedicalStore> optional = repo.findById(storeId);
		if(optional.isPresent()) {
			MedicalStore medicalStore = optional.get();
			return medicalStore;
		}
		else {
			return null;
		}
	}
	
}
