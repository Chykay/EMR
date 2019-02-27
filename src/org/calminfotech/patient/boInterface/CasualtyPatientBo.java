package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.forms.CasualtyPatientForm;
import org.calminfotech.patient.models.CasPatient;


public interface CasualtyPatientBo {
	
	public List<CasPatient> fetchAll();

	public List<CasPatient> fetchAllByOrganisation();

	public CasPatient getcasPatientById(int id);

	public CasPatient save(CasualtyPatientForm casualtyPatientForm);

	public void delete(CasPatient casPatient);

	public void update(CasualtyPatientForm casualtyPatientForm);

	public void update(CasPatient casPatient);



}
