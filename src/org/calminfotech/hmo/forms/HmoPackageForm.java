package org.calminfotech.hmo.forms;

public class HmoPackageForm {

	private Integer id;

	//@NotBlank(message = "Field cannot be empty!")
	private String name;

	private Integer hmo_id;
	
	private Integer status_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHmo_id() {
		return hmo_id;
	}

	public void setHmo_id(Integer hmo_id) {
		this.hmo_id = hmo_id;
	}

	public Integer getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}
	
	
	
		
}
