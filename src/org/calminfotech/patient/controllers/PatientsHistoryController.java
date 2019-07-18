package org.calminfotech.patient.controllers;

import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.calminfotech.hmo.boInterface.HmoBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientHistoryBo;
import org.calminfotech.patient.boInterface.PatientPaymentOptionBo;
import org.calminfotech.patient.boInterface.PatientSearchBo;
import org.calminfotech.patient.forms.PatientHistoryForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHistory;
import org.calminfotech.patient.utils.PatientCodeGenerator;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BloodgenotypeList;
import org.calminfotech.utils.BloodgroupList;
import org.calminfotech.utils.HistoryTypeList;
import org.calminfotech.utils.LifestatusList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.OccupationList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.Historytype;
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
public class PatientsHistoryController {

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
	private StatesList stateList;

	@Autowired
	private HmoBo hmoBo;

	/*
	 * @Autowired private EhmoBo ehmoBo;
	 */

	@Autowired
	private HistoryTypeList historytypeBo;

	@Autowired
	private PatientPaymentOptionBo patientPaymentOptionBo;

	@Autowired
	private PatientSearchBo searchBo;

	@Autowired
	private PatientCodeGenerator patientCodeGenerator;

	@Autowired
	private PatientHistoryBo patientHistoryBo;

	@RequestMapping(value = "/history/save", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String addHistory(
			@ModelAttribute("histForm") PatientHistoryForm historyForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		PatientHistory patienthist = new PatientHistory();
		Patient patient = patientBo.getPatientById(historyForm.getPatientId());

		Historytype historytype = historytypeBo.getHistoryTypeById(historyForm
				.getHistorytypeId());

		patienthist.setPatient(patient);
		patienthist.setHistory(historyForm.getHistory());
		patienthist.setHistoryType(historytype);

		patienthist.setCreatedBy(userIdentity.getUsername());
		patienthist.setCreatedDate(new GregorianCalendar().getTime());

		patienthist.setOrganisation(userIdentity.getOrganisation());

		patientHistoryBo.save(patienthist);

		return "redirect:/patients/view/"
				+ patienthist.getPatient().getPatientId();

	}

	@RequestMapping(value = "/history/edit/{id}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String geteditHistory(@PathVariable("id") Integer id,
			@ModelAttribute("histForm") PatientHistoryForm historyForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		PatientHistory patienthist = patientHistoryBo.getPatientHistoryById(id);
		List<Historytype> historytype = historytypeBo.fetchAll();

		historyForm.setId(patienthist.getId());
		historyForm.setHistorytypeId(patienthist.getHistoryType()
				.getHistorytypeId());
		historyForm.setHistory(patienthist.getHistory());

		model.addAttribute("historytypelist", historytype);

		this.auditor.before(request, "Patient History", historyForm);
		return "customers/patientshistory/edit";

	}

	@RequestMapping(value = "/history/edit/{id}", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String posteditHistory(@PathVariable("id") Integer id,
			@ModelAttribute("histForm") PatientHistoryForm historyForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		PatientHistory patienthist = patientHistoryBo.getPatientHistoryById(id);

		patienthist.setHistory(historyForm.getHistory());
		patienthist.setHistoryType(historytypeBo.getHistoryTypeById(historyForm
				.getHistorytypeId()));

		patienthist.setModifiedBy(userIdentity.getUsername());

		patienthist.setModifiedDate(new GregorianCalendar().getTime());
		patientHistoryBo.update(patienthist);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! History  Updated");
		this.auditor.after(request, "Patient History", historyForm,
				this.userIdentity.getUsername(), id);

		return "redirect:/patients/view/"
				+ patienthist.getPatient().getPatientId();

	}

	@RequestMapping(value = "/history/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
			@ModelAttribute("histForm") PatientHistoryForm historyForm,
			RedirectAttributes redirectAttributes) {

		PatientHistory patienthist = patientHistoryBo.getPatientHistoryById(id);

		patienthist.setModifiedBy(userIdentity.getUsername());
		patienthist.setModifiedDate(new GregorianCalendar().getTime());
		patienthist.setDeleted(true);
		patientHistoryBo.update(patienthist);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! History  deleted");
		return "redirect:/patients/view/"
				+ patienthist.getPatient().getPatientId();

	}

}
