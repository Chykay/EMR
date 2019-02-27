package org.calminfotech.hrunit.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "staffgroupspecialization")
@SQLDelete(sql = "UPDATE staffgroupspecialization SET is_deleted = 1 WHERE id = ?")
//@Where(clause = "is_deleted <> 1")
public class Staffgroupspecialization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	

	@Column(name = "name")
	private String name;
	
	@Column(name = "staffgroup_id")
	private int staffgroup_id;
	
	
	@Column(name = "is_deleted")
	private boolean isdeleted;

	



	@OneToMany
	@JoinColumn(name = "staffspecialization_id")
	private Set<StaffRegistration> staffregistration;
	





	public Set<StaffRegistration> getStaffregistration() {
		return staffregistration;
	}



	public void setStaffregistration(Set<StaffRegistration> staffregistration) {
		this.staffregistration = staffregistration;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	


	public int getStaffgroup_id() {
		return staffgroup_id;
	}



	public void setStaffgroup_id(int staffgroup_id) {
		this.staffgroup_id = staffgroup_id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	public boolean isDeleted() {
		return isDeleted();
	}



	public boolean isIsdeleted() {
		return isdeleted;
	}



	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}



	
	
}