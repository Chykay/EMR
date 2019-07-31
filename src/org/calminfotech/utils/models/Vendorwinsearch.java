package org.calminfotech.utils.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "vw_vendor_list")
// @Where(clause = "is_deleted <> 1")
public class Vendorwinsearch {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

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

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

}
