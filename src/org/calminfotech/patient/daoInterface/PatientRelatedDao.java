package org.calminfotech.patient.daoInterface;

import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientRelated;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface PatientRelatedDao {
	
	public List<PatientRelated> fetchAll(Organisation organisation);

	public List<PatientRelated> fetchAllByOrganisation(Organisation organisation);
	
	
	public PatientRelated getRelPatientById(int id);
	

	public void save(PatientRelated relPatient);

	public void delete(PatientRelated relPatient);

	public void update(PatientRelated relPatient);
	
	

	public PatientRelated checkIfPatientIdExist(String patientId);

	public PatientRelated getPatientRelatedByPatientId(Patient patient);

	

	

}
