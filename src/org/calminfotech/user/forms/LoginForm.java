package org.calminfotech.user.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class LoginForm {

	@NotBlank(message = "Field cannot be empty!")
	@Email(message = "Invalid email format!")
	private String email;

	@NotBlank(message = "Field cannot be empty!")
	private String password;

	@NotBlank(message = "Field cannot be empty!")
	private String orgId;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
