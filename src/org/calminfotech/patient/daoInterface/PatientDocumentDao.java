package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.system.models.Organisation;

public interface PatientDocumentDao {

	public List<PatientDocument> fetchAll();

	public List<PatientDocument> fetchAllByOrgainsation(
			Organisation organisation);

	public List<PatientDocument> fetchAllByPatient(Patient patient);

	public void update(PatientDocument patientDocument);

	public PatientDocument getPatientDocumentById(int id);

	public void delete(PatientDocument patientDocument);

	public void save(PatientDocument patientDocument);

}
