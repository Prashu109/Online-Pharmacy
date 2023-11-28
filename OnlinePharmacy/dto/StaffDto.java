package com.jsp.OnlinePharmacy.dto;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class StaffDto {

	private int staffId;
	private String staffName;
	private String email;
	private long phoneNumber;

	@ManyToOne
	private AdminDto adminDto;
	
	@OneToOne
	private MedicalStoreDto medicalStoreDto;

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public AdminDto getAdminDto() {
		return adminDto;
	}

	public void setAdminDto(AdminDto adminDto) {
		this.adminDto = adminDto;
	}

	public MedicalStoreDto getMedicalStoreDto() {
		return medicalStoreDto;
	}

	public void setMedicalStoreDto(MedicalStoreDto medicalStoreDto) {
		this.medicalStoreDto = medicalStoreDto;
	}
	
	

}
