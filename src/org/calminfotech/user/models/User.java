package org.calminfotech.user.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.hrunit.models.StaffRegistration;
import org.calminfotech.system.models.Organisation;
import org.hibernate.annotations.SQLDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import java.util.Set;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET is_deleted = 1 WHERE UserId = ?")
// @Where(clause = "is_deleted <> 1")
public class User implements Serializable {

	/**
	 * x
	 */
	private static final long serialVersionUID = 1L;

	private int userId;

	private String username;

	private String password;

	private boolean activation;

	private String createdBy;

	private Date createdDate;

	private Date modifiedDate;

	private String modified_by;

	private boolean status;

	private boolean lock;

	private Role role;

	private Organisation organisation;

	private UserType userType;

	private Set<UserActivation> userActivation = new HashSet<UserActivation>();

	private UserProfile userProfile;

	private Date clock_in_time;

	private String clock_out_time;

	private String clocking_status;

	private HrunitCategory unit;

	private Boolean is_deleted;

	private Boolean levelsecurity;

	private Boolean webaccess;
	private StaffRegistration staff;

	// private Set<VisitWorkflowPoint> workflowPoints = new
	// HashSet<VisitWorkflowPoint>();

	private Set<Permission> userPermission = new HashSet<Permission>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	/*
	 * @OneToMany(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name="userid")
	 */
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

	@OneToOne
	@JoinColumn(name = "staff_id", nullable = false)
	public StaffRegistration getStaff() {
		return staff;
	}

	public void setStaff(StaffRegistration staff) {
		this.staff = staff;
	}

	public void setUsername(String email) {
		this.username = email;
	}

	@Column(name = "password", nullable = true)
	public String getPassword() {
		return password;
	}

	public Boolean getWebaccess() {
		return webaccess;
	}

	public void setWebaccess(Boolean webaccess) {
		this.webaccess = webaccess;
	}

	@Column(name = "is_deleted")
	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	@Column(name = "levelsecurity")
	public Boolean getLevelsecurity() {
		return levelsecurity;
	}

	public void setLevelsecurity(Boolean levelsecurity) {
		this.levelsecurity = levelsecurity;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "activation", nullable = true)
	public boolean getActivation() {
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
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name = "lock", nullable = true)
	public boolean getLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	@ManyToOne
	@JoinColumn(name = "role_id")
	@JsonIgnore
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
	public Date getClock_in_time() {
		return clock_in_time;
	}

	public void setClock_in_time(Date clock_in_time) {
		this.clock_in_time = clock_in_time;
	}

	@Column(name = "clock_out_time", nullable = false)
	public String getClock_out_time() {
		return clock_out_time;
	}

	public void setClock_out_time(String clock_out_time) {
		this.clock_out_time = clock_out_time;
	}

	@ManyToOne
	@JoinColumn(name = "clocking_unit_id")
	public HrunitCategory getUnit() {
		return unit;
	}

	@Column(name = "clocking_status")
	public String getClocking_status() {
		return clocking_status;
	}

	public void setUnit(HrunitCategory unit) {
		this.unit = unit;
	}

	public void setClocking_status(String clocking_status) {
		this.clocking_status = clocking_status;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	public Set<UserActivation> getUserActivation() {
		return userActivation;
	}

	public void setUserActivation(Set<UserActivation> userActivation) {
		this.userActivation = userActivation;
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

	// @ManyToMany
	// @JoinTable(name = "consultation_workflow_points_users", joinColumns = {
	// @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name
	// = "point_id") })

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public Set<Permission> getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(Set<Permission> userPermission) {
		this.userPermission = userPermission;

	}
}
