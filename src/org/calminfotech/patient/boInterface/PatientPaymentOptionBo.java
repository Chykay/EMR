package org.calminfotech.patient.boInterface;

import org.calminfotech.patient.models.PatientPaymentOption;



public interface PatientPaymentOptionBo {
	
	public void save(PatientPaymentOption patientPaymentOption);

	public PatientPaymentOption getPatientPaySchemeById(int id);

	public void delete(PatientPaymentOption patientPaymentOption);

}
