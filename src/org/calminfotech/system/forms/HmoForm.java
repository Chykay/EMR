package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class HmoForm {

	private Integer id;

	// private String systemId;

	@NotBlank(message = "Field cannot be empty!")
	private String name;

	@NotBlank(message = "Field cannot be empty!")
	private String address;
	
	@Range(message = "Please select a LGA")
	private Integer lgaId;

	@Range(message = "Please select a State")
	private Integer stateId;

	@NotBlank(message = "Field cannot be empty!")
	private String phoneNumber;

	@NotBlank(message = "Field cannot be empty!")
	private String postalNumber;

	@NotBlank(message = "Field cannot be empty!")
	private String email;

	@Range(message = "Please select a Bank")
	private Integer bankId;

	@NotBlank(message = "Field cannot be empty!")
	private String bankAccount;

	@NotBlank(message = "Field cannot be empty!")
	private String adminName;

	@NotBlank(message = "Field cannot be empty!")
	private String adminEmail;

	@NotBlank(message = "Field cannot be empty!")
	private String adminPhone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*
	 * public String getSystemId() { return systemId; }
	 * 
	 * public void setSystemId(String systemId) { this.systemId = systemId; }
	 */

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

	public Integer getLgaId() {
		return lgaId;
	}

	public void setLgaId(Integer lgaId) {
		this.lgaId = lgaId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalNumber() {
		return postalNumber;
	}

	public void setPostalNumber(String postalNumber) {
		this.postalNumber = postalNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

}
