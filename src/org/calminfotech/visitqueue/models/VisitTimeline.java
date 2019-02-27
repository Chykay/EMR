package org.calminfotech.visitqueue.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.user.models.User;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "visit_timelines")
public class VisitTimeline {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	
	@ManyToOne
	@JoinColumn(name = "visit_id")
	private Visit visit;
	
	@ManyToOne
	@JoinColumn(name = "point_id")
	private VisitWorkflowPoint point;
	
	



	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}



//	@ManyToOne
//	@JoinColumn(name = "unit_id")
	//private HrunitCategory hrunitcategory;
	
	
   @ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	

	public VisitWorkflowPoint getPoint() {
	return point;
}

public void setPoint(VisitWorkflowPoint point) {
	this.point = point;
}

//	public HrunitCategory getHrunitcategory() {
//		return hrunitcategory;
//	}
//
//	public void setHrunitcategory(HrunitCategory hrunitcategory) {
//		this.hrunitcategory = hrunitcategory;
//	}
//
//	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}



	@Column(name = "comment")
	private String comment;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "status")
	private boolean status;

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

	
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createdDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createdBy
	 */
	
	
	
	public boolean isStatus() {
		return status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/*public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public LoginSection getLoginSection() {
		return loginSection;
	}

	public void setLoginSection(LoginSection loginSection) {
		this.loginSection = loginSection;
	}*/

	
	/*public int getUnitCategoryId() {
		return unitCategoryId;
	}

	public void setUnitCategoryId(int unitCategoryId) {
		this.unitCategoryId = unitCategoryId;
	}*/

}
