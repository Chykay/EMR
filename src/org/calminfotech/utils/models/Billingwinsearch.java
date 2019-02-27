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
@Table(name = "vw_billitem_list")
// @Where(clause = "is_deleted <> 1")
public class Billingwinsearch {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "globalitemtype_id")
	private int globalitemtypeId;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGlobalitemtypeId() {
		return globalitemtypeId;
	}

	public void setGlobalitemtypeId(int globalitemtypeId) {
		this.globalitemtypeId = globalitemtypeId;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

}
