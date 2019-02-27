package org.calminfotech.hmo.forms;

//import org.hibernate.validator.constraints.NotBlank;
// org.hibernate.validator.constraints.Range;

public class HmoForm {

	private Integer id;
	private String name;
	private Integer organisationId;
	
	private String admin_name;
	
	private String address;
	
	private String phone;

	private Integer bank_id;
	
    private String accountno;
    
	private String email;
	
	private Integer billingscheme_id;
	
	private Integer status_id;
	
	public Integer getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	private String created_date;
	
	private Integer created_by;
	
	private String modified_date;
	
	private Integer modified_by;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}



	public String getAdmin_name() {
		return admin_name;
	}

	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	public Integer getBank_id() {
		return bank_id;
	}

	public void setBank_id(Integer bank_id) {
		this.bank_id = bank_id;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getBillingscheme_id() {
		return billingscheme_id;
	}

	public void setBillingscheme_id(Integer billingscheme_id) {
		this.billingscheme_id = billingscheme_id;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public String getModified_date() {
		return modified_date;
	}

	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}

	public Integer getModified_by() {
		return modified_by;
	}

	public void setModified_by(Integer modified_by) {
		this.modified_by = modified_by;
	}
	
	

	

}
