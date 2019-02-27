package org.calminfotech.patient.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Organisation;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientTelephone;

public interface PatientTelephoneDao {
	
	

	public List<PatientTelephone> fetchAll(Patient patient);

	public void save(PatientTelephone patientTelephone);

	public PatientTelephone getPatientTelephoneById(int id);

	public void delete(PatientTelephone patientTelephone);
	
	public void update(PatientTelephone patientTelephone);
	

}
