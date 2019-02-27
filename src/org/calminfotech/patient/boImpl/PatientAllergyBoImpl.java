package org.calminfotech.patient.boImpl;



import java.util.List;

import org.calminfotech.patient.boInterface.PatientAllergyBo;
import org.calminfotech.patient.daoInterface.PatientHistoryDao;
import org.calminfotech.patient.daoInterface.PatientAllergyDao;
import org.calminfotech.patient.models.Patient;

import org.calminfotech.patient.models.PatientAllergy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
	public class PatientAllergyBoImpl implements PatientAllergyBo{

	
	@Autowired
	private PatientAllergyDao patientAllergyDao;

	@Override
	public void save(PatientAllergy patientAllergy) {
		// TODO Auto-generated method stub
		this.patientAllergyDao.save(patientAllergy);
	}

	@Override
	public void delete(PatientAllergy patientAllergy) {
		// TODO Auto-generated method stub
		this.patientAllergyDao.delete(patientAllergy);
	}

	@Override
	public PatientAllergy getPatientAllergyById(int id) {
		// TODO Auto-generated method stub
		return this.patientAllergyDao.getPatientAllergyById(id);
	}

	@Override
	public void update(PatientAllergy patientAllergy) {
		this.patientAllergyDao.update(patientAllergy);		
	}

	@Override
	public List<PatientAllergy> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return patientAllergyDao.fetchAll(patient);
	}



	
	
	
}
