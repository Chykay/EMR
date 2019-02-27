package org.calminfotech.user.forms;

import org.hibernate.validator.constraints.NotBlank;

public class ForgotPasswordForm {

	@NotBlank(message = "Field cannot be empty!")
	public String email;

	@NotBlank(message = "Field cannot be empty!")
	private String orgId;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
