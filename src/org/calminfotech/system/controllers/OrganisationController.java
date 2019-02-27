package org.calminfotech.system.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.OrganisationCategoryBo;
import org.calminfotech.system.boInterface.OrganisationCompanyBo;
import org.calminfotech.system.boInterface.OrganisationDirectorBo;
import org.calminfotech.system.boInterface.OrganisationDocumentBo;
import org.calminfotech.system.boInterface.OrganisationTypeBo;
import org.calminfotech.system.boInterface.ResourceBo;
import org.calminfotech.system.daoInterface.OrganisationDao;
import org.calminfotech.system.forms.DirectorImageForm;
import org.calminfotech.system.forms.OrganisationDirectorForm;
import org.calminfotech.system.forms.OrganisationDocumentForm;
import org.calminfotech.system.forms.OrganisationForm;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.system.models.OrganisationDirector;
import org.calminfotech.system.models.OrganisationDocument;
import org.calminfotech.user.forms.UserImageForm;
import org.calminfotech.user.utils.Authorize;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/organisations")
public class OrganisationController {

	@Autowired
	private OrganisationCompanyBo organisationCompanyBo;

	@Autowired
	private OrganisationBo organisationBo;

	@Autowired
	private OrganisationDao organisationDao;

	@Autowired
	private Authorize authorize;

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
		if (!authorize.isAllowed("BRAN000")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}
		// if (!authorize.isAllowed("HMO000") )

		model.addAttribute("organisations", this.organisationBo
				.fetchAll(this.userIdentity.getOrganisation().getId()));

