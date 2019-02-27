package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAddress;


public interface PatientAddressBo {

	

	public void save(PatientAddress patientAddress);

	public void delete(PatientAddress patientAddress);
	
	public void update(PatientAddress patientAddress);

	public PatientAddress getPatientAddressById(int id);

	public List<PatientAddress> fetchAll(Patient patient);
	
}
