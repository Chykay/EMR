package org.calminfotech.patient.boInterface;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.calminfotech.patient.forms.PatientSearchForm;

public interface PatientSearchBo {
	
	public List searchPatient(PatientSearchForm patientForm, HttpSession session);

}
