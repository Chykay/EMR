package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "GL_totaling_code", uniqueConstraints = @UniqueConstraint(columnNames={"code", "company_id"}))
public class TotalingCode extends CommonLedger{
	@Id
	@GeneratedValue
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	

	@ManyToOne
	@JoinColumn(name = "ledger_type")
	private LedgerType ledgerType;
	 
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Transient
	public boolean isEditable;

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

	public LedgerType getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(LedgerType ledgerType) {
		this.ledgerType = ledgerType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

/*	public boolean getIsEditable() {
		return isEditable;
	

	}*/

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

}
