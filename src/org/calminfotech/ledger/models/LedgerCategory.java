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
@SQLDelete(sql = "UPDATE GL_ledger_category SET isActive = 0 WHERE id = ?")
@Table(name = "GL_ledger_category")
public class LedgerCategory extends CommonLedger{
	@Id
	@GeneratedValue
	@Column(name ="id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "parent_id")
	private int parentID;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	/*@Column(name = "ledger_type")
	private Integer ledgerType;*/
	
	// column is_parent that gets updated to 1 when it is selected as a parent
	
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

	/*public Integer getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(Integer ledgerType) {
		this.ledgerType = ledgerType;
	}*/

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	

}
