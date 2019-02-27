package org.calminfotech.utils.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "vw_staff_list")
//@Where(clause = "is_deleted <> 1")
public class Personnelwinsearch {

	@Id
	@Column(name = "id")
	private Integer id;
	
	
	@Column(name = "name")
	private String name;


	@Column(name = "organisation_id")
	private int  organisationId;



	


	public int getOrganisationId() {
		return organisationId;
	}


	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}


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
	
	
}
