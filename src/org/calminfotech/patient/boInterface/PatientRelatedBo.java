package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.models.PatientRelated;
import org.calminfotech.system.models.Organisation;


public interface PatientRelatedBo {

	

	public void save(PatientRelated patientRelated);

	public void delete(PatientRelated patientRelated);
	
	public void update(PatientRelated patientRelated);

	public PatientRelated getRelPatientById(int id);

	public List<PatientRelated> fetchAll(Patient patient);

	List<PatientRelated> fetchAll(Organisation organisation);
	
}
