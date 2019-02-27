package org.calminfotech.patient.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Organisation;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAddress;

public interface PatientAddressDao {
	
	

	public List<PatientAddress> fetchAll(Patient patient);

	public void save(PatientAddress patientAddress);

	public PatientAddress getPatientAddressById(int id);

	public void delete(PatientAddress patientAddress);
	
	public void update(PatientAddress patientAddress);
	

}
