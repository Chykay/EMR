package org.calminfotech.hmo.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.utils.models.Bank;
import org.calminfotech.utils.models.Hmostatus;
import org.hibernate.annotations.SQLDelete;

//import org.hibernate.annotations.SQLDelete;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hmo")
@SQLDelete(sql = "UPDATE hmo SET is_deleted = 1 WHERE hmo_id = ?")
//@Where(clause = "is_deleted <> 1")
public class Hmo {

	@Id
	@GeneratedValue
	@Column(name ="id")
	private Integer id;
	
	@Column(name ="organisation_id")
	private Integer organisationId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	
	@OneToMany
	@JoinColumn(name = "hmo_id")
	//@Where(clause = "is_deleted <> 1 and status=1")
	private Set<HmoPackage> hmoPackage;
	
	
	/*@ManyToOne
	@JoinColumn(name = "billingscheme_id")
	private BillScheme billScheme;
	*/

	@ManyToOne
	@JoinColumn(name = "hmostatus_id")
	private Hmostatus hmostatus;
	
	@ManyToOne
	@JoinColumn(name = "bank_id")
	private Bank bank;
	
	
	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	
	@Column(name = "accountno")
	private String accoutno;
	
	@Column(name = "admin_name")
	private String adminName;
	
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	
	

	
	public Hmostatus getHmostatus() {
		return hmostatus;
	}

	public void setHmostatus(Hmostatus hmostatus) {
		this.hmostatus = hmostatus;
	}
/*
	public BillScheme getBillScheme() {
		return billScheme;
	}

	public void setBillScheme(BillScheme billScheme) {
		this.billScheme = billScheme;
	}
*/
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getAccoutno() {
		return accoutno;
	}

	public void setAccoutno(String accoutno) {
		this.accoutno = accoutno;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Set<HmoPackage> getHmoPackage() {
		return hmoPackage;
	}

	public void setHmoPackage(Set<HmoPackage> hmoPackage) {
		this.hmoPackage = hmoPackage;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	
	
	


	
	public Set<HmoPackage> getEhmoPackage() {
		return hmoPackage;
	}

	public void setEhmoPackage(Set<HmoPackage> hmoPackage) {
		this.hmoPackage = hmoPackage;
	}

	
	
	
}
	