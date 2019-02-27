package org.calminfotech.system.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "unitofmeasure")
public class GlobalUnitofMeasure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	// full
	@Column(name = "name", nullable = false)
	private String name;
	// short cut
	@Column(name = "code", nullable = false)
	private String code;

	

	@ManyToMany
	@JoinTable(name = "globalitem_unitofmeasure", 
	joinColumns = { @JoinColumn(name = "unitofmeasure_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "globalitem_id") })
	
	
	private List<GlobalItem> item;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public Integer getId() {
		return id;
	}


	public void setId(Integer globalUnitofMeasure) {
		this.id = globalUnitofMeasure;
	}
	public List<GlobalItem> getItem() {
		return item;
	}

	public void setItem(List<GlobalItem> item) {
		this.item = item;
	}
	
	
}
