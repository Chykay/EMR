package org.calminfotech.patient.daoInterface;

import java.util.List;
import org.calminfotech.patient.models.PatientHmo;

public interface PatientHmoDao {

public List<PatientHmo> fetchAll();

public PatientHmo getPatientHmoById(int id);

	public void save(PatientHmo patientHmo);
	
//	public void save(PatientHmoPackage hmoPackage);

	public void delete(PatientHmo patientHmo);

	public void update(PatientHmo patientHmo);

}
