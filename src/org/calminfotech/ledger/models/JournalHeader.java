package org.calminfotech.ledger.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
//@SQLDelete(sql = "UPDATE GL_balance_sheet_ledger SET is_active = 0 WHERE id = ?")
@Table(name = "GL_journal_header")
public class JournalHeader extends CommonLedger{
	@Id
	@GeneratedValue
	@Column(name ="id")
	private int id;
	
	@Column(name = "journal_id")
	private String journalID;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="tot_credit")
	private float totCredit;
	
	@Column(name="tot_debit")
	private float totDebit;

	@Column(name="status")
	private String status = "NOT POSTED";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJournalID() {
		return journalID;
	}

	public void setJournalID(String journalID) {
		this.journalID = journalID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getTotCredit() {
		return totCredit;
	}

	public void setTotCredit(float totCredit) {
		this.totCredit = totCredit;
	}

	public float getTotDebit() {
		return totDebit;
	}

	public void setTotDebit(float totDebit) {
		this.totDebit = totDebit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
