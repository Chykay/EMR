package org.calminfotech.patient.controllers;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.calminfotech.hmo.boInterface.HmoBo;
import org.calminfotech.patient.boInterface.PatientAllergyBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientPaymentOptionBo;
import org.calminfotech.patient.boInterface.PatientSearchBo;
import org.calminfotech.patient.forms.PatientAllergyForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.utils.PatientCodeGenerator;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.AllergyList;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BloodgenotypeList;
import org.calminfotech.utils.BloodgroupList;
import org.calminfotech.utils.LifestatusList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.OccupationList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.Allergywinsearch;
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
@RequestMapping(value = "/patients")
public class PatientsAllergyController {

	@Autowired
	private PatientBo patientBo;

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
	private OccupationList occuList;

	@Autowired
	private BloodgroupList groupList;

	@Autowired
	private BloodgenotypeList genoList;

	@Autowired
	private LifestatusList lifeList;

	@Autowired
	private AllergyList allergyBo;

	@Autowired
	private GlobalItemBo globalitemBo;

	@Autowired
	private StatesList stateList;

	@Autowired
	private HmoBo hmoBo;

	/*
	 * @Autowired private EhmoBo ehmoBo;
	 */

	// @Autowired
	// private SearchUtility patientallergyBo;

	@Autowired
	private PatientPaymentOptionBo patientPaymentOptionBo;

	@Autowired
	private PatientSearchBo searchBo;

	@Autowired
	private PatientCodeGenerator patientCodeGenerator;

	@Autowired
	private PatientAllergyBo patientAllergyBo;

	@RequestMapping(value = "/allergy/save", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String addAllergy(
			@ModelAttribute("allergyForm") PatientAllergyForm allergyForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		PatientAllergy patientallergy = new PatientAllergy();
		Patient patient = patientBo.getPatientById(allergyForm.getPatientId());

		Allergywinsearch allergy = allergyBo.getAllergyById(allergyForm
				.getAllergyId());

		GlobalItem globalitem = globalitemBo.getGlobalItemById(allergyForm
				.getAllergyId());

		patientallergy.setPatient(patient);
		patientallergy.setAllergy(globalitem);
		patientallergy.setReaction(allergyForm.getReaction());
		patientallergy.setCreatedBy(userIdentity.getUsername());
		patientallergy.setCreateDate(new GregorianCalendar().getTime());

		patientallergy.setOrganisation(userIdentity.getOrganisation());

		patientAllergyBo.save(patientallergy);

		return "redirect:/patients/view/"
				+ patientallergy.getPatient().getPatientId();

	}

	@RequestMapping(value = "/allergy/edit/{id}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String geteditAllergy(@PathVariable("id") Integer id,
			@ModelAttribute("allergyForm") PatientAllergyForm allergyForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		PatientAllergy patientallergy = patientAllergyBo
				.getPatientAllergyById(id);
		// List<Allergytype> allergytype = allergytypeBo.fetchAll();

		allergyForm.setId(patientallergy.getId());
		allergyForm.setAllergyId(patientallergy.getAllergy().getItemId());

		// allergyForm.setAllergyname(patientallergy.getAllergy().getName());
		allergyForm.setReaction(patientallergy.getReaction());

		model.addAttribute("allergyname", patientallergy.getAllergy()
				.getGlobalitemName());

		this.auditor.before(request, "Patient Allergy", allergyForm);
		return "customers/patientsallergy/edit";

	}

	@RequestMapping(value = "/allergy/edit/{id}", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String posteditAllergy(@PathVariable("id") Integer id,
			@ModelAttribute("allergyForm") PatientAllergyForm allergyForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		PatientAllergy patientallergy = patientAllergyBo
				.getPatientAllergyById(id);

		// patientallergy.setPatient(patientBo.getPatientById(allergyForm.getPatientId());

		patientallergy.setAllergy(globalitemBo.getGlobalItemById(allergyForm
				.getAllergyId()));

		patientallergy.setReaction(allergyForm.getReaction());
		patientallergy.setModifiedBy(userIdentity.getUsername());

		patientallergy.setModifiedDate(new GregorianCalendar().getTime());
		patientAllergyBo.update(patientallergy);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Allergy  Updated");
		this.auditor.after(request, "Patient Allergy", allergyForm,
				this.userIdentity.getUsername(), id);

		return "redirect:/patients/view/"
				+ patientallergy.getPatient().getPatientId();

	}

	@RequestMapping(value = "/allergy/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
			@ModelAttribute("allergyForm") PatientAllergyForm allergyForm,
			RedirectAttributes redirectAttributes) {

		PatientAllergy patientallergy = patientAllergyBo
				.getPatientAllergyById(id);

		patientallergy.setModifiedBy(userIdentity.getUsername());
		patientallergy.setModifiedDate(new GregorianCalendar().getTime());
		patientallergy.setDeleted(true);
		patientAllergyBo.update(patientallergy);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Allergy  deleted");
		return "redirect:/patients/view/"
				+ patientallergy.getPatient().getPatientId();

	}

}
