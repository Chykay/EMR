package org.calminfotech.patient.daoInterface;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.calminfotech.patient.forms.PatientSearchForm;

public interface PatientSearchDao {
	
	public List searchPatient(PatientSearchForm patientForm, HttpSession session);

}
