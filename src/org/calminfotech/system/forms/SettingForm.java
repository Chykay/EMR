package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotBlank;

public class SettingForm {

	@NotBlank(message = "Field cannot be left empty!")
	private String organisationName;

	@NotBlank(message = "Field cannot be left empty!")
	private String organisationAddress;

	@NotBlank(message = "Field cannot be left empty!")
	private String organisationEmail;

	@NotBlank(message = "Field cannot be left empty!")
	private String systemEmail;

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getOrganisationAddress() {
		return organisationAddress;
	}

	public void setOrganisationAddress(String organisationAddress) {
		this.organisationAddress = organisationAddress;
	}

	public String getOrganisationEmail() {
		return organisationEmail;
	}

	public void setOrganisationEmail(String organisationEmail) {
		this.organisationEmail = organisationEmail;
	}

	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

}
