package org.calminfotech.patient.boImpl;



import java.util.List;

import org.calminfotech.patient.boInterface.PatientFamilyHistoryBo;
import org.calminfotech.patient.daoInterface.PatientFamilyHistoryDao;
import org.calminfotech.patient.daoInterface.PatientFamilyHistoryDao;
import org.calminfotech.patient.models.Patient;

import org.calminfotech.patient.models.PatientFamilyHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
	public class PatientFamilyHistoryBoImpl implements PatientFamilyHistoryBo{

	
	@Autowired
	private PatientFamilyHistoryDao patientFamilyHistoryDao;

	@Override
	public void save(PatientFamilyHistory patientFamilyHistory) {
		// TODO Auto-generated method stub
		this.patientFamilyHistoryDao.save(patientFamilyHistory);
	}

	@Override
	public void delete(PatientFamilyHistory patientFamilyHistory) {
		// TODO Auto-generated method stub
		this.patientFamilyHistoryDao.delete(patientFamilyHistory);
	}

	@Override
	public PatientFamilyHistory getPatientFamilyHistoryById(int id) {
		// TODO Auto-generated method stub
		return this.patientFamilyHistoryDao.getPatientFamilyHistoryById(id);
	}

	@Override
	public void update(PatientFamilyHistory patientFamilyHistory) {
		this.patientFamilyHistoryDao.update(patientFamilyHistory);		
	}

	@Override
	public List<PatientFamilyHistory> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return patientFamilyHistoryDao.fetchAll(patient);
	}



	
	
	
}
