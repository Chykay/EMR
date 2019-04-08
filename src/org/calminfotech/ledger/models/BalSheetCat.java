package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@SQLDelete(sql = "UPDATE GL_balance_sheet_ledger SET isActive = 0 WHERE id = ?")
@Table(name = "GL_balance_sheet_ledger")
public class BalSheetCat extends CommonLedger{
	@Id
	@GeneratedValue
	@Column(name ="id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "parent_id")
	private int parentID;
	
	@Column(name = "isActive")
	private boolean isActive;
	
	@Transient
	private String parentName;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parent_name) {
		this.parentName = parent_name;
	}
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	

}
