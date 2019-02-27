package org.calminfotech.patient.boImpl;



import java.util.List;

import org.calminfotech.patient.boInterface.PatientNokBo;
import org.calminfotech.patient.daoInterface.PatientNokDao;
import org.calminfotech.patient.daoInterface.PatientNokDao;
import org.calminfotech.patient.models.Patient;

import org.calminfotech.patient.models.PatientNok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
	public class PatientNokBoImpl implements PatientNokBo{

	
	@Autowired
	private PatientNokDao patientNokDao;

	@Override
	public void save(PatientNok patientNok) {
		// TODO Auto-generated method stub
		this.patientNokDao.save(patientNok);
	}

	@Override
	public void delete(PatientNok patientNok) {
		// TODO Auto-generated method stub
		this.patientNokDao.delete(patientNok);
	}

	@Override
	public PatientNok getPatientNokById(int id) {
		// TODO Auto-generated method stub
		return this.patientNokDao.getPatientNokById(id);
	}

	@Override
	public void update(PatientNok patientNok) {
		this.patientNokDao.update(patientNok);		
	}

	@Override
	public List<PatientNok> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return patientNokDao.fetchAll(patient);
	}



	
	
	
}