		return "system/organisation/index";

	}

	@RequestMapping(value = { "/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	// @Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model, RedirectAttributes redirectAttributes) {
		// if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {
		if (!authorize.isAllowed("BRAN000")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}
		model.addAttribute("organisations", this.organisationBo
				.fetchAll(this.userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/organisation/indexall";
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

		if (!authorize.isAllowed("BRAN002")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		Organisation organisation = this.organisationBo.getOrganisationById(id);

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisations";
		}
		model.addAttribute("imageForm", new UserImageForm());
		model.addAttribute("organisation", organisation);
		return "system/organisation/view";
	}

	@RequestMapping(value = "/add")
	@Layout(value = "layouts/form_wizard_layout")
	public String addAction(RedirectAttributes redirectAttributes, Model model) {
		// if (userIdentity.getOrganisation().getOrgCoy().getId() == 1)

		// {
		if (!authorize.isAllowed("BRAN001")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		model.addAttribute("oForm", new OrganisationForm());
		model.addAttribute("types", this.typeList.fetchAll());

		model.addAttribute("lgas", this.lgasList.fetchAll());
		model.addAttribute("states", this.stateList.fetchAll());
		model.addAttribute("countries", this.countryList.fetchAll());

		model.addAttribute("otype",
				this.organisationTypeBo.fetchAllByOrganisation());

		model.addAttribute("categories", this.organisationCategoryBo
				.fetchAll(this.userIdentity.getOrganisation().getId()));

		return "system/organisation/add";
		// } else {
		// alert.setAlert(redirectAttributes, Alert.WARNING,
		// "You cannot do this");

		// return "redirect:/";
		// }
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String saveAction(
			@Valid @ModelAttribute("oForm") OrganisationForm organisationForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		// if (userIdentity.getOrganisation().getOrgCoy().getId() == 1)

		// {
		System.out.print("***");
		System.out.print(organisationForm.getAddress());

		System.out.print(organisationForm.getEmail());

		System.out.print(organisationForm.getEstablishedYear());

		// System.out.print((Date) organisationForm.getEstablishedYear());

		System.out.print("***");
		if (!authorize.isAllowed("BRAN001")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}
		if (result.hasErrors()) {
			// System.out.println(result.getFieldError());
			alert.setAlert(redirectAttributes, Alert.DANGER, result
					.getFieldError().toString());
			return "redirect:/system/organisations/add";
		}

		Organisation organisation = this.organisationBo.save(organisationForm);

		// Run the resource management system to keep the system up
		// this.resourceBo.buildResourcesForOrganisation(organisation);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Branch has been created!");

		return "redirect:/system/organisations";
		// } else {
		// alert.setAlert(redirectAttributes, Alert.WARNING,
		// "You cannot do this");

		// return "redirect:/";
		// }
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
		if (!authorize.isAllowed("BRAN003")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}
		// if (userIdentity.getOrganisation().getId() == 1 )

		// {

		Organisation organisation = this.organisationBo.getOrganisationById(id);
		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisations/";
		}

		OrganisationForm organisationForm = new OrganisationForm();

		// State state =
		// stateBo.getStateById(organisation.getState().getStateId());
		// LocalGovernmentArea lga =
		// lgaBo.getLgaById(organisation.getLga().getLocalGovernmentAreaId());

		System.out.print("orgid");
		System.out.print(organisation.getId());
		System.out.print("orgid");

		organisationForm.setId(organisation.getId());
		organisationForm.setName(organisation.getName());
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

		organisationForm.setPhoneno(organisation.getPhoneno());
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

		// model.addAttribute("categories",
		// organisationCategoryBo.fetchAllByOrganisationByCategoryType(organisation.getOrganisationType().getOrganisationTypeId()));

		// model.addAttribute("categories",
		// this.organisationCategoryBo.fetchAll(this.userIdentity.getOrganisation().getId()));

		model.addAttribute("oForm", organisationForm);

		this.auditor.before(request, "Organisation Admin Form",
				organisationForm);

		return "system/organisation/edit";
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
			@Valid @ModelAttribute("oForm") OrganisationForm organisationForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!authorize.isAllowed("BRAN003")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}
		// if (userIdentity.getOrganisation().getId() == 1 )

		// {
		if (result.hasErrors()) {
			model.addAttribute("types", this.typeList.fetchAll());
			return "system/organisation/edit";
		}

		this.organisationBo.update(organisationForm);

		this.auditor.after(request, "Organisation Admin Form",
				organisationForm, this.userIdentity.getUsername(),
				organisationForm.getId());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Branch updated");

		return "redirect:/system/organisations/index";

		// return "redirect:/system/organisations/view/"
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
		if (!authorize.isAllowed("BRAN004")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}
		Organisation organisation = this.organisationBo.getOrganisationById(id);
		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisations/";
		}

		OrganisationForm organisationForm = new OrganisationForm();
		organisationForm.setId(organisation.getId());

		model.addAttribute("organisation", organisation);
		model.addAttribute("oForm", organisationForm);
		return "system/organisation/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("oForm") OrganisationForm organisationForm,
			RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("BRAN004")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		Organisation organisation = this.organisationBo
				.getOrganisationById(organisationForm.getId());

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisations/";
		}

		this.organisationBo.delete(organisation);
		// organisation.setDelete(true);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Organisation deleted!");
		return "redirect:/system/organisations/";
	}

	@RequestMapping(value = "/activate/{id}", method = RequestMethod.POST)
	public String confirmDeleteActionact(@PathVariable("id") Integer id,
			@ModelAttribute("oForm") OrganisationForm organisationForm,
			RedirectAttributes redirectAttributes) {

		Organisation organisation = this.organisationBo.getOrganisationById(id);

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisations/";
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
		return "redirect:/system/organisations/";
	}

	// Organisation Directors
	@RequestMapping(value = "/director/{id}")
	public String directorPage(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		OrganisationCompany organ = organisationCompanyBo
				.getOrganisationById(id);

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

		OrganisationCompany organisation = organisationCompanyBo
				.getOrganisationById(directorForm.getOrganisation());

		if (result.hasErrors()) {
			model.addAttribute("organisation", organisation);
			return "system/organisation/directors/index";
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
		director.setOrganisation(organisationCompanyBo
				.getOrganisationById(directorForm.getOrganisation()));
		Blob blob = Hibernate.createBlob(directorForm.getDirectorAvatar()
				.getInputStream());
		director.setDirectorAvatar(blob);

		String contentType = directorForm.getDirectorAvatar().getContentType();
		director.setContentType(contentType);

		directorBo.save(director);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/system/organisations/director/"
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

		Organisation organisation = organisationBo
				.getOrganisationById(directorId);
		// System.out.println("Organisation Id is " + organisation.getItemId());

		// Organisation organisation =
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
		return "system/organisation/directors/view";

	}

	@RequestMapping(value = "/{id}/documents")
	public String documentAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		System.out.println("confirm");

		Organisation organisation = this.organisationBo.getOrganisationById(id);

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/organisations/directors";
		}
		OrganisationDocumentForm pdForm = new OrganisationDocumentForm();
		pdForm.setId(organisation.getId());
		System.out.println("confirm");

		model.addAttribute("adminDocumentForm", pdForm);
		model.addAttribute("organisation", organisation);

		return "system/organisation/directors/documents";
	}

	@RequestMapping(value = "/{id}/documents", method = RequestMethod.POST)
	public String saveDocument(
			@Valid @ModelAttribute("adminDocumentForm") OrganisationDocumentForm adminDocumentForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		System.out.println(adminDocumentForm + "/frm");
		System.out.println(adminDocumentForm.getOrganisationId() + "confirm");

		OrganisationCompany organisation = this.organisationCompanyBo
				.getOrganisationById(adminDocumentForm.getId());

		if (result.hasErrors()) {
			model.addAttribute("organisation", organisation);
			return "system/organisations/directors/documents";
		}

		if (null == organisation) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Could not upload document. Invalid resource");
			return "redirect:/system/organisations/directors/"
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

		return "redirect:/system/organisations/" + adminDocumentForm.getId()
				+ "/documents";
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
			return "redirect:/system/organisations/directors";
		}

		int organisation = adminDocument.getOrganisation().getId();

		this.adminDocumentBo.delete(adminDocument);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! File deleted");

		return "redirect:/system/organisations/" + adminDocumentForm.getId()
				+ "/documents";
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
	 * return "redirect:/system/organisations/settings/index";
	 * 
	 * }
	 */

}