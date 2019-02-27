package org.calminfotech.system.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.models.Group;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "resources")
public class Resource {

	private Integer id;
	private String urlPattern;
	private String description;
	private Organisation organisation;
	private Set<Group> groups = new HashSet<Group>();
	private Action action;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "url_pattern")
	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPath) {
		this.urlPattern = urlPath;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "resources_permissions", 
	joinColumns = { @JoinColumn(name = "resource_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "group_id") })
	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}


	@Enumerated(EnumType.STRING)
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	

}
