package org.calminfotech.patient.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Organisation;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;

public interface PatientAllergyDao {
	
	

	public List<PatientAllergy> fetchAll(Patient patient);

	public void save(PatientAllergy patientAllergy);

	public PatientAllergy getPatientAllergyById(int id);

	public void delete(PatientAllergy patientAllergy);
	
	public void update(PatientAllergy patientAllergy);
	

}
