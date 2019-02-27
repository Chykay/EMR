package org.calminfotech.system.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "globalitem_kind")
//@SQLDelete(sql = "UPDATE drugskind SET is_deleted = 1 WHERE drugskind_id = ?")
//@Where(clause = "is_deleted <> 1")
public class GlobalItemKind {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	

	@Id
	@Column(name = "code", nullable = false)
	private String code;
	
	
	
	@Column(name = "name", nullable = false)
	private String name;
	
	
	
    @ManyToOne
	@JoinColumn(name = "globalitemtype_id", nullable = false)
	private GlobalItemType  globalitemtype;
	
   
    
    
	@OneToMany
	@JoinColumn(name = "globalitemkind_code")
	private Set<GlobalItem> globalItem;

    

	
	
	
	
	
	
	
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	
	@Column(name = "modified_by", nullable = true)
	private String ModifiedBy;
	
	@Column(name = "modified_date", nullable = true)
	private Date modifiedDate;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	
	
	
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public GlobalItemType getGlobalitemtype() {
		return globalitemtype;
	}


	public void setGlobalitemtype(GlobalItemType globalitemtype) {
		this.globalitemtype = globalitemtype;
	}


	public Set<GlobalItem> getGlobalItem() {
		return globalItem;
	}


	public void setGlobalItem(Set<GlobalItem> globalItem) {
		this.globalItem = globalItem;
	}


	public boolean isDeleted() {
		return isDeleted;
	}


	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	private Integer organisation_id;
	
	
	
		public String getModifiedBy() {
		return ModifiedBy;
	}

	
	public Integer getOrganisation_id() {
		return organisation_id;
	}

	public void setOrganisation_id(Integer organisation_id) {
		this.organisation_id = organisation_id;
	}

	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	

	public String getName() {
		return name;
	}





	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	//@ManyToOne
	
	
}
