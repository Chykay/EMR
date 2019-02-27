package org.calminfotech.visitqueue.forms;



public class AppointmentScheduleForm {
	
	
	private Integer id;
	
	private Integer consultationId;
	
	private Integer visitId;
	
	private String start_date2;

	private String end_date2;

	private String title;

	private String description;
	
	private Integer userId2;
	
	private Integer patientId2;
	
	private String patientName2;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(Integer consultationId) {
		this.consultationId = consultationId;
	}

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}



	public String getStart_date2() {
		return start_date2;
	}

	public void setStart_date2(String start_date2) {
		this.start_date2 = start_date2;
	}

	public String getEnd_date2() {
		return end_date2;
	}

	public void setEnd_date2(String end_date2) {
		this.end_date2 = end_date2;
	}

	public Integer getUserId2() {
		return userId2;
	}

	public void setUserId2(Integer userId2) {
		this.userId2 = userId2;
	}

	public Integer getPatientId2() {
		return patientId2;
	}

	public void setPatientId2(Integer patientId2) {
		this.patientId2 = patientId2;
	}

	public String getPatientName2() {
		return patientName2;
	}

	public void setPatientName2(String patientName2) {
		this.patientName2 = patientName2;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}
