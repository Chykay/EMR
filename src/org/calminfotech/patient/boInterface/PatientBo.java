package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientDocument;

public interface PatientBo {

	public List<Patient> fetchAll(int start);

	// public List<Patient> fetchAllByOrganisation();
	public List<Patient> fetchAllByOrganisation(int organisationId);

	public List<Patient> fetchTop50ByOrganisation(int organisationId);

	public Patient getPatientById(int id);

	public Patient save(PatientForm patientForm);

	// just added for transfer sake
	public Patient save(Patient patient);

	// ends

	public void delete(Patient patient);

	public void update(PatientForm patientForm);

	public void update(Patient patient);

	public List<PatientDocument> getPatientDocumentByPatient(Patient patient);

	public Patient findByBirthDay(String patientDob);

	public Patient checkIfPatientIdExist(String patientId);

	public List<Patient> fetchByOrganisationrec(int organisationId);

	public List<Patient> fetchByOrganisationrecbypatient(int pid);
}
