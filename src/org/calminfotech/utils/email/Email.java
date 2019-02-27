package org.calminfotech.utils.email;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "mailqueue")
public class Email {

	private Integer id;
	private String sender;
	private String receiver;
	private String subject;
	private String message;
	private Integer status;
	private Date lastProcessDate;
	private Integer numberOfTrials;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Row_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "sender", nullable = false)
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Column(name = "receiver", nullable = false)
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "subject", nullable = false)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "message", nullable = false)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "lastprocessdate", nullable = false)
	public Date getLastProcessDate() {
		return lastProcessDate;
	}

	public void setLastProcessDate(Date lastProcessDate) {
		if (null == lastProcessDate) {
			this.lastProcessDate = new Date(System.currentTimeMillis());
		} else {
			this.lastProcessDate = lastProcessDate;
		}
	}

	@Column(name = "numbertrial", nullable = false)
	public Integer getNumberOfTrials() {
		return numberOfTrials;
	}

	public void setNumberOfTrials(Integer numberOfTrials) {
		this.numberOfTrials = numberOfTrials;
	}

}
