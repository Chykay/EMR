package org.calminfotech.user.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Title;



@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "online_users")
public class OnlineUser {
	
	
	private int userId;
	private String username;
	private Organisation organisation;
	private UserType userType;
	private Group group;
	private Date clock_in_time;
	private String clock_out_time;
	
	
	private String last_name;
	private String other_names;
	private Title title;
	

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	@Column(name = "email", nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
	@ManyToOne
	@JoinColumn(name = "user_type_id")
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	
	@Column(name = "clock_in_time")
	public Date getClock_in_time() {
		return clock_in_time;
	}
	public void setClock_in_time(Date clock_in_time) {
		this.clock_in_time = clock_in_time;
	}
	
	
	@Column(name = "clock_out_time")
	public String getClock_out_time() {
		return clock_out_time;
	}
	public void setClock_out_time(String clock_out_time) {
		this.clock_out_time = clock_out_time;
	}
	
	
	
	@Column(name = "last_name")
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	@Column(name = "other_names")
	public String getOther_names() {
		return other_names;
	}
	public void setOther_names(String other_names) {
		this.other_names = other_names;
	}
	
	@ManyToOne
	@JoinColumn(name = "title_id")
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	


	
	

}
