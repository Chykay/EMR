package org.calminfotech.visitqueue.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemPoint;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.models.User;
import org.calminfotech.visitqueue.models.VisitTimeline;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "visit_workflow_points")
@SQLDelete(sql = "UPDATE visit_workflow_points SET is_active = 0 WHERE id = ?")
//@Where(clause = "is_active <> 0")
public class VisitWorkflowPoint {

	public static final String PHARMACY = "Pharmacy";
	public Set<VisitTimeline> getVisitTimeline() {
		return visitTimeline;
	}

	public void setVisitTimeline(Set<VisitTimeline> visitTimeline) {
		this.visitTimeline = visitTimeline;
	}

	public static final String CONSULTANT = "Consultant";
	public static final String LABORATORY = "Laboratory";
	public static final String VITALS = "Vitals";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "start_point")
	private boolean startPoint;

	@Column(name = "end_point")
	private boolean endPoint;
	

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	/*
	@ManyToMany
	@JoinTable(name = "consultation_workflow_points_users", joinColumns = { @JoinColumn(name = "point_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private Set<User> pointUsers = new HashSet<User>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "point_id" )
	private Set<GlobalItemPoint> globalPoint;
	*/
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "point_id" )
	private Set<VisitTimeline> visitTimeline;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "point_id" )
	private Set<Visit> visit;
	
	
	
	
	/*
	@ManyToMany
	@JoinTable(name = "globalitem_point", 
	joinColumns = { @JoinColumn(name = "point_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "item_id") })
	private List<GlobalItem> globalItem;
*/
	@Column(name = "is_active")
	private boolean active;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the startPoint
	 */
	public boolean isStartPoint() {
		return startPoint;
	}

	/**
	 * @param startPoint
	 *            the startPoint to set
	 */
	public void setStartPoint(boolean startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * @return the endpoint
	 */
	public boolean isEndPoint() {
		return endPoint;
	}


	/**
	 * @param endpoint
	 *            the endpoint to set
	 */
	public void setEndPoint(boolean endpoint) {
		this.endPoint = endpoint;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Visit> getVisit() {
		return visit;
	}

	public void setVisit(Set<Visit> visit) {
		this.visit = visit;
	}

	
	
}
