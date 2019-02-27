package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;

import org.calminfotech.patient.models.PatientPaymentOption;
import org.calminfotech.patient.models.Patient;

public interface PatientPaymentOptionDao {
	
	public List<PatientPaymentOption> fetchAll();

	public List<PatientPaymentOption> fetchAllByOrgainsation(
			Organisation organisation);

	public List<PatientPaymentOption> fetchAllByPatient(Patient patient);

	public void save(PatientPaymentOption patientPaymentOption);

	public PatientPaymentOption getPatientPaySchemeById(int id);

	public void delete(PatientPaymentOption patientPaymentOption);

}
