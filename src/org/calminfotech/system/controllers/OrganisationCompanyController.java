package org.calminfotech.system.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.calminfotech.system.boInterface.OrganisationCategoryBo;
import org.calminfotech.system.boInterface.OrganisationCompanyBo;
import org.calminfotech.system.boInterface.OrganisationDirectorBo;
import org.calminfotech.system.boInterface.OrganisationDocumentBo;
import org.calminfotech.system.boInterface.OrganisationTypeBo;
import org.calminfotech.system.boInterface.ResourceBo;
import org.calminfotech.system.daoInterface.OrganisationCompanyDao;
import org.calminfotech.system.forms.DirectorImageForm;
import org.calminfotech.system.forms.OrganisationCompanyForm;
import org.calminfotech.system.forms.OrganisationDirectorForm;
import org.calminfotech.system.forms.OrganisationDocumentForm;
import org.calminfotech.system.forms.SettingsForm;
import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.system.models.OrganisationDirector;
import org.calminfotech.system.models.OrganisationDocument;
import org.calminfotech.user.forms.UserImageForm;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.CountryList;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.UserTypesList;
import org.calminfotech.utils.annotations.Layout;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/organisationscompany")
public class OrganisationCompanyController {

	@Autowired
	private OrganisationCompanyBo organisationBo;

	@Autowired
	private OrganisationCompanyDao organisationDao;

	@Autowired
	private OrganisationDocumentBo adminDocumentBo;

	@Autowired
	private ResourceBo resourceBo;

	@Autowired
	private UserTypesList typeList;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private StatesList stateBo;

	@Autowired
	private LocalGovernmentAreaList lgaBo;

	@Autowired
	private OrganisationDirectorBo directorBo;

	@Autowired
	private OrganisationTypeBo organisationTypeBo;

	@Autowired
	private OrganisationCategoryBo organisationCategoryBo;

	@Autowired
	private LocalGovernmentAreaList lgasList;

	@Autowired
	private StatesList stateList;

	@Autowired
	private CountryList countryList;

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String index(RedirectAttributes redirectAttributes, Model model) {

		// if (authorize.isAllowed("HMO000") )

		model.addAttribute("organisations", this.organisationBo
				.fetchAll(this.userIdentity.getOrganisation().getId()));

		return "system/organisationcompany/index";

	}

	@RequestMapping(value = { "/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	// @Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model, RedirectAttributes redirectAttributes) {
		// if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {
		model.addAttribute("organisations", this.organisationBo
				.fetchAll(this.userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/organisationcompany/indexall";
		// }

		// else

		// {
		// alert.setAlert(redirectAttributes, Alert.WARNING,
		// "You cannot do this");

		// return "redirect:/";

		// }
	}

	@RequestMapping(value = "/view/{id}")
	@Layout(value = "layouts/form_wizard_layout")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		OrganisationCompany organisation = this.organisationBo
				.getOrganisationById(id);

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisations";
		}
		model.addAttribute("imageForm", new UserImageForm());
		model.addAttribute("organisation", organisation);
		return "system/organisationcompany/view";
	}

	@RequestMapping(value = "/add")
	@Layout(value = "layouts/form_wizard_layout")
	public String addAction(RedirectAttributes redirectAttributes, Model model) {
		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1)

		{

			model.addAttribute("oForm", new OrganisationCompanyForm());
			model.addAttribute("types", this.typeList.fetchAll());
			model.addAttribute("lgas", this.lgasList.fetchAll());
			model.addAttribute("states", this.stateList.fetchAll());
			model.addAttribute("countries", this.countryList.fetchAll());

			model.addAttribute("otype",
					this.organisationTypeBo.fetchAllByOrganisation());

			model.addAttribute("categories", this.organisationCategoryBo
					.fetchAll(this.userIdentity.getOrganisation().getId()));

			return "system/organisationcompany/add";
		} else {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String saveAction(
			@Valid @ModelAttribute("oForm") OrganisationCompanyForm organisationForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1)

		{
			System.out.print("***");
			System.out.print(organisationForm.getAddress());

			System.out.print(organisationForm.getEmail());

			System.out.print(organisationForm.getEstablishedYear());

			// System.out.print((Date) organisationForm.getEstablishedYear());

			System.out.print("***");

			if (result.hasErrors()) {
				// System.out.println(result.getFieldError());
				alert.setAlert(redirectAttributes, Alert.DANGER, result
						.getFieldError().toString());
				return "redirect:/system/organisationscompany/add";
			}

			OrganisationCompany organisation = this.organisationBo
					.save(organisationForm);

			// Run the resource management system to keep the system up
			// this.resourceBo.buildResourcesForOrganisation(organisation);

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Organisation has been created!");

			return "redirect:/system/organisations";
		} else {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";
		}
	}

	/*
	 * @InitBinder public void initBinder(final WebDataBinder binder){ final
	 * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 * binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
	 * true)); }
	 */

	@RequestMapping(value = "/edit/{id}")
	@Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		// if (userIdentity.getOrganisation().getId() == 1 )

		// {
		OrganisationCompany organisation = this.organisationBo
				.getOrganisationById(id);
		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisationscompany/";
		}

