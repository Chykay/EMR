package org.calminfotech.user.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.calminfotech.system.models.Organisation;

import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;



@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class UserClockOut {
	
	
	
	private int userId;
	private String username;
	private String password;
	private boolean activation;
	private String createdBy;
	private Date createdDate;
	private Date modifiedDate;
	private boolean status;
	private boolean lock;
	private Group group;

	private UserType userType;
	private Set<UserActivation> userActivation = new HashSet<UserActivation>();
	private UserProfile userProfile;

	private Set<VisitWorkflowPoint> workflowPoints = new HashSet<VisitWorkflowPoint>();
	
	private String clock_in_time;
	private Date clock_out_time;
	
	private boolean clock_status;
	private String clocking_status;
	public enum pclocking_status {
		Clockin, Clockout
	}
	
	
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
	
	
	@Column(name = "password", nullable = true)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "activation", nullable = true)
	public boolean isActivation() {
		return activation;
	}
	public void setActivation(boolean activation) {
		this.activation = activation;
	}
	
	
	@Column(name = "created_by", nullable = false)
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	@Column(name = "created_date", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	@Column(name = "modified_date", nullable = true)
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Column(name = "status", nullable = true)
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Column(name = "lock", nullable = true)
	public boolean isLock() {
		return lock;
	}
	public void setLock(boolean lock) {
		this.lock = lock;
	}
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "user_type_id")
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	public Set<UserActivation> getUserActivation() {
		return userActivation;
	}
	public void setUserActivation(Set<UserActivation> userActivation) {
		this.userActivation = userActivation;
	}
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	
	
	@Column(name = "clock_in_time")
	public String getClock_in_time() {
		return clock_in_time;
	}
	public void setClock_in_time(String clock_in_time) {
		this.clock_in_time = clock_in_time;
	}
	
	
	@Column(name = "clock_out_time", nullable = false)
	public Date getClock_out_time() {
		return clock_out_time;
	}
	public void setClock_out_time(Date clock_out_time) {
		this.clock_out_time = clock_out_time;
	}
	
	
	
	
	@Column(name = "clocking_status")
	public String getClocking_status() {
		return clocking_status;
	}
	public void setClocking_status(String clocking_status) {
		this.clocking_status = clocking_status;
	}
	
	@Column(name = "clock_status")
	public boolean isClock_status() {
		return clock_status;
	}
	public void setClock_status(boolean clock_status) {
		this.clock_status = clock_status;
	};


}
