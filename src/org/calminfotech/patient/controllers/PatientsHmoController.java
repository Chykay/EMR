package org.calminfotech.patient.controllers;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.calminfotech.hmo.boInterface.HmoBo;
import org.calminfotech.hmo.boInterface.HmoPackageBo;
import org.calminfotech.hmo.models.HmoPackage;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.patient.boInterface.PatientPaymentOptionBo;
import org.calminfotech.patient.boInterface.PatientSearchBo;
import org.calminfotech.patient.forms.PatientHmoForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHmo;
import org.calminfotech.patient.utils.PatientCodeGenerator;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BloodgenotypeList;
import org.calminfotech.utils.BloodgroupList;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.HmostatusList;
import org.calminfotech.utils.LifestatusList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.OccupationList;
import org.calminfotech.utils.PhoneTypeList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.Hmostatus;
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
public class PatientsHmoController {

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
	private HmostatusList hmoList;

	@Autowired
	private StatesList stateList;

	@Autowired
	private HmoBo hmoBo;

	/*
	 * @Autowired private EhmoBo ehmoBo;
	 */

	@Autowired
	private PhoneTypeList PhonetypeBo;

	@Autowired
	private HmoPackageBo hmoPackageBo;

	@Autowired
	private HmostatusList hmostatusListBo;

	@Autowired
	private PatientPaymentOptionBo patientPaymentOptionBo;

	@Autowired
	private PatientSearchBo searchBo;

	@Autowired
	private PatientCodeGenerator patientCodeGenerator;

	@Autowired
	private PatientHmoBo patientHmoBo;

