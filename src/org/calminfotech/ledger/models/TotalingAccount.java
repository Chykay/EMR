package org.calminfotech.ledger.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
//@SQLDelete(sql = "UPDATE TotalingAccount SET is_deleted = 1 WHERE id = ?")
@Table(name = "GL_totaling_ledger")
public class TotalingAccount extends CommonLedger{
	@Id
	@GeneratedValue
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	

	@ManyToOne
	@JoinColumn(name = "ledger_type")
	private LedgerType ledger_type;
	 
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "is_active")
	private boolean is_active;

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

	public LedgerType getLedger_type() {
		return ledger_type;
	}

	public void setLedger_type(LedgerType ledger_type) {
		this.ledger_type = ledger_type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	

}
