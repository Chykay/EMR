package org.calminfotech.ledger.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.user.models.User;


@MappedSuperclass
public class CommonLedger{


	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private OrganisationCompany orgCoy;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	private Date create_date;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User created_by;
		
	@Column(name = "modify_date", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date modify_date;
	
	@ManyToOne
	@JoinColumn(name = "modified_by")
	private User modified_by;
	
	@Column(name = "is_deleted")
	private boolean is_deleted;

	public Organisation getOrganisation() { return organisation; }
	
	public OrganisationCompany getOrgCoy() {
		return orgCoy;
	}

	public void setOrgCoy(OrganisationCompany orgCoy) {
		this.orgCoy = orgCoy;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public User getCreated_by() {
		return created_by;
	}
	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public User getModified_by() {
		return modified_by;
	}
	public void setModified_by(User modified_by) {
		this.modified_by = modified_by;
	}
	public boolean getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	

	
}
