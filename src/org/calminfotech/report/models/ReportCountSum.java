package org.calminfotech.report.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class ReportCountSum {

	/*
	 * @Id
	 * 
	 * @GeneratedValue private String id;
	 */
	@Id
	@Column(name = "countvalue")
	private Integer countvalue;

	@Column(name = "sumvalue")
	private Double sumvalue;

	@Column(name = "sumpay")
	private Double sumpay;

	@Column(name = "sumcash")
	private Double sumcash;

	@Column(name = "sumpos")
	private Double sumpos;

	@Column(name = "sumtransfer")
	private Double sumtransfer;

	@Column(name = "sumdeposit")
	private Double sumdeposit;

	public Double getSumcash() {
		return sumcash;
	}

	public void setSumcash(Double sumcash) {
		this.sumcash = sumcash;
	}

	public Double getSumpos() {
		return sumpos;
	}

	public void setSumpos(Double sumpos) {
		this.sumpos = sumpos;
	}

	public Double getSumtransfer() {
		return sumtransfer;
	}

	public void setSumtransfer(Double sumtransfer) {
		this.sumtransfer = sumtransfer;
	}

	public Double getSumdeposit() {
		return sumdeposit;
	}

	public void setSumdeposit(Double sumdeposit) {
		this.sumdeposit = sumdeposit;
	}

	public Double getSumpay() {
		return sumpay;
	}

	public void setSumpay(Double sumpay) {
		this.sumpay = sumpay;
	}

	public void setSumvalue(Double sumvalue) {
		this.sumvalue = sumvalue;
	}

	public Integer getCountvalue() {
		return countvalue;
	}

	public void setCountvalue(Integer countvalue) {
		this.countvalue = countvalue;
	}

	public Double getSumvalue() {
		return sumvalue;
	}

	public void setSumvalue(double sumvalue) {
		this.sumvalue = sumvalue;
	}

}
