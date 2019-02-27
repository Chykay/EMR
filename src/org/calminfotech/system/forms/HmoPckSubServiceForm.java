package org.calminfotech.system.forms;

import java.util.Date;

public class HmoPckSubServiceForm {
	
	private Integer id;
	private String subserviceName;
	private Integer hmoPckService;
	private Date createdDate;
	
	//Getters and Setters
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubserviceName() {
		return subserviceName;
	}
	public void setSubserviceName(String subserviceName) {
		this.subserviceName = subserviceName;
	}
	public Integer getHmoPckService() {
		return hmoPckService;
	}
	public void setHmoPckService(Integer hmoPckService) {
		this.hmoPckService = hmoPckService;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
