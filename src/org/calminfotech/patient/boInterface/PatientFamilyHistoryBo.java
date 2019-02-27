package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientFamilyHistory;


public interface PatientFamilyHistoryBo {

	

	public void save(PatientFamilyHistory patientFamilyHistory);

	public void delete(PatientFamilyHistory patientFamilyHistory);
	
	public void update(PatientFamilyHistory patientFamilyHistory);

	public PatientFamilyHistory getPatientFamilyHistoryById(int id);

	public List<PatientFamilyHistory> fetchAll(Patient patient);
	
}
