package org.calminfotech.visitqueue.forms;

import org.hibernate.validator.constraints.Range;

public class VisitWorkflowUserConfigurationForm {

	private Integer id;

	private Integer bid;
	
	@Range(min = 1, message = "Select a visit")
	private Integer visitId;

	@Range(min = 1, message = "Select a point")
	private Integer workflowPointId;

	@Range(min = 1, message = "Select a Staff")
	private Integer userId;
	
	@Range(min = 1, message = "Select a Section")
	private Integer section;
	
	@Range(min = 1, message = "Select a dept")
	private Integer departmentId;
	
	@Range(min = 1, message = "Select a unit")
	
	private Integer unitId;

	private String comment;
	
	private String donedate;
	
	private String dischargeddate;
	
	private String end_date;
	private String given_date;
	
	private String dispensedate;
	
	private String manufacturedate;
	
	private String expirydate;
	

	private String dat1;
	
	
	private String dat2;
	
	private Integer parentCategoryId;
	private String otherinfo;
	private String manulicno;
	private String batchno;
	private String patientName;
private Integer patientId;
	private Integer statusId;
	
	private Boolean usehmo;
	

	private Integer billtoId;
	
	

	private String duedate;

	private Integer qty;
	

	private Integer globaitemid;
	

	private Integer itemmeasureid;
	
	private Integer referenceid;
	
	
	

	private Double amt;
	
	
	
	
	public String getGiven_date() {
		return given_date;
	}

	public void setGiven_date(String given_date) {
		this.given_date = given_date;
	}

	public Integer getBilltoId() {
		return billtoId;
	}

	public void setBilltoId(Integer billtoId) {
		this.billtoId = billtoId;
	}



	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public Boolean getUsehmo() {
		return usehmo;
	}

	public void setUsehmo(Boolean usehmo) {
		this.usehmo = usehmo;
	}

	public String getDispensedate() {
		return dispensedate;
	}

	public void setDispensedate(String dispensedate) {
		this.dispensedate = dispensedate;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public String getOtherinfo() {
		return otherinfo;
	}

	public void setOtherinfo(String otherinfo) {
		this.otherinfo = otherinfo;
	}

	public String getManulicno() {
		return manulicno;
	}

	public void setManulicno(String manulicno) {
		this.manulicno = manulicno;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public Integer getReferenceid() {
		return referenceid;
	}

	public void setReferenceid(Integer referenceid) {
		this.referenceid = referenceid;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	

	public Integer getGlobaitemid() {
		return globaitemid;
	}

	public void setGlobaitemid(Integer globaitemid) {
		this.globaitemid = globaitemid;
	}

	public Integer getItemmeasureid() {
		return itemmeasureid;
	}

	public void setItemmeasureid(Integer itemmeasureid) {
		this.itemmeasureid = itemmeasureid;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getDischargeddate() {
		return dischargeddate;
	}

	public void setDischargeddate(String dischargeddate) {
		this.dischargeddate = dischargeddate;
	}

	public String getDonedate() {
		return donedate;
	}

	public void setDonedate(String donedate) {
		this.donedate = donedate;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the visitId
	 */
	public Integer getVisitId() {
		return visitId;
	}

	/**
	 * @param visitId
	 *            the visitId to set
	 */
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	/**
	 * @return the workflowPointId
	 */
	public Integer getWorkflowPointId() {
		return workflowPointId;
	}

	/**
	 * @param workflowPointId
	 *            the workflowPointId to set
	 */
	public void setWorkflowPointId(Integer workflowPointId) {
		this.workflowPointId = workflowPointId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManufacturedate() {
		return manufacturedate;
	}

	public void setManufacturedate(String manufacturedate) {
		this.manufacturedate = manufacturedate;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getDat1() {
		return dat1;
	}

	public void setDat1(String dat1) {
		this.dat1 = dat1;
	}

	public String getDat2() {
		return dat2;
	}

	public void setDat2(String dat2) {
		this.dat2 = dat2;
	}
	

}
