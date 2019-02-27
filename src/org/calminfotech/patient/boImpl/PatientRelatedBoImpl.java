package org.calminfotech.patient.boImpl;



import java.util.List;

import org.calminfotech.patient.boInterface.PatientRelatedBo;
import org.calminfotech.patient.daoInterface.PatientHistoryDao;
import org.calminfotech.patient.daoInterface.PatientRelatedDao;
import org.calminfotech.patient.models.Patient;

import org.calminfotech.patient.models.PatientRelated;
import org.calminfotech.system.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
	public class PatientRelatedBoImpl implements PatientRelatedBo{

	
	@Autowired
	private PatientRelatedDao patientRelatedDao;

	@Override
	public void save(PatientRelated patientRelated) {
		// TODO Auto-generated method stub
		this.patientRelatedDao.save(patientRelated);
	}

	@Override
	public void delete(PatientRelated patientRelated) {
		// TODO Auto-generated method stub
		this.patientRelatedDao.delete(patientRelated);
	}

	@Override
	public PatientRelated getRelPatientById(int id) {
		// TODO Auto-generated method stub
		return this.patientRelatedDao.getRelPatientById(id);
	}

	@Override
	public void update(PatientRelated patientRelated) {
		this.patientRelatedDao.update(patientRelated);		
	}

	@Override
	public List<PatientRelated> fetchAll(Organisation organisation) {
		// TODO Auto-generated method stub
		return patientRelatedDao.fetchAllByOrganisation(organisation);
	}


	@Override
	public List<PatientRelated> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}



	
	
	
}