	@RequestMapping(value = "/hmo/save", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String addHmo(@ModelAttribute("hmoForm") PatientHmoForm hmoForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		PatientHmo patienthmo = new PatientHmo();

		Patient patient = patientBo.getPatientById(hmoForm.getPatientId());

		HmoPackage hmoPackage = hmoPackageBo.getHmoPackageById(hmoForm
				.getPackageId());

		patienthmo.setPatient(patient);
		patienthmo.setHmoPackage(hmoPackage);
		patienthmo.setPatienthmocode(hmoForm.getPatienthmocode());
		patienthmo.setHmostatus(hmostatusListBo.getHmostatusById(hmoForm
				.getHmostatusId()));
		if (!hmoForm.getFromdat().equals("")
				&& DateUtils.isValidDate(hmoForm.getFromdat()))

		{
			patienthmo.setFromdat(DateUtils.formatStringToDate(hmoForm
					.getFromdat()));
		}

		if (!hmoForm.getTodat().equals("")
				&& DateUtils.isValidDate(hmoForm.getTodat()))

		{
			patienthmo
					.setTodat(DateUtils.formatStringToDate(hmoForm.getTodat()));
		}

		patienthmo.setCreatedBy(userIdentity.getUsername());
		patienthmo.setCreatedDate(new GregorianCalendar().getTime());

		patienthmo.setOrganisation(userIdentity.getOrganisation());

		patientHmoBo.save(patienthmo);

		return "redirect:/patients/view/"
				+ patienthmo.getPatient().getPatientId();

	}

	@RequestMapping(value = "/hmo/edit/{id}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String geteditHmo(@PathVariable("id") Integer id,
			@ModelAttribute("hmoForm") PatientHmoForm hmoForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		PatientHmo patienthmo = patientHmoBo.getPatientHmoById(id);
		// List<Phonetype> hmotype = hmotypeBo.fetchAll();

		hmoForm.setId(patienthmo.getId());
		hmoForm.setPatienthmocode(patienthmo.getPatienthmocode());
		hmoForm.setHmostatusId(patienthmo.getHmostatus().getHmostatus_id());
		// hmoForm.setPhonetypeId(patienthmo.getPhoneType().getPhonetypeId());
		// hmoForm.setTelnumber(patienthmo.getTelnumber());
		hmoForm.setPackageId(patienthmo.getHmoPackage().getId());

		hmoForm.setFromdat(DateUtils.formatDateToString(patienthmo.getFromdat()));
		hmoForm.setTodat(DateUtils.formatDateToString(patienthmo.getTodat()));

		model.addAttribute("hmostatus", this.hmoList.fetchAll());

		// model.addAttribute("hmotypelist",hmotype);

		this.auditor.before(request, "Patient Hmo", hmoForm);
		return "customers/patientshmo/edit";

	}

	@RequestMapping(value = "/hmo/edit/{id}", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String posteditHmo(@PathVariable("id") Integer id,
			@ModelAttribute("hmoForm") PatientHmoForm hmoForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		PatientHmo patienthmo = patientHmoBo.getPatientHmoById(id);
		// patienthmo.setTelnumber(hmoForm.getTelnumber());
		// patienthmo.setPhoneType(hmotypeBo.getPhoneTypeById(hmoForm.getPhonetypeId()));
		patienthmo.setPatienthmocode(hmoForm.getPatienthmocode());
		patienthmo.setHmostatus(this.hmostatusListBo.getHmostatusById(hmoForm
				.getHmostatusId()));
		// hmoForm.setPhonetypeId(patienthmo.getPhoneType().getPhonetypeId());
		// hmoForm.setTelnumber(patienthmo.getTelnumber());
		// patienthmo.setPackageId(patienthmo.getHmoPackage().getId());

		patienthmo
				.setFromdat(DateUtils.formatStringToDate(hmoForm.getFromdat()));
		patienthmo.setTodat(DateUtils.formatStringToDate(hmoForm.getTodat()));

		patienthmo.setModifiedBy(userIdentity.getUsername());

		patienthmo.setModifiedDate(new GregorianCalendar().getTime());
		patientHmoBo.update(patienthmo);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Hmo  Updated");
		this.auditor.after(request, "Patient Hmo", hmoForm,
				this.userIdentity.getUsername(), id);

		return "redirect:/patients/view/"
				+ patienthmo.getPatient().getPatientId();

	}

	@RequestMapping(value = "/hmo/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete1(@PathVariable("id") Integer id,
			@ModelAttribute("hmoForm") PatientHmoForm hmoForm,
			RedirectAttributes redirectAttributes) {

		PatientHmo patienthmo = patientHmoBo.getPatientHmoById(id);

		patienthmo.setModifiedBy(userIdentity.getUsername());
		patienthmo.setModifiedDate(new GregorianCalendar().getTime());
		patienthmo.setDeleted(true);
		patientHmoBo.update(patienthmo);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Hmo  deleted");
		return "redirect:/patients/view/"
				+ patienthmo.getPatient().getPatientId();

	}

	@RequestMapping(value = "/hmo/disable/{id}", method = RequestMethod.GET)
	public String confirmDisable(@PathVariable("id") Integer id,
			HttpServletRequest request,
			@ModelAttribute("hmoForm") PatientHmoForm hmoForm,
			RedirectAttributes redirectAttributes) {

		PatientHmo patienthmo = patientHmoBo.getPatientHmoById(id);

		// PatientHmoForm hmoForm= new PatientHmoForm();
		// hmoForm.setHmostatusId(hmostatusId)
		this.auditor.before(request, "Patient Hmo", hmoForm);

		if (patienthmo.getHmostatus().getHmostatus_id() == 1) {
			Hmostatus hmostatus = hmostatusListBo.getHmostatusById(2);
			patienthmo.setHmostatus(hmostatus);

		}

		else

		{
			Hmostatus hmostatus = hmostatusListBo.getHmostatusById(1);
			patienthmo.setHmostatus(hmostatus);
		}

		patienthmo.setModifiedBy(userIdentity.getUsername());
		patienthmo.setModifiedDate(new GregorianCalendar().getTime());

		patientHmoBo.update(patienthmo);

		this.auditor.after(request, "Patient Hmo", hmoForm,
				this.userIdentity.getUsername(), id);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Hmo  deleted");
		return "redirect:/patients/view/"
				+ patienthmo.getPatient().getPatientId();

	}

}
