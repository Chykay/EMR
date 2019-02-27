package org.calminfotech.system.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.ConsultationStatusBo;
import org.calminfotech.system.forms.ConsultationStatusForm;
import org.calminfotech.system.models.ConsultationStatus;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
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
@RequestMapping(value = "/system/consultation/statuses")
public class ConsultationStatusController {

	@Autowired
	private ConsultationStatusBo consultationStatusBo;

	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = { "", "/index" })
	public String indexAction(Model model) {

		model.addAttribute("statuses",
				this.consultationStatusBo.fetchAllByOrganisation());
		return "system/consultation/statuses/index";
	}

	// No need for it sha!
	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") int id, Model model,
			RedirectAttributes redirectAttributes) {
		ConsultationStatus status = this.consultationStatusBo.getStatusById(id);

		if (null == status) {
			this.alert.setAlert(redirectAttributes, Alert.WARNING,
					"Invalid Resource");
			return "redirect:/system/consultation/statuses/index";
		}

		model.addAttribute("status", status);
		return "system/consultation/statuses/view";
	}

	@RequestMapping(value = "/add")
	public String addAction(Model model) {
		ConsultationStatusForm dForm = new ConsultationStatusForm();

		model.addAttribute("dForm", dForm);
		return null;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("dForm") ConsultationStatusForm form,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "system/consultation/statuses/add";
		}

		this.consultationStatusBo.save(form);
		this.alert
				.setAlert(redirectAttributes, Alert.SUCCESS, "Status created");

		return "redirect:/system/consultation/statuses";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editAtcion(@PathVariable("id") int id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		ConsultationStatus status = this.consultationStatusBo.getStatusById(id);
		if (null == status) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid resource");
			return "redirect:/system/consultation/statuses/index";
		}

		ConsultationStatusForm dForm = new ConsultationStatusForm();
		dForm.setType(status.getType());
		//dForm.setId(status.getId());

		this.auditor.before(request, "Consultation Status", dForm);
		model.addAttribute("status", status);
		model.addAttribute("dForm", dForm);

		return "system/consultation/statuses/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("dForm") ConsultationStatusForm form,
			BindingResult result, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			return "system/consultation/statuses/edit";
		}

		this.consultationStatusBo.update(form);

		this.auditor.after(request, "Consultation Status", form,
				this.userIdentity.getUsername(),form.getId());
		this.alert
				.setAlert(redirectAttributes, Alert.SUCCESS, "Status updated");

		return "redirect:/system/consultation/statuses/index";
	}

	public String deleteAction() {
		// First check if any consultation is attached before deleting
		return null;
	}

	@RequestMapping(value = "/startpoint/{id}")
	public String startPointAction(@PathVariable("id") int id, Model model,
			RedirectAttributes redirectAttributes) {
		ConsultationStatus status = this.consultationStatusBo.getStatusById(id);
		if (null == status) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid resource");
			return "redirect:/system/consultation/statuses/index";
		}

		ConsultationStatusForm dForm = new ConsultationStatusForm();
	//	dForm.setId(status.getId());

		model.addAttribute("dForm", dForm);
		model.addAttribute("status", status);

		return "system/consultation/statuses/start_point";
	}

	@RequestMapping(value = "/startpoint/{id}", method = RequestMethod.POST)
	public String changeStartPoint(
			@ModelAttribute("dForm") ConsultationStatusForm form,
			RedirectAttributes redirectAttributes) {
		
		// Re-evaluate if on going process can affect!!!
		
		this.consultationStatusBo.changeStartPoint(form);

		this.alert
				.setAlert(redirectAttributes, Alert.SUCCESS, "Consultation Start point changed");
		return "redirect:/system/consultation/statuses/index";
	}
	


	@RequestMapping(value = "/endpoint/{id}")
	public String endPointAction(@PathVariable("id") int id, Model model,
			RedirectAttributes redirectAttributes) {
		ConsultationStatus status = this.consultationStatusBo.getStatusById(id);
		if (null == status) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid resource");
			return "redirect:/system/consultation/statuses/index";
		}

		ConsultationStatusForm dForm = new ConsultationStatusForm();
		dForm.setId(status.getId());

		//model.addAttribute("dForm", dForm);
		model.addAttribute("status", status);

		return "system/consultation/statuses/end_point";
	}

	@RequestMapping(value = "/endpoint/{id}", method = RequestMethod.POST)
	public String changeEndPoint(
			@ModelAttribute("dForm") ConsultationStatusForm form,
			RedirectAttributes redirectAttributes) {
		
		// Re-evaluate if on going process can affect!!!
		
		this.consultationStatusBo.changeEndPoint(form);

		this.alert
				.setAlert(redirectAttributes, Alert.SUCCESS, "Consultation End point changed");
		return "redirect:/system/consultation/statuses/index";
	}
}
