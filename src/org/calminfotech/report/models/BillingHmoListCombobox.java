package org.calminfotech.report.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class BillingHmoListCombobox {

	@Id
	@Column(name = "id")
	private Integer hmoid;

	@Column(name = "name")
	private String hmoname;

	public Integer getHmoid() {
		return hmoid;
	}

	public void setHmoid(Integer hmoid) {
		this.hmoid = hmoid;
	}

	public String getHmoname() {
		return hmoname;
	}

	public void setHmoname(String hmoname) {
		this.hmoname = hmoname;
	}

}
