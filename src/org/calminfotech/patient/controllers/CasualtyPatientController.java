package org.calminfotech.patient.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;




import org.calminfotech.patient.boInterface.CasualtyPatientBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.daoInterface.CasualtyPatientDao;
import org.calminfotech.patient.forms.CasualtyPatientForm;
import org.calminfotech.patient.models.CasPatient;
import org.calminfotech.system.boInterface.LanguageBo;
import org.calminfotech.system.boInterface.TitleBo;
import org.calminfotech.system.daoInterface.TitleDao;
import org.calminfotech.system.models.Gender;
import org.calminfotech.system.models.Title;
import org.calminfotech.patient.models.Patient;


import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.AutoGen;
import org.calminfotech.utils.GenderList;

import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.annotations.Layout;
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
@RequestMapping(value = "/customers/caspatient")
public class CasualtyPatientController {

	@Autowired
	private PatientBo patientBo; 

	@Autowired
	private CasualtyPatientBo casualtyPatientBo; 

	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;
 
	@Autowired 
	private Auditor auditor;

	@Autowired
	private LocalGovernmentAreaList lgasList;

	@Autowired
	private MaritalStatusList MSList;

	@Autowired
	private StatesList stateList;

	@Autowired
	private TitleBo titleBo;

	@Autowired
	private LanguageBo languageBo;

	@Autowired
	private GenderList genderList;

	@Autowired
	private CasualtyPatientDao casualtyPatientDao;

	@Autowired
	private TitleDao titleDao;

	@Autowired
	private MaritalStatusList maritalStatusList;

	@Autowired
	private LocalGovernmentAreaList lgaList;

	@RequestMapping(value = { "", "/" })
	@Layout("layouts/datatable")
	public String casualtyindexAction(Model model) {

		model.addAttribute("casualtypatientstable",
				this.casualtyPatientBo.fetchAllByOrganisation());

		return "customers/caspatient/index";
	}

	@RequestMapping(value = "/add")
	@Layout(value = "layouts/form_wizard_layout")
	public String addCasualtyPatientAction(Model model) {

		model.addAttribute("casForm", new CasualtyPatientForm());
		model.addAttribute("titles", this.titleBo.fetchAllByOrganisation());
		model.addAttribute("language", this.languageBo.fetchAllByOrganisation());
		model.addAttribute("genders", this.genderList.fetchAll());

		model.addAttribute("lgas", this.lgasList.fetchAll());
		model.addAttribute("states", this.stateList.fetchAll());
		model.addAttribute("maritalstat", this.MSList.fetchAll());

		return "customers/caspatient/add";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveCasualtyPatientAction(
			@Valid @ModelAttribute("casForm") CasualtyPatientForm casualtyPatientForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("titles", this.titleBo.fetchAllByOrganisation());
			model.addAttribute("languages",
					this.languageBo.fetchAllByOrganisation());
			model.addAttribute("genders", this.genderList.fetchAll());

			model.addAttribute("lgas", this.lgasList.fetchAll());
			model.addAttribute("states", this.stateList.fetchAll());

			return "customers/caspatient/add";
		}

		CasPatient casPatient = this.casualtyPatientBo
				.save(casualtyPatientForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! New Casualty Patient Succesfully Created! Patient id:  "
						+ casPatient.getPatient_id());

		return "redirect:/customers/caspatient/view/" + casPatient.getPatient_id();

	}

