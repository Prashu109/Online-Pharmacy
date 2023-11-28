package com.jsp.OnlinePharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlinePharmacy.dao.MedicalstoreDao;
import com.jsp.OnlinePharmacy.dao.MedicineDao;
import com.jsp.OnlinePharmacy.dto.MedicineDto;
import com.jsp.OnlinePharmacy.entity.MedicalStore;
import com.jsp.OnlinePharmacy.entity.Medicine;
import com.jsp.OnlinePharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.OnlinePharmacy.exception.MedicineIdNotFoundException;
import com.jsp.OnlinePharmacy.util.ResponseStructure;

@Service
public class MedicineService {
	
	@Autowired
	private MedicineDao medicineDao;
	
	@Autowired
	private MedicalstoreDao storeDao;
	
	public ResponseEntity<ResponseStructure<Medicine>> saveMedicine(int storeId,Medicine medicine){
		
		MedicalStore dbMedicalStore = storeDao.getMedicalstoreById(storeId);
		if(dbMedicalStore != null) {
			
			medicine.setMedicalStore(dbMedicalStore);
			Medicine dbMedicine = medicineDao.saveMedicine(medicine);
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("medicine added successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbMedicine);
			
			return new ResponseEntity<ResponseStructure<Medicine>> (structure,HttpStatus.CREATED);
			
		}else {
			throw new MedicalStoreIdNotFoundException("sorry failed to add medicine");
		}
		
	}

	public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(int medicineId, Medicine medicine) {
		  Medicine dbMedicine=medicineDao.updateMedicine(medicineId,medicine);
			if(dbMedicine!=null) {
				ResponseStructure<Medicine> structure=new ResponseStructure<Medicine>();
				structure.setMessage("medicine updated successfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dbMedicine);
				return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.OK);
			}else {
				throw new MedicineIdNotFoundException("Sorry failed to update Medicine");
			}
		}
	
	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicine(int medicineId){
		Medicine dbMedicine = medicineDao.deleteMedicine(medicineId);
	
		if(dbMedicine != null) {
			
			ResponseStructure<Medicine> structure=new ResponseStructure<Medicine>();
			structure.setMessage("medicine deleted successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.GONE);
		}else {
			throw new MedicineIdNotFoundException("Sorry failed to delete Medicine");
		}
	}
	
	public ResponseEntity<ResponseStructure<Medicine>> getMedicineById(int medicineId){
		
		Medicine dbMedicine = medicineDao.getMedicineById(medicineId);
		if(dbMedicine != null) {
			
			ResponseStructure<Medicine> structure=new ResponseStructure<Medicine>();
			structure.setMessage("medicine fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.FOUND);
		}else {
			throw new MedicineIdNotFoundException("Sorry failed to fetched  Medicine");
		}
	}

}
