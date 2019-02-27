package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;


public interface PatientAllergyBo {

	

	public void save(PatientAllergy patientAllergy);

	public void delete(PatientAllergy patientAllergy);
	
	public void update(PatientAllergy patientAllergy);

	public PatientAllergy getPatientAllergyById(int id);

	public List<PatientAllergy> fetchAll(Patient patient);
	
}
