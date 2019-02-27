package org.calminfotech.patient.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Organisation;


import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHistory;

public interface PatientHistoryDao {
	
	

	public List<PatientHistory> fetchAll(Patient patient);

	public void save(PatientHistory patientHistory);

	public PatientHistory getPatientHistoryById(int id);

	public void delete(PatientHistory patientHistory);
	
	public void update(PatientHistory patientHistory);
	

}
