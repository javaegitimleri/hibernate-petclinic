package com.javaegitimleri.petclinic.service;

import com.javaegitimleri.petclinic.dao.ClinicDao;
import com.javaegitimleri.petclinic.dao.OwnerDao;
import com.javaegitimleri.petclinic.model.Clinic;
import com.javaegitimleri.petclinic.model.Owner;

public class PetClinicService {
	
	private OwnerDao ownerDao;
	private ClinicDao clinicDao;
	
	public void setOwnerDao(OwnerDao ownerDao) {
		this.ownerDao = ownerDao;
	}
	
	public void setClinicDao(ClinicDao clinicDao) {
		this.clinicDao = clinicDao;
	}
	
	public void addNewOwners(Long clinicId, Owner...owners) {
		Clinic clinic = clinicDao.findById(clinicId);
		for(Owner owner:owners) {
			ownerDao.create(owner);
			clinic.getPersons().add(owner);
		}
	}
}
