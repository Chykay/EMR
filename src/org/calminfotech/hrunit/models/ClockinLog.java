package org.calminfotech.hrunit.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hrunit_clockin_log")
public class ClockinLog {
	
	
		private Integer id;
		private String username;
		private Date clockTime;
		private String clockingType;
		private Integer clockingUnit;	
		private Integer userId;
		private Integer organisationId;
		
		
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "clocking_id", unique = true, nullable = false)
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		
		
		@Column(name = "username")
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		
		@Column(name = "clock_time")
		public Date getClockTime() {
			return clockTime;
		}
		public void setClockTime(Date clockTime) {
			this.clockTime = clockTime;
		}
		
		@Column(name = "clocking_type")
		public String getClockingType() {
			return clockingType;
		}
		public void setClockingType(String clockingType) {
			this.clockingType = clockingType;
		}
		
		
		@Column(name = "clocking_unit")
		public Integer getClockingUnit() {
			return clockingUnit;
		}
		public void setClockingUnit(Integer clockingUnit) {
			this.clockingUnit = clockingUnit;
		}
		
		@Column(name = "user_login_id")
		public Integer getUserId() {
			return userId;
		}
		
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		
		@Column(name = "organisation_id")
		public Integer getOrganisationId() {
			return organisationId;
		}
		public void setOrganisationId(Integer organisationId) {
			this.organisationId = organisationId;
		}
		
	
}
