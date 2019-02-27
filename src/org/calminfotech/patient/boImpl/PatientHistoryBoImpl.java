package org.calminfotech.patient.boImpl;



import java.util.List;

import org.calminfotech.patient.boInterface.PatientHistoryBo;
import org.calminfotech.patient.daoInterface.PatientHistoryDao;
import org.calminfotech.patient.daoInterface.PatientHistoryDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHistory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
	public class PatientHistoryBoImpl implements PatientHistoryBo{

	
	@Autowired
	private PatientHistoryDao patientHistoryDao;

	@Override
	public void save(PatientHistory patientHistory) {
		// TODO Auto-generated method stub
		this.patientHistoryDao.save(patientHistory);
	}

	@Override
	public void delete(PatientHistory patientHistory) {
		// TODO Auto-generated method stub
		this.patientHistoryDao.delete(patientHistory);
	}

	@Override
	public PatientHistory getPatientHistoryById(int id) {
		// TODO Auto-generated method stub
		return this.patientHistoryDao.getPatientHistoryById(id);
	}

	@Override
	public void update(PatientHistory patientHistory) {
		this.patientHistoryDao.update(patientHistory);		
	}

	@Override
	public List<PatientHistory> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return patientHistoryDao.fetchAll(patient);
	}



	
	
	
}
