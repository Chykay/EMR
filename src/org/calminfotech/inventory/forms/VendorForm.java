package org.calminfotech.inventory.forms;



import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class VendorForm {

	private int id;
	
    @Size(min = 0, max = 100, message = "Field mustless than 100 characters!")
	private String vendorId;

	@NotBlank(message = "Field cannot be empty!")
    @Size(min = 3, max = 100, message = "Vendor Name must be between 3 and 100 characters!")
	private String vendorName;
	

	@NotBlank(message = "Field cannot be empty!")
	@Email(message = "Invalid email format")
    @Size(min = 1, max = 100, message = "Email must between 1 and 100 characters!")
	private String email;
	
	@NotBlank(message = "Field cannot be empty!")
    @Size(min = 1, max = 100, message = "Field must between 1 and 100 characters!")
	private String telephoneNumber;

	@NotBlank(message = "Field cannot be empty!")
	private String contactAddress;
	
    @Size(min = 0, max = 100, message = "Field must be less than 100 characters!")
	private String url;
	
    @Size(min = 0, max = 100, message = "Field must be less than 100 characters!")
	private String fax;

	
	public int getId() {
		return id;
	}

	
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}

	
	
}

