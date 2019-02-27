package org.calminfotech.patient.boImpl;


import org.calminfotech.patient.boInterface.PatientPaymentOptionBo;
import org.calminfotech.patient.daoInterface.PatientPaymentOptionDao;
import org.calminfotech.patient.models.PatientPaymentOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class PatientPaymentOptionBoImpl implements PatientPaymentOptionBo{
	
	@Autowired
	private PatientPaymentOptionDao patientPaymentOptionDao;

	
	@Override
	public void save(PatientPaymentOption patientPaymentOption) {
		// TODO Auto-generated method stub
		this.patientPaymentOptionDao.save(patientPaymentOption);	
	}

	@Override
	public PatientPaymentOption getPatientPaySchemeById(int id) {
		// TODO Auto-generated method stub
	
		return this.patientPaymentOptionDao.getPatientPaySchemeById(id);
	}

	@Override
	public void delete(PatientPaymentOption patientPaymentOption) {
		// TODO Auto-generated method stub
		this.patientPaymentOptionDao.delete(patientPaymentOption);	
	}

}
