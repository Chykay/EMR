package org.calminfotech.patient.boImpl;



import java.util.List;

import org.calminfotech.patient.boInterface.PatientTelephoneBo;
import org.calminfotech.patient.daoInterface.PatientHistoryDao;
import org.calminfotech.patient.daoInterface.PatientTelephoneDao;
import org.calminfotech.patient.models.Patient;

import org.calminfotech.patient.models.PatientTelephone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
	public class PatientTelephoneBoImpl implements PatientTelephoneBo{

	
	@Autowired
	private PatientTelephoneDao patientTelephoneDao;

	@Override
	public void save(PatientTelephone patientTelephone) {
		// TODO Auto-generated method stub
		this.patientTelephoneDao.save(patientTelephone);
	}

	@Override
	public void delete(PatientTelephone patientTelephone) {
		// TODO Auto-generated method stub
		this.patientTelephoneDao.delete(patientTelephone);
	}

	@Override
	public PatientTelephone getPatientTelephoneById(int id) {
		// TODO Auto-generated method stub
		return this.patientTelephoneDao.getPatientTelephoneById(id);
	}

	@Override
	public void update(PatientTelephone patientTelephone) {
		this.patientTelephoneDao.update(patientTelephone);		
	}

	@Override
	public List<PatientTelephone> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return patientTelephoneDao.fetchAll(patient);
	}



	
	
	
}
