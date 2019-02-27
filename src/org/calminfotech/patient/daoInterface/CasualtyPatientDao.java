package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.patient.models.CasPatient;

public interface CasualtyPatientDao {
	
	
	public List<CasPatient> fetchAll();

	public List<CasPatient> fetchAllByOrganisation(Organisation organisation);

	public CasPatient getCasPatientById(int id);

	public void save(CasPatient casPatient);

	public void delete(CasPatient casPatient);

	public void update(CasPatient casPatient);


}
