package org.calminfotech.patient.models;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.billing.models.BillSchemeItem;


@Embeddable
public class PatientPaymentOptionId implements java.io.Serializable{
	
	private Patient patient;
	
	private BillSchemeItem  paymentSchemeItem;	
	
	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	

	@ManyToOne
	public BillSchemeItem getPaymentSchemeItem() {
		return paymentSchemeItem;
	}

	public void setPaymentSchemeItem(BillSchemeItem paymentSchemeItem) {
		this.paymentSchemeItem = paymentSchemeItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((paymentSchemeItem == null) ? 0 : paymentSchemeItem.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientPaymentOptionId other = (PatientPaymentOptionId) obj;
		if (paymentSchemeItem == null) {
			if (other.paymentSchemeItem != null)
				return false;
		} else if (!paymentSchemeItem.equals(other.paymentSchemeItem))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		return true;
	}

}
