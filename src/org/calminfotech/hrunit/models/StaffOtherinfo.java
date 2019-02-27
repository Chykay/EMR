package org.calminfotech.hrunit.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.calminfotech.hrunit.models.Staffgroupranking;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "staffgroup")
@SQLDelete(sql = "UPDATE staffgroup SET is_deleted = 1 WHERE id = ?")
//@Where(clause = "is_deleted <> 1")
public class StaffOtherinfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	
	@Column(name = "fromdate")
	private String fromdate;
	
	
	

	@Column(name = "is_deleted")
	private boolean isdeleted;

	


	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Set<StaffRegistration> staffreg;

	


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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