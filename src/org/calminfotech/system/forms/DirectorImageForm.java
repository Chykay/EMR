package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class DirectorImageForm {
	
	private Integer id;
	
	private Integer director_Id;

	@NotBlank(message = "Pick a file")
	private MultipartFile imageFile;

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public Integer getDirector_Id() {
		return director_Id;
	}

	public void setDirector_Id(Integer director_Id) {
		this.director_Id = director_Id;
	}

	

	

	
	
	

}
