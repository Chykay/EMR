package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;

public interface PatientDao {

	public List<Patient> fetchAll(int start);

	// public List<Patient> fetchAllByOrganisation(Organisation organisation);

	public List<Patient> fetchTop50ByOrganisation(int organisationId);

	public List<Patient> fetchAllByOrganisation(int organisationId);

	public Patient getPatientById(int id);

	public void save(Patient patient);

	public void delete(Patient patient);

	public void update(Patient patient);

	Patient findByBirthDay(String subscriberDob);

	Patient checkIfPatientIdExist(String PatientId);

	public List<Patient> fetchByOrganisationrec(int organisationId);

	public List<Patient> fetchByOrganisationrecbypatient(int pid);

}
