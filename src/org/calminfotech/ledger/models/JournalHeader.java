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
	private String journal_id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJournal_id() {
		return journal_id;
	}

	public void setJournal_id(String journal_id) {
		this.journal_id = journal_id;
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
}
