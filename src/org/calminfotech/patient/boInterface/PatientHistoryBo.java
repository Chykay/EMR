package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHistory;



public interface PatientHistoryBo {

	

	public void save(PatientHistory patientHistory);

	public void delete(PatientHistory patientHistory);
	
	public void update(PatientHistory patientHistory);

	public PatientHistory getPatientHistoryById(int id);

	public List<PatientHistory> fetchAll(Patient patient);
	
}
