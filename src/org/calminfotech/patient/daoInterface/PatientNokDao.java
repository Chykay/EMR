package org.calminfotech.patient.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Organisation;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientNok;

public interface PatientNokDao {
	
	

	public List<PatientNok> fetchAll(Patient patient);

	public void save(PatientNok patientNok);

	public PatientNok getPatientNokById(int id);

	public void delete(PatientNok patientNok);
	
	public void update(PatientNok patientNok);
	

}
