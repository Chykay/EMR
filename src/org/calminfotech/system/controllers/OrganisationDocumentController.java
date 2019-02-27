package org.calminfotech.system.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.calminfotech.system.boInterface.OrganisationDocumentBo;
import org.calminfotech.system.boInterface.OrganisationDirectorBo;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.forms.OrganisationDirectorForm;
import org.calminfotech.system.forms.OrganisationDocumentForm;
import org.calminfotech.system.forms.DirectorImageForm;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.OrganisationDocument;
import org.calminfotech.system.models.OrganisationDirector;

import org.calminfotech.utils.Alert;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/admin/organisations/directors")
public class OrganisationDocumentController {

	
	@Autowired
	private OrganisationDocumentBo adminDocumentBo;
	
	@Autowired
	private OrganisationBo organisationBo;
	
	
	@Autowired
	private Alert alert;
	
	
	
	@Autowired
	private OrganisationDirectorBo organisationDirectorBo;
	
	@Autowired
	private OrganisationDirectorBo DirectorBo;

	@ResponseBody
	@RequestMapping(value = "/documents/view/{id}/{documentName}")
	public String viewDocumentAction(@PathVariable Integer id,
			@PathVariable("documentName") String documentName,
			RedirectAttributes redirectAttributes, HttpServletResponse response) {

		
		OrganisationDocument adminDocument = this.adminDocumentBo
				.getAdminDocumentById(id);

		if (null == adminDocument) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/admin/organisations/directors";
		}

		try {
			response.setContentType(adminDocument.getContentType());

			response.setHeader("Content-Disposition", "inline;filename=\""
					+ adminDocument.getName() + "\"");

			OutputStream outputStream = response.getOutputStream();

			IOUtils.copy(adminDocument.getFile().getBinaryStream(),
					outputStream);

			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/documents/delete/{id}")
	public String documentDeleteAction(@PathVariable Integer id,
			Model model, RedirectAttributes redirectAttributes) {
		
		
		System.out.println("confirm");
		OrganisationDocument adminDocument = this.adminDocumentBo
				.getAdminDocumentById(id);

		if (null == adminDocument) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/admin/organisations/directors";
		}

		OrganisationDocumentForm adminDocumentForm = new OrganisationDocumentForm();
		adminDocumentForm.setId(adminDocument.getId());
		
		
		model.addAttribute("document", adminDocument);
		model.addAttribute("pForm", adminDocumentForm);

		return "admin/organisations/directors/delete";
	}

	@RequestMapping(value = "/documents/delete/{id}", method = RequestMethod.POST)
	public String confirmDocumentDeleteAction(
			@ModelAttribute("pForm") OrganisationDocumentForm adminDocumentForm,
			RedirectAttributes redirectAttributes) {

		OrganisationDocument adminDocument = this.adminDocumentBo
				.getAdminDocumentById(adminDocumentForm.getId());

		if (null == adminDocument) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/admin/organisations/directors";
		}

		int organisation = adminDocument.getOrganisation().getId();

		this.adminDocumentBo.delete(adminDocument);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! File deleted");

		return "redirect:/admin/organisations/"+organisation+"/documents";
	}

	
	@ResponseBody
	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public String viewImage(@PathVariable("id") Integer id,
			HttpServletResponse response) {

		OrganisationDirector director = this.organisationDirectorBo.getOrganisationDirectorId(id);

		if (null != director.getDirectorAvatar()) {
			try {
				response.setContentType(director.getContentType());

				response.setHeader("Content-Disposition", "inline;filename=\""
						+ director.getDirectorLastName() + "\"");

				OutputStream outputStream = response.getOutputStream();

				IOUtils.copy(director.getDirectorAvatar().getBinaryStream(), outputStream);

				outputStream.flush();
				outputStream.close();

			} catch (IOException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public String processImage(
			@ModelAttribute("imageForm") DirectorImageForm imageForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		
		
		
		System.out.println("This is the ID : "+ imageForm.getDirector_Id());

		OrganisationDirector director = this.DirectorBo.getOrganisationDirectorId(imageForm.getDirector_Id());

		if (null == director) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Could not upload image. Invalid resource");
			return "redirect:/admin/organisations/directors";
		}

		try {
			@SuppressWarnings("deprecation")
			Blob blob = Hibernate.createBlob(imageForm.getImageFile()
					.getInputStream());
			director.setDirectorAvatar(blob);
            MultipartFile file= imageForm.getImageFile();
            long fileSize=file.getSize();
            if(fileSize < 55000){
			String contentType =file.getContentType();
			director.setContentType(contentType);
			// userProfile.setModifiedDate(new
			// Date(System.currentTimeMillis()));

		
			// Used for updating image and image content type only
			this.organisationDirectorBo.update(director);

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Image Uploaded successfully");
            }else{
            	alert.setAlert(redirectAttributes, Alert.DANGER,
    					"Image greater than required size");
            }
		} catch (IOException e) {
			e.printStackTrace();
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Image Upload failed");
		}

		return "redirect:/admin/organisations/director/view/" + director.getDirectorId();
	}

		}


