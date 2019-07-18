package org.calminfotech.patient.boImpl;

import org.calminfotech.patient.boInterface.PatientDocumentBo;
import org.calminfotech.patient.daoInterface.PatientDocumentDao;
import org.calminfotech.patient.models.PatientDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientDocumentBoImpl implements PatientDocumentBo {

	@Autowired
	private PatientDocumentDao patientDocumentDao;

	@Override
	public void update(PatientDocument patientDocument) {
		// TODO Auto-generated method stub
		this.patientDocumentDao.update(patientDocument);
	}

	@Override
	public void save(PatientDocument patientDocument) {
		// TODO Auto-generated method stub
		this.patientDocumentDao.save(patientDocument);
	}

	@Override
	public void delete(PatientDocument patientDocument) {
		// TODO Auto-generated method stub
		this.patientDocumentDao.delete(patientDocument);
	}

	@Override
	public PatientDocument getPatientDocumentById(int id) {
		// TODO Auto-generated method stub
		return this.patientDocumentDao.getPatientDocumentById(id);
	}

}
