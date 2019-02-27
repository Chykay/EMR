package org.calminfotech.patient.controllers;

import java.util.List;

import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientSearchBo;
import org.calminfotech.patient.boInterface.PatientRelatedBo;
import org.calminfotech.patient.forms.PatientSearchForm;

import org.calminfotech.system.boInterface.LanguageBo;
import org.calminfotech.system.boInterface.TitleBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.GenderList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchRelPatientController {
	
	
	@Autowired
	private PatientRelatedBo patientRelatedBo;
	
	@Autowired
	UserIdentity userIdentity;
	
	@Autowired
	private PatientBo patientBo;
	
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
	private Alert alert;
	
	@Autowired
	private PatientSearchBo searchBo;
	
	@RequestMapping(value = "/getRelatedPatient", method = RequestMethod.GET)
	@Layout(value = "layouts/blank")
	public String patientSearch( Model model) {
		model.addAttribute("patientstable",
				this.patientBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("patientSearch", new PatientSearchForm());
		return "customers/relatedpatient/searchrelation";
	}
	
	/*
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@Layout(value = "layouts/blank")
	public String searchPatient(
			@ModelAttribute("patientSearch") PatientSearchForm searchForm,
			Model model) {

		List patientList = searchBo.searchPatient(searchForm);
		model.addAttribute("patient", patientList);
		return "customers/relatedpatient/searchrelresult";
	}
*/
}
