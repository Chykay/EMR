package org.calminfotech.hrunit.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(name = "spGetClockingUnitProc",
query = "{CALL sp_user_clocking(:userid,:orgid)}", 
callable = true, 
resultClass = GetClockingUnitProc.class)
@org.hibernate.annotations.Entity(dynamicInsert = true)
//@Table(name = "hrunit_category")
public class GetClockingUnitProc implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_id", unique = true, nullable = false)
	private Integer unitId;

	
	@Column(name = "category_name")
	private String unitName;
	
	@Column(name = "status", nullable = true)
	private boolean checkStatus;

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public boolean isCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(boolean checkStatus) {
		this.checkStatus = checkStatus;
	}
	
		
	
	

	

}
