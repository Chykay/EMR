package org.calminfotech.patient.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Organisation;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientFamilyHistory;

public interface PatientFamilyHistoryDao {
	
	

	public List<PatientFamilyHistory> fetchAll(Patient patient);

	public void save(PatientFamilyHistory patientFamilyHistory);

	public PatientFamilyHistory getPatientFamilyHistoryById(int id);

	public void delete(PatientFamilyHistory patientFamilyHistory);
	
	public void update(PatientFamilyHistory patientFamilyHistory);
	

}