		OrganisationCompanyForm organisationForm = new OrganisationCompanyForm();

		// State state =
		// stateBo.getStateById(organisation.getState().getStateId());
		// LocalGovernmentArea lga =
		// lgaBo.getLgaById(organisation.getLga().getLocalGovernmentAreaId());

		System.out.print("orgid");
		System.out.print(organisation.getId());
		System.out.print("orgid");

		organisationForm.setId(organisation.getId());
		organisationForm.setName(organisation.getName());
		organisationForm.setOrganisationTypeId(organisation
				.getOrganisationType().getOrganisationTypeId());
		organisationForm.setAddress(organisation.getAddress());
		organisationForm.setDescription(organisation.getDescription());
		organisationForm.setEmail(organisation.getEmail());
		if (organisation.getCountry() != null) {
			organisationForm.setCountryCode(organisation.getCountry()
					.getCountryCode());
		} else {
			organisationForm.setCountryCode(null);
		}

		if (organisation.getState() != null) {
			organisationForm.setStateCode(organisation.getState()
					.getStateCode());
		} else {
			organisationForm.setStateCode(null);
		}

		if (organisation.getLga() != null) {
			organisationForm.setLgaCode(organisation.getLga()
					.getLocalGovernmentAreasCode());
		} else {
			organisationForm.setLgaCode(null);
		}

		organisationForm.setParentCategoryId(organisation
				.getOrganisationCategory().getCategoryId());

		organisationForm.setPhoneno(organisation.getPhoneno());
		organisationForm.setDomain(organisation.getDomain());
		if (organisation.getEstablishedYear() != null) {
			organisationForm.setEstablishedYear(DateUtils
					.formatDateToString(organisation.getEstablishedYear()));
		}

		// model.addAttribute("lgas", this.lgaBo.fetchAll());
		// model.addAttribute("states", this.stateBo.fetchAll());
		model.addAttribute("organisation", organisation);

		model.addAttribute("types", this.typeList.fetchAll());
		model.addAttribute("statelist",
				this.stateList.fetchStateByCountryCode(organisation
						.getCountry() != null ? organisation.getCountry()
						.getCountryCode() : null));
		model.addAttribute(
				"lgalist",
				this.lgasList
						.fetchLgaByStateCode(organisation.getState() != null ? organisation
								.getState().getStateCode() : null));

		model.addAttribute("countries", this.countryList.fetchAll());

		model.addAttribute("otype",
				this.organisationTypeBo.fetchAllByOrganisation());

		model.addAttribute("categories", organisationCategoryBo
				.fetchAllByOrganisationByCategoryType(organisation
						.getOrganisationType().getOrganisationTypeId()));

		// model.addAttribute("categories",
		// this.organisationCategoryBo.fetchAll(this.userIdentity.getOrganisation().getId()));
		model.addAttribute("statelist",
				this.stateList.fetchStateByCountryCode(organisation
						.getCountry() != null ? organisation.getCountry()
						.getCountryCode() : null));
		model.addAttribute(
				"lgalist",
				this.lgasList
						.fetchLgaByStateCode(organisation.getState() != null ? organisation
								.getState().getStateCode() : null));

		model.addAttribute("countries", this.countryList.fetchAll());

		model.addAttribute("oForm", organisationForm);

		this.auditor.before(request, "Organisation Admin Form",
				organisationForm);

		return "system/organisationcompany/edit";
		// }
		// else
		// {
		// alert.setAlert(redirectAttributes, Alert.WARNING,
		// "You cannot do this" );

