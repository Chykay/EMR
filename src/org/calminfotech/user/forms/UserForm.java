package org.calminfotech.user.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class UserForm {

	private Integer userId;
	
	private Integer emailId;
	

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private Integer user_type_id ; 
	
	private String password; 
	
	private Boolean levelsecurity;
	
	private Integer organisation_id;
	
	
	
	
	
	public Integer getEmailId() {
		return emailId;
	}

	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}

	public Integer getOrganisation_id() {
		return organisation_id;
	}

	public void setOrganisation_id(Integer organisation_id) {
		this.organisation_id = organisation_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUser_type_id() {
		return user_type_id;
	}

	public void setUser_type_id(Integer user_type_id) {
		this.user_type_id = user_type_id;
	}

	@NotBlank(message = "Field cannot be empty!")
	@Email(message = "Invalid email format!")
	private String email;

	@NotBlank(message = "Field cannot be empty!")
	private String lastName;

	
	private String otherNames;
	
	

	private Integer role_id;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Boolean getLevelsecurity() {
		return levelsecurity;
	}

	public void setLevelsecurity(Boolean levelsecurity) {
		this.levelsecurity = levelsecurity;
	}

	

	
}