	// this is for view
	@RequestMapping(value = "/view/{id}")
	public String viewCasualtyPatientAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		CasPatient casPatient = this.casualtyPatientBo.getcasPatientById(id);
		System.out.println(casPatient.getSurname());
		if (null == casPatient) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/customers/caspatient";
		}
		// this is to show casualty date on modal transfer window
		CasualtyPatientForm casualtyPatientForm = new CasualtyPatientForm();
		casualtyPatientForm.setId(casPatient.getPatient_id());
		casualtyPatientForm.setAddress(casPatient.getAddress());
		casualtyPatientForm.setEmail(casPatient.getEmail());
		casualtyPatientForm.setOthernames(casPatient.getOthernames());

		casualtyPatientForm.setSurname(casPatient.getSurname());
		casualtyPatientForm.setGenotype(casPatient.getGenotype());
		casualtyPatientForm.setBldgrp(casPatient.getBldgrp());
		casualtyPatientForm.setHeight(casPatient.getHeight());
		casualtyPatientForm.setPhoneNumber(casPatient.getPhoneNumber());
		casualtyPatientForm.setHomeNumber(casPatient.getHomeNumber());

		casualtyPatientForm.setTitleId(casPatient.getTitle().getId());
		casualtyPatientForm.setGenderId(casPatient.getGender().getId());

		model.addAttribute("caspxform", casualtyPatientForm);
		model.addAttribute("titles", this.titleBo.fetchAllByOrganisation());
		model.addAttribute("genders", this.genderList.fetchAll());
		// modal show ends here

		model.addAttribute("cascaspatient", casPatient);

		return "customers/caspatient/view";
	}

	// this is for the real transfer

	@RequestMapping(value = "/view/{id}", method = RequestMethod.POST)
	public String portCasualty(
			@Valid @ModelAttribute("caspxform") CasualtyPatientForm casualtyPatientForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		// CasPatient casPatient = this.casualtyPatientBo.getcasPatientById(id);

		if (result.hasErrors()) {
			model.addAttribute("titles", this.titleBo.fetchAllByOrganisation());
			model.addAttribute("languages",
					this.languageBo.fetchAllByOrganisation());
			model.addAttribute("genders", this.genderList.fetchAll());

			model.addAttribute("lgas", this.lgasList.fetchAll());
			model.addAttribute("states", this.stateList.fetchAll());

			// return "redirect:/customers/caspatient/view/" +
			// casPatient.getItemId();
			return "redirect:/customers/caspatient";
		}

		Patient patient = new Patient();

		//Patient.setPatient_id(new AutoGen().genNum());

		patient.setSurname(casualtyPatientForm.getSurname());
		patient.setOthernames(casualtyPatientForm.getOthernames());

		patient.setEmail(casualtyPatientForm.getEmail());
		//patient.setAddress(casualtyPatientForm.getAddress());
	//	patient.setPhoneNumber(casualtyPatientForm.getPhoneNumber());
		//patient.setDob(casualtyPatientForm.getDob());
		//patient.setBldgrp(casualtyPatientForm.getBldgrp());
		//patient.setGenotype(casualtyPatientForm.getGenotype());
		//patient.setHeight(casualtyPatientForm.getHeight());j

		/*
		 * Patient.setLga(this.lgaList.getLgaById(casualtyPatientForm.getLgaId())
		 * ); Patient.setState(this.stateList.getStateById(casualtyPatientForm.
		 * getStateId()));
		 * Patient.setMaritalStatus(this.maritalStatusList.getMartialStatusById
		 * (casualtyPatientForm.getStatusId()));
		 */
	//	patient.setCreatedBy(userIdentity.getUserId());
		//patient.setHomeNumber(casualtyPatientForm.getHomeNumber());

		Title title = this.titleDao.getTitleById(casualtyPatientForm
				.getTitleId());
		//patient.setTitle(title);

		Gender gender = this.genderList.getGenderById(casualtyPatientForm
				.getGenderId());
		//patient.setGender(gender);

	//	patient.setStatus("Active");

		patient.setOrganisation(userIdentity.getOrganisation());

		this.patientBo.save(patient);

		// ends
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Record Transferred successfully");

		return "redirect:/customers/patients/view/" + patient.getPatientId();
	}

	// the porting stops here

	@RequestMapping(value = "/transfer/{id}")
	public String editCasualtyAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		CasPatient caspatient = this.casualtyPatientBo.getcasPatientById(id);
		if (null == caspatient) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/customers/caspatient";
		}

		CasualtyPatientForm casualtyPatientForm = new CasualtyPatientForm();
		casualtyPatientForm.setId(caspatient.getPatient_id());
		casualtyPatientForm.setAddress(caspatient.getAddress());
		casualtyPatientForm.setEmail(caspatient.getEmail());
		casualtyPatientForm.setOthernames(caspatient.getOthernames());

		casualtyPatientForm.setSurname(caspatient.getSurname());
		casualtyPatientForm.setGenotype(caspatient.getGenotype());
		casualtyPatientForm.setBldgrp(caspatient.getBldgrp());
		casualtyPatientForm.setHeight(caspatient.getHeight());
		casualtyPatientForm.setPhoneNumber(caspatient.getPhoneNumber());
		casualtyPatientForm.setHomeNumber(caspatient.getHomeNumber());

		casualtyPatientForm.setTitleId(caspatient.getTitle().getId());

		if (null != caspatient.getGender())
			casualtyPatientForm.setGenderId(caspatient.getGender().getId());

		model.addAttribute("caspForm", casualtyPatientForm);
		model.addAttribute("titles", this.titleBo.fetchAllByOrganisation());
		model.addAttribute("genders", this.genderList.fetchAll());
		model.addAttribute("lgas", this.lgasList.fetchAll());
		model.addAttribute("states", this.stateList.fetchAll());
		model.addAttribute("maritalstat", this.MSList.fetchAll());

		auditor.before(request, "Casualty Patient Form", casualtyPatientForm);

		return "customers/caspatient/transfer";
	}

	@RequestMapping(value = "/transfer/{id}", method = RequestMethod.POST)
	public String updateCasualtyAction(
			@Valid @ModelAttribute("caspForm") CasualtyPatientForm casualtyPatientForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		// System.out.println("errror!");
		if (result.hasErrors()) {
			model.addAttribute("titles", this.titleBo.fetchAllByOrganisation());
			System.out.println("errror!");
			return "customers/caspatient/transfer";
		}

		this.casualtyPatientBo.update(casualtyPatientForm);
		auditor.after(request, "Casualty Patient Form", casualtyPatientForm,
				userIdentity.getUsername(),casualtyPatientForm.getId());
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Casualty Patient profile updated");
		return "redirect:/customers/caspatient/view/"
				+ casualtyPatientForm.getId();
	}

}
