package org.calminfotech.system.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.visitqueue.models.VisitWorkflowPoint;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "section")
public class LoginSection {
	
	private Integer id;
	private String session_name;/*
	private BillScheme Pscheme;*/
	private Date createdDate;
	private String createdBy;
	private Organisation organisation;
	private Set<LoginSectionPoint> loginSectionPoint = new HashSet<LoginSectionPoint>();
	private Set<VisitWorkflowPoint> vwfwPoint;
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	/*@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="section_id")
	private Set<Visit> visit;*/
	
	/*@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "clocking_section_id")
	private Set<User> user;*/
	
	@Column(name = "session_name")
	public String getSession_name() {
		return session_name;
	}
	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}
	
	/*@ManyToOne
	@JoinColumn(name = "billingscheme_id")
	public BillScheme getPscheme() {
		return Pscheme;
	}*/
	
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	
	/*public void setPscheme(BillScheme pscheme) {
		Pscheme = pscheme;
	}*/
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@OneToMany
	@JoinColumn(name = "session_id")
	public Set<LoginSectionPoint> getLoginSectionPoint() {
		return loginSectionPoint;
	}
	public void setLoginSectionPoint(Set<LoginSectionPoint> loginSectionPoint) {
		this.loginSectionPoint = loginSectionPoint;
	}
	
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="section")
	public Set<VisitWorkflowPoint> getVwfwPoint() {
		return vwfwPoint;
	}
	public void setVwfwPoint(Set<VisitWorkflowPoint> vwfwPoint) {
		this.vwfwPoint = vwfwPoint;
	}
	/*public Set<Visit> getVisit() {
		return visit;
	}
	public void setVisit(Set<Visit> visit) {
		this.visit = visit;
	}*/
	
	
	/*public Set<User> getUser() {
		return user;
	}
	public void setUser(Set<User> user) {
		this.user = user;
	}*/
	
}
