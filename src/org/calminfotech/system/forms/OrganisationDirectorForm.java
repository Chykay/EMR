package org.calminfotech.system.forms;

import org.springframework.web.multipart.MultipartFile;

public class OrganisationDirectorForm {	
	
	private Integer directorId;	
	
	private String directorLastName;
	
	private String directorFirstName;
	
	private String directorOtherName;
	
	private String gender;
	
	private String Email;
	
	private String directorPhone;
	
	private MultipartFile directorAvatar;
	
	private String contentType;
	
	private Integer organisation;

	//Getters and Setters
	
	public Integer getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
	}

	public String getDirectorLastName() {
		return directorLastName;
	}

	public void setDirectorLastName(String directorLastName) {
		this.directorLastName = directorLastName;
	}

	public String getDirectorFirstName() {
		return directorFirstName;
	}

	public void setDirectorFirstName(String directorFirstName) {
		this.directorFirstName = directorFirstName;
	}

	public String getDirectorOtherName() {
		return directorOtherName;
	}

	public void setDirectorOtherName(String directorOtherName) {
		this.directorOtherName = directorOtherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDirectorPhone() {
		return directorPhone;
	}

	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}

	public MultipartFile getDirectorAvatar() {
		return directorAvatar;
	}

	public void setDirectorAvatar(MultipartFile directorAvatar) {
		this.directorAvatar = directorAvatar;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Integer organisation) {
		this.organisation = organisation;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
