package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
//@SQLDelete(sql = "UPDATE TotalingAccount SET is_deleted = 1 WHERE id = ?")
@Table(name = "GL_balance_sheet_ledger")
public class BalSheetCat extends CommonLedger{
	@Id
	@GeneratedValue
	@Column(name ="id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "parent_id")
	private int parent_id;
	
	@Column(name = "is_active")
	private boolean is_active;
	
	@Transient
	private String parent_name;

	

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

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	

}
