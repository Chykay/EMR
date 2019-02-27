package org.calminfotech.system.controllers;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.forms.GetSettingAssignmentProcForm;
import org.calminfotech.system.forms.OrganisationSettingForm;
import org.calminfotech.system.models.GetsettingsAssignmentProc;
import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.system.models.SettingsAssignment_log;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/setting")
public class SettingController {

	@Autowired
	private SettingBo settingBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String indexAction(Model model, HttpServletRequest request) {

		OrganisationSettingForm organisationSettingForm = new OrganisationSettingForm();
		OrganisationCompany organisation = this.userIdentity.getOrganisation()
				.getOrgCoy();

		organisationSettingForm.setName(organisation.getName());
		organisationSettingForm.setAddress(organisation.getAddress());
		organisationSettingForm.setSystemEmail(organisation.getEmail());
		// organisationSettingForm.setConsultationCode(organisation.getConsultationCode());

		auditor.before(request, "Organisation Settings Form",
				organisationSettingForm);

		model.addAttribute("settingForm", organisationSettingForm);
		return "system/setting/index";
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public String update(
			@Valid @ModelAttribute("settingForm") OrganisationSettingForm organisationSettingForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "system/setting/index";
		}

		// Update settings
		settingBo.update(organisationSettingForm);

		auditor.after(request, "Organisation Settings Form",
				organisationSettingForm, userIdentity.getUsername(), 0);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Organisation details Updated!");
		return "redirect:/system/setting";
	}

	@RequestMapping(value = "/entersetting", method = RequestMethod.GET)
	@Layout(value = "layouts/datatable")
	public String enterLabResult(Model model,
			RedirectAttributes redirectAttributes) {

		List<GetsettingsAssignmentProc> settingAssignmentProc = settingBo
				.fetchallsettingsbyorg(userIdentity.getOrganisation()
						.getOrgCoy().getId());

		GetSettingAssignmentProcForm sForm = new GetSettingAssignmentProcForm();
		// lForm.setConsultation_prescribed_id(id);
		// lForm.setVisit_id(vid);

		model.addAttribute("sForm", sForm);
		model.addAttribute("settingslist", settingAssignmentProc);

		return "system/organisation/settings/index";
	}

	@RequestMapping(value = "/entersetting", method = RequestMethod.POST)
	public String saveLabResult(
			@ModelAttribute("sForm") GetSettingAssignmentProcForm sForm,
			Model model, RedirectAttributes redirectAttributes)

	{

		System.out.println("Deleting");
		// System.out.print(sForm.getTestId());

		this.settingBo.deleteassingment(userIdentity.getOrganisation()
				.getOrgCoy().getId().intValue());
		System.out.println("Deleted");
		String[] settings_code = sForm.getSettings_code();
		String[] settings_value = sForm.getSettings_value();
		// String[] finalResults = lForm.getFinalResult();
		HashMap<String, String> hash = new HashMap();

		// iterate array list
		int ct = 1;

		for (String code : settings_code) {
			hash.put("code" + ct, code);
			ct++;
		}

		// System.out.println("cod_values££");
		// System.out.println(cod_values.length);

		int ct2 = 1;
		for (String value : settings_value) {
			hash.put("value" + ct2, value);
			ct2++;
		}

		// saving here
		for (int i = 1; i <= settings_value.length; i++)

		{

			SettingsAssignment settings = new SettingsAssignment();
			SettingsAssignment_log settings_log = new SettingsAssignment_log();

			String code = hash.get("code" + i);
			String value = hash.get("value" + i);

			if (value.equals(null)) {
				value = "";
			}

			// int mysetupidint =Integer.parseInt(setupid);

			settings.setSettings_code(code);
			settings.setSettings_value(value);
			settings.setCreatedBy(userIdentity.getUsername());
			settings.setCreatedDate(new GregorianCalendar().getTime());
			settings.setOrganisation(userIdentity.getOrganisation().getOrgCoy());

			settings_log.setSettings_code(code);
			settings_log.setSettings_value(value);
			settings_log.setCreatedBy(userIdentity.getUsername());
			settings_log.setCreatedDate(new GregorianCalendar().getTime());
			settings_log.setOrganisation(userIdentity.getOrganisation()
					.getOrgCoy());

			this.settingBo.save(settings, settings_log);
			System.out.println("saved item");
		}

		return "redirect:/system/setting/entersetting";

	}

}
