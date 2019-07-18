package org.calminfotech.hmo.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.Where;

//import org.hibernate.annotations.SQLDelete;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hmocode")
@SQLDelete(sql = "UPDATE hmo SET is_deleted = 1 WHERE hmo_id = ?")
// @Where(clause = "is_deleted <> 1")
public class HmoCode {

	@Id
	@Column(name = "code")
	private String hmocode;

	@OneToMany
	@JoinColumn(name = "hmocode")
	// @Where(clause = "is_deleted <> 1 and status=1")
	private Set<Hmo> hmo;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "isactive")
	private Boolean isActive;

	public Set<Hmo> getHmo() {
		return hmo;
	}

	public void setHmo(Set<Hmo> hmo) {
		this.hmo = hmo;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getHmocode() {
		return hmocode;
	}

	public void setHmocode(String hmocode) {
		this.hmocode = hmocode;
	}

}
