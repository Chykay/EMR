package org.calminfotech.patient.boImpl;



import java.util.List;

import org.calminfotech.patient.boInterface.PatientAddressBo;
import org.calminfotech.patient.daoInterface.PatientHistoryDao;
import org.calminfotech.patient.daoInterface.PatientAddressDao;
import org.calminfotech.patient.models.Patient;

import org.calminfotech.patient.models.PatientAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
	public class PatientAddressBoImpl implements PatientAddressBo{

	
	@Autowired
	private PatientAddressDao patientAddressDao;

	@Override
	public void save(PatientAddress patientAddress) {
		// TODO Auto-generated method stub
		this.patientAddressDao.save(patientAddress);
	}

	@Override
	public void delete(PatientAddress patientAddress) {
		// TODO Auto-generated method stub
		this.patientAddressDao.delete(patientAddress);
	}

	@Override
	public PatientAddress getPatientAddressById(int id) {
		// TODO Auto-generated method stub
		return this.patientAddressDao.getPatientAddressById(id);
	}

	@Override
	public void update(PatientAddress patientAddress) {
		this.patientAddressDao.update(patientAddress);		
	}

	@Override
	public List<PatientAddress> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return patientAddressDao.fetchAll(patient);
	}



	
	
	
}
