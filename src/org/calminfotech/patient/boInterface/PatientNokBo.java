package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientNok;


public interface PatientNokBo {

	

	public void save(PatientNok patientNok);

	public void delete(PatientNok patientNok);
	
	public void update(PatientNok patientNok);

	public PatientNok getPatientNokById(int id);

	public List<PatientNok> fetchAll(Patient patient);
	
}
