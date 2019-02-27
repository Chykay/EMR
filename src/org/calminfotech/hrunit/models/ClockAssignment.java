package org.calminfotech.hrunit.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.models.UserAssignment;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserProfile;
import org.hibernate.annotations.NamedNativeQuery;


@Entity
@NamedNativeQuery(
	name = "spGetDeleteClockingCheckedVal",
	query = "{CALL sp_delete_clocking_checked_values(:userid)}",
	callable = true,
	resultClass = ClockAssignment.class
)
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hrunit_clockin")
public class ClockAssignment implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	
//	@Column(name = "unit_id")
//	private int unitId;
	
	@ManyToOne
	@JoinColumn(name = "unit_id")
	HrunitCategory hrunit;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserProfile userprofile;
		
	@Column(name = "email")
	private String userName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "clock_in_time")
	private Date clockInTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "clock_out_time")
	private Date clockOutTime;
	
	@Column(name = "organisation_id")
	private int organisationId ;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expected_clock_out_time")
	private Date expClockOutTime ;
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	

	public HrunitCategory getHrunit() {
		return hrunit;
	}

	public void setHrunit(HrunitCategory hrunit) {
		this.hrunit = hrunit;
	}

	public Date getClockInTime() {
		return clockInTime;
	}

	public void setClockInTime(Date clockInTime) {
		this.clockInTime = clockInTime;
	}

	public Date getClockOutTime() {
		return clockOutTime;
	}

	public void setClockOutTime(Date clockOutTime) {
		this.clockOutTime = clockOutTime;
	}

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	public UserProfile getUserprofile() {
		return userprofile;
	}

	public void setUserprofile(UserProfile userprofile) {
		this.userprofile = userprofile;
	}

	public Date getExpClockOutTime() {
		return expClockOutTime;
	}

	public void setExpClockOutTime(Date expClockOutTime) {
		this.expClockOutTime = expClockOutTime;
	}

}
