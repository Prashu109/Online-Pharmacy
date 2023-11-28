package com.jsp.OnlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.OnlinePharmacy.entity.Medicine;
import com.jsp.OnlinePharmacy.repository.MedicineRepo;

@Repository
public class MedicineDao {
	
	@Autowired
	private MedicineRepo repo;
	
	public Medicine saveMedicine( Medicine medicine) {
		
		return repo.save(medicine);
	}

	public Medicine updateMedicine(int medicineId, Medicine medicine) {
		Optional<Medicine> optional=repo.findById(medicineId);
		if(optional.isPresent()) {
			medicine.setMedcineId(medicineId);
			medicine.setMedicalStore(optional.get().getMedicalStore());
			return repo.save(medicine);
		}
		return null;
	}
	
	public Medicine deleteMedicine(int medicineId) {
		
		Optional<Medicine> optional = repo.findById(medicineId);
		if(optional.isPresent()) {
			
			Medicine medicine = optional.get();
			repo.deleteById(medicineId);
			return medicine;
		}else {
			return null;
		}
	}
	
	public Medicine getMedicineById(int medicineId) {
		
		Optional<Medicine> optional = repo.findById(medicineId);
		if(optional.isPresent()) {
			
			Medicine medicine = optional.get();
			
			return medicine;
		}else {
			return null;
		}
	}


}
