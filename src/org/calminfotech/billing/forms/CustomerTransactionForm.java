package org.calminfotech.billing.forms;

/**
 * @author adeoye
 * 
 */
public class CustomerTransactionForm {

	private Integer id;

	private String effectivedate;

	private Double amount;

	private String description;
	private String drcr;
	private Integer patientId;
	private Integer paymode_id;
	private String PatientName;
	private Integer PatientId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEffectivedate() {
		return effectivedate;
	}

	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}

	public String getPatientName() {
		return PatientName;
	}

	public void setPatientName(String patientName) {
		PatientName = patientName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDrcr() {
		return drcr;
	}

	public void setDrcr(String drcr) {
		this.drcr = drcr;
	}

	public Integer getPaymode_id() {
		return paymode_id;
	}

	public void setPaymode_id(Integer paymode_id) {
		this.paymode_id = paymode_id;
	}

}
