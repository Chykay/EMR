package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotBlank;

public class OrganisationSettingForm {

	@NotBlank(message = "Field cannot be left empty!")
	private String name;

	@NotBlank(message = "Field cannot be left empty!")
	private String address;

	@NotBlank(message = "Field cannot be left empty!")
	private String systemEmail;
	
	private String consultationCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
 
	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

	public String getConsultationCode() {
		return consultationCode;
	}

	public void setConsultationCode(String consultationCode) {
		this.consultationCode = consultationCode;
	}
}
