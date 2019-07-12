package org.calminfotech.utils.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "banks")
//@Where(clause = "is_deleted <> 1")
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_id", unique = true, nullable = false)
	private Integer bank_id;

	@Column(name = "bank_code")
	private String bankCode;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "modify_date", nullable = true)
	private Date modifyDate;
	
	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	public Integer getBank_id() {
		return bank_id;
	}

	public void setBank_id(Integer bank_id) {
		this.bank_id = bank_id;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	
	
}
