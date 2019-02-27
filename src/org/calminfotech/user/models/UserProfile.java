package org.calminfotech.user.models;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.calminfotech.hrunit.models.StaffRegistration;
import org.calminfotech.system.models.Gender;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.Title;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "user_profiles")
public class UserProfile {

	private Integer userId;
	private User user;
	private String lastName;
	private String otherNames;
	private Title title;
	private Gender gender;
	private String address;
	private Date createdDate;
	private String createdBy;
	private Date modifiedDate;
	private Blob profileImage;
	private String imageContentType;
	private String modified_by;

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}

	
	
	
	
	

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "other_names", nullable = false)
	public String getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}

	@ManyToOne
	@JoinColumn(name = "title_id")
	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	@ManyToOne
	@JoinColumn(name = "gender_id", nullable = true)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Column(name = "address", nullable = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "created_date", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "created_by", nullable = true)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	
	
	@Column(name = "modified_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	@Column(name = "profile_image", nullable = true)
	public Blob getProfileImage() {
		return profileImage;
	}



	public void setModifiedDate(java.util.Date date) {
		this.modifiedDate = (Date) date;
	}

	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}

	@Column(name = "image_content_type", nullable = true)
	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	

}
