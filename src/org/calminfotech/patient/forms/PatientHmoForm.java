package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.Range;
import java.util.*;
import java.util.Date;

public class PatientHmoForm {
	private Integer id;
	@Range(min = 1, message = "select a patient")
	private Integer patientId;

	//@Range(min = 1, message = "Select an HMO package")
	//private Integer packageId;
	private Integer packageId;


	private String fromdat;

	public String getFromdat() {
		return fromdat;
	}

	public void setFromdat(String fromdat) {
		this.fromdat = fromdat;
	}

	public String getTodat() {
		return todat;
	}

	public void setTodat(String todat) {
		this.todat = todat;
	}

	private String todat;
	
	private Integer hmostatusId;
	

	

	public Integer getHmostatusId() {
		return hmostatusId;
	}

	public void setHmostatusId(Integer hmostatusId) {
		this.hmostatusId = hmostatusId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	


	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}


}
