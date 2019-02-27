package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientTelephone;


public interface PatientTelephoneBo {

	

	public void save(PatientTelephone patientTelephone);

	public void delete(PatientTelephone patientTelephone);
	
	public void update(PatientTelephone patientTelephone);

	public PatientTelephone getPatientTelephoneById(int id);

	public List<PatientTelephone> fetchAll(Patient patient);
	
}