		// return "redirect:/";
		// }
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String updateAction(
			@Valid @ModelAttribute("oForm") OrganisationCompanyForm organisationForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		// if (userIdentity.getOrganisation().getId() == 1 )

		// {
		if (result.hasErrors()) {
			model.addAttribute("types", this.typeList.fetchAll());
			return "system/organisationcompany/edit";
		}

		this.organisationBo.update(organisationForm);

		this.auditor.after(request, "Organisation Admin Form",
				organisationForm, this.userIdentity.getUsername(),
				organisationForm.getId());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Organisation updated");
		return "redirect:/system/organisationscompany";
		// return "redirect:/system/organisationscompany/view/"
		// + organisationForm.getId();
		// }
		// else
		// {
		// alert.setAlert(redirectAttributes, Alert.WARNING,
		// "You cannot do this" );

		// return "redirect:/";
		// }
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		OrganisationCompany organisation = this.organisationBo
				.getOrganisationById(id);
		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisationscompany/";
		}

		OrganisationCompanyForm organisationForm = new OrganisationCompanyForm();
		organisationForm.setId(organisation.getId());

		model.addAttribute("organisation", organisation);
		model.addAttribute("oForm", organisationForm);
		return "system/organisationcompany/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("oForm") OrganisationCompanyForm organisationForm,
			RedirectAttributes redirectAttributes) {

		OrganisationCompany organisation = this.organisationBo
				.getOrganisationById(organisationForm.getId());

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisationscompany/";
		}

		this.organisationBo.delete(organisation);
		// organisation.setDelete(true);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Organisation deleted!");
		return "redirect:/system/organisationscompany/";
	}

	@RequestMapping(value = "/activate/{id}", method = RequestMethod.POST)
	public String confirmDeleteActionact(@PathVariable("id") Integer id,
			@ModelAttribute("oForm") OrganisationCompanyForm organisationForm,
			RedirectAttributes redirectAttributes) {

		OrganisationCompany organisation = this.organisationBo
				.getOrganisationById(id);

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisationscompany/";
		}

		if (organisation.isActive()) {
			organisation.setActive(false);
		} else {
			organisation.setActive(true);

		}

		this.organisationBo.update(organisation);
		// organisation.setDelete(true);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Organisation deleted!");
		return "redirect:/system/organisationscompany/";
	}

	// Organisation Directors
	@RequestMapping(value = "/director/{id}")
	public String directorPage(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		OrganisationCompany organ = organisationBo.getOrganisationById(id);

		if (null == organ) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisations";
		}

		OrganisationDirectorForm directorForm = new OrganisationDirectorForm();
		directorForm.setOrganisation(organ.getId());

		model.addAttribute("directorForm", directorForm);
		model.addAttribute("organisation", organ);
		model.addAttribute("directorList",
				directorBo.fetchAllDirectorByOrganisation(organ));
		return "system/organisations/directors/index";
	}

	@RequestMapping(value = "/director/{id}", method = RequestMethod.POST)
	public String saveDirectorAction(
			@Valid @ModelAttribute("directorForm") OrganisationDirectorForm directorForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		OrganisationCompany organisation = organisationBo
				.getOrganisationById(directorForm.getOrganisation());

		if (result.hasErrors()) {
			model.addAttribute("organisation", organisation);
			return "system/organisationcompany/directors/index";
		}

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Could not be save. Invalid resource");
			return "redirect:/system/organisations";
		}

		OrganisationDirector director = new OrganisationDirector();
		director.setOrganisation(organisation);

		director.setDirectorLastName(directorForm.getDirectorLastName());
		director.setDirectorFirstName(directorForm.getDirectorFirstName());
		director.setEmail(directorForm.getEmail());
		director.setDirectorPhone(directorForm.getDirectorPhone());
		// director.setCreatedBy(userIdentity.getUsername());
		director.setOrganisation(organisationBo
				.getOrganisationById(directorForm.getOrganisation()));
		Blob blob = Hibernate.createBlob(directorForm.getDirectorAvatar()
				.getInputStream());
		director.setDirectorAvatar(blob);

		String contentType = directorForm.getDirectorAvatar().getContentType();
		director.setContentType(contentType);

		directorBo.save(director);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/system/organisationscompany/director/"
				+ organisation.getId();

	}

	// To Edit Organisation Director
	@RequestMapping(value = "/director/edit/{id}")
	public String editDirectorPage(@PathVariable("id") Integer directorId,
			Model model) {

		return "";
	}

	// To View Organisation Director
	@RequestMapping(value = "/director/view/{id}")
	public String viewDirector(@PathVariable("id") Integer directorId,
			Model model) {

		OrganisationCompany organisation = organisationBo
				.getOrganisationById(directorId);
		// System.out.println("Organisation Id is " + organisation.getItemId());

		// OrganisationCompany organisation =
		// this.organisationBo.getOrganisationById(id);

		OrganisationDirector director = directorBo
				.getOrganisationDirectorId(directorId);
		// director.setOrganisation(organisation);

		DirectorImageForm imageForm = new DirectorImageForm();
		imageForm.setDirector_Id(director.getDirectorId());

		model.addAttribute("imageForm", imageForm);

		// model.addAttribute("organisation", organisation);

		model.addAttribute("director", director);
		model.addAttribute("organisation", organisation);
		return "system/organisationcompany/directors/view";

	}

	@RequestMapping(value = "/{id}/documents")
	public String documentAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		System.out.println("confirm");

		OrganisationCompany organisation = this.organisationBo
				.getOrganisationById(id);

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisationscompany/directors";
		}
		OrganisationDocumentForm pdForm = new OrganisationDocumentForm();
		pdForm.setId(organisation.getId());
		System.out.println("confirm");

		model.addAttribute("adminDocumentForm", pdForm);
		model.addAttribute("organisation", organisation);

		return "system/organisationcompany/directors/documents";
	}

	@RequestMapping(value = "/{id}/documents", method = RequestMethod.POST)
	public String saveDocument(
			@Valid @ModelAttribute("adminDocumentForm") OrganisationDocumentForm adminDocumentForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		System.out.println(adminDocumentForm + "/frm");
		System.out.println(adminDocumentForm.getOrganisationId() + "confirm");

		OrganisationCompany organisation = this.organisationBo
				.getOrganisationById(adminDocumentForm.getId());

		if (result.hasErrors()) {
			model.addAttribute("organisation", organisation);
			return "system/organisations/directors/documents";
		}

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Could not upload document. Invalid resource");
			return "redirect:/system/organisationscompany/directors/"
					+ adminDocumentForm.getOrganisationId();
		}

		OrganisationDocument adminDocument = new OrganisationDocument();
		try {
			@SuppressWarnings("deprecation")
			Blob blob = Hibernate.createBlob(adminDocumentForm.getDocument()
					.getInputStream());
			adminDocument.setFile(blob);

			String contentType = adminDocumentForm.getDocument()
					.getContentType();

			adminDocument.setContentType(contentType);
			adminDocument.setName(adminDocumentForm.getDocument()
					.getOriginalFilename());
			adminDocument.setOrganisation(organisation);
			adminDocument.setId(organisation.getId());
			adminDocument.setCreateDate(new GregorianCalendar().getTime());
			adminDocument.setCreatedBy(userIdentity.getUsername());

			// organisation.getAdminDocuments().add(adminDocument);

			// Used for saving
			this.adminDocumentBo.save(adminDocument);

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Image Uploaded successfully");

		} catch (IOException e) {
			e.printStackTrace();
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Document Upload failed");
		}

		return "redirect:/system/organisationscompany/"
				+ adminDocumentForm.getId() + "/documents";
	}

	@RequestMapping(value = "/{id}/documents/delete", method = RequestMethod.POST)
	public String confirmDocumentDeleteAction(
			@ModelAttribute("pForm") OrganisationDocumentForm adminDocumentForm,
			RedirectAttributes redirectAttributes) {

		OrganisationDocument adminDocument = this.adminDocumentBo
				.getAdminDocumentById(adminDocumentForm.getId());

		if (null == adminDocument) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisationscompany/directors";
		}

		int organisation = adminDocument.getOrganisation().getId();

		this.adminDocumentBo.delete(adminDocument);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! File deleted");

		return "redirect:/system/organisationscompany/"
				+ adminDocumentForm.getId() + "/documents";
	}

	@RequestMapping(value = "settings/index", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String indexsettings(RedirectAttributes redirectAttributes,
			Model model) {

		// if (authorize.isAllowed("HMO000") )

		SettingsForm vForm = new SettingsForm();

		model.addAttribute(
				"settings",
				this.organisationBo.fetchallsettings(this.userIdentity
						.getOrganisation().getOrgCoy().getId()));
		model.addAttribute("vform", vForm);
		return "system/organisationcompany/settings/index";
	}

	@ResponseBody
	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public String viewImage(HttpServletResponse response,
			@PathVariable("id") Integer id) {
		// /User user = userBo.getUserById(id);
		// UserProfile userProfile = user.getUserProfile();
		OrganisationCompany org = this.organisationBo.getOrganisationById(id);
		if (null != org.getLogo()) {
			try {
				response.setContentType(org.getImageContentType());
				response.setHeader("Content-Disposition", "inline;filename=\""
						+ org.getName() + "\"");
				OutputStream outputStream = response.getOutputStream();
				IOUtils.copy(org.getLogo().getBinaryStream(), outputStream);
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
			@ModelAttribute("imageForm") UserImageForm imageForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		// UserProfile userProfile = userProfileBo

		// .getUserProfileByUserId(userIdentity.getUserId());

		OrganisationCompany org = this.organisationBo
				.getOrganisationById(userIdentity.getOrganisation().getOrgCoy()
						.getId());

		if (null == org) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Could not upload image");
			return "redirect:/user/profile";
		}

		try {
			System.out.print("contentcontent");
			System.out.print(imageForm.getImageFile().getContentType()
					.toString());
			System.out.print(imageForm.getImageFile().getSize());

			if (imageForm.getImageFile().getSize() > 150000)

			{

				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"Image size cannot be more than 150 KB");

				return "redirect:/system/organisationscompany/view/"
						+ org.getId();

			}

			if (!imageForm.getImageFile().getContentType().toString()
					.toLowerCase().equals("image/jpeg")

					&& !imageForm.getImageFile().getContentType().toString()
							.toLowerCase().equals("image/png")) {

				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"Image type should be jpg or png");

				return "redirect:/system/organisationscompany/view/"
						+ org.getId();

			}

			@SuppressWarnings("deprecation")
			Blob blob = Hibernate.createBlob(imageForm.getImageFile()
					.getInputStream());

			org.setLogo(blob);

			String contentType = imageForm.getImageFile().getContentType();
			org.setImageContentType(contentType);
			org.setModifiedDate(new Date(System.currentTimeMillis()));
			org.setModifiedBy(userIdentity.getUsername());

			organisationDao.update(org);

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Image Uploaded successfully");

		} catch (IOException e) {
			e.printStackTrace();
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Image Upload failed");
		}

		return "redirect:/system/organisationscompany/view/" + org.getId();
	}

	/*
	 * 
	 * @RequestMapping(value = "settings/changesettings", method =
	 * RequestMethod.POST)
	 * 
	 * @Layout("layouts/datatable") public String
	 * indexchangesettings(RedirectAttributes redirectAttributes, Model model,
	 * 
	 * @ModelAttribute("vform") SettingsForm settingsForm,HttpServletRequest
	 * request ) {
	 * 
	 * 
	 * /* Visit visit = visitBo.getVisitationById(vform.getId());
	 * 
	 * VisitWorkflowUserConfigurationForm vformbefore = new
	 * VisitWorkflowUserConfigurationForm();
	 * 
	 * vformbefore.setStatusId(visit.getStatus().getId());
	 * auditor.before(request, "Visit Queue Status", vformbefore);
	 */

	/*
	 * 
	 * 
	 * SysSettings Settings =
	 * this.organisationBo.getSettingsById(settingsForm.getId());
	 * 
	 * 
	 * SettingsForm settingsbeforeForm = new SettingsForm();
	 * 
	 * settingsbeforeForm.setValue(sysSettings.getValue());
	 * 
	 * auditor.before(request, "Settings Value", settingsbeforeForm);
	 * 
	 * sysSettings.setValue(settingsForm.getValue());
	 * 
	 * this.organisationBo.update(sysSettings);
	 * 
	 * auditor.after(request, "Settings Value", settingsForm,
	 * userIdentity.getUsername());
	 * 
	 * 
	 * alert.setAlert(redirectAttributes, Alert.SUCCESS,
	 * "Settings Value Succesfully updated!!!");
	 * 
	 * 
	 * return "redirect:/system/organisationscompany/settings/index";
	 * 
	 * }
	 */

}