package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GL_post_code")
public class PostCode {
	
	@Id
	@Column(name="code")
	private String code;
	
	@Column(name="description")
	private String description;
	
	@Column(name="post_type_id")
	private Integer postTypeID;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPostTypeID() {
		return postTypeID;
	}

	public void setPostTypeID(Integer post_type_id) {
		this.postTypeID = post_type_id;
	}
	
	
}
