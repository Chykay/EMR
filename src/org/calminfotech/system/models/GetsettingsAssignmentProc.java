package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(
	name = "spGetSettingsProc",
	query = "{CALL sp_settings_assignment(:orgid)}",
	callable = true,
	resultClass = GetsettingsAssignmentProc.class
)

@org.hibernate.annotations.Entity(dynamicInsert = true)
public class GetsettingsAssignmentProc implements java.io.Serializable {
	
	private static final long serialVersionUID = -9160881819374280018L;

	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	*/
	@Id
	@Column(name = "settings_code",unique = false, nullable = true)
	private String settings_code;
	
	@Column(name = "name",unique = false, nullable = true)
	private String name;
	

	@Column(name = "category")
	private String category;
	
	@Column(name = "searchtype",unique = false, nullable = true)
	private String searchtype;

	
	@Column(name = "settings_value", nullable = true)
	private String settings_value;

	public void setSettings_code(String settings_code) {
		this.settings_code = settings_code;
	}




	public String getSettings_value() {
		return settings_value;
	}




	public void setSettings_value(String settings_value) {
		this.settings_value = settings_value;
	}




	public String getCategory() {
		return category;
	}




	public void setCategory(String category) {
		this.category = category;
	}




	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSearchtype() {
		return searchtype;
	}



	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}



	public String getSettings_code() {
		return settings_code;
	}



	


}