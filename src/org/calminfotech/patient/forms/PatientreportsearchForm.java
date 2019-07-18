package org.calminfotech.patient.forms;

public class PatientreportsearchForm {

	private Integer id;

	private String datefrom;

	private String dateto;

	private String da1;

	private String dat2;

	private Integer hmoid;

	private Integer status;

	private Integer staffunit_id;

	private String hmoname;

	private Integer patientId;

	private String patientName;

	private Integer unitId;
	private Integer personnelid;

	private String personnelname;

	public Integer getPersonnelid() {
		return personnelid;
	}

	public void setPersonnelid(Integer personnelid) {
		this.personnelid = personnelid;
	}

	public String getPersonnelname() {
		return personnelname;
	}

	public void setPersonnelname(String personnelname) {
		this.personnelname = personnelname;
	}

	public Integer getStaffunit_id() {
		return staffunit_id;
	}

	public void setStaffunit_id(Integer staffunit_id) {
		this.staffunit_id = staffunit_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}

	public String getDateto() {
		return dateto;
	}

	public void setDateto(String dateto) {
		this.dateto = dateto;
	}

	public Integer getHmoid() {
		return hmoid;
	}

	public void setHmoid(Integer hmoid) {
		this.hmoid = hmoid;
	}

	public String getHmoname() {
		return hmoname;
	}

	public void setHmoname(String hmoname) {
		this.hmoname = hmoname;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDa1() {
		return da1;
	}

	public void setDa1(String da1) {
		this.da1 = da1;
	}

	public String getDat2() {
		return dat2;
	}

	public void setDat2(String dat2) {
		this.dat2 = dat2;
	}

}
