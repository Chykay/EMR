package org.calminfotech.visitqueue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//import org.calminfotech.system.boInterface.LoginSectionBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.visitqueue.forms.VisitWorkflowPointForm;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/setting/visitworkflows")
public class VisitWorkflowPointsController {

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private VisitWorkflowPointBo visitWorkflowPointBo;

	
	
	
	
	
	
	
	@Layout(value = "layouts/datatable")
	@RequestMapping(value = { "", "/index" })
	public String indexAction(Model model) {

		model.addAttribute("points",
				this.visitWorkflowPointBo.fetchAll());

		return "system/setting/visitworkflows/index";
	}

	@RequestMapping(value = "add")
	public String addAction(Model model) {

		model.addAttribute("dForm", new VisitWorkflowPointForm());
	//	model.addAttribute("sections", this.loginSectionBo.fetchAllByOrganisation());
		return "system/setting/visitworkflows/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("dForm") VisitWorkflowPointForm form,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "system/setting/visitworkflows/add";
		}
		System.out.println("Kunle save ====="+form.getSection());
		VisitWorkflowPoint point = this.visitWorkflowPointBo.save(form);

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Visit Workflow Point saved!");

		return "redirect:/system/setting/visitworkflows/view/" + point.getId();
	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

	VisitWorkflowPoint point = this.visitWorkflowPointBo
				.getWorkflowPointById(id);
		// Add to form
		if (null == point) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid Resource");
			return "redirect:/system/setting/visitworkflows";
		}

		VisitWorkflowPointForm form = new VisitWorkflowPointForm();
		//form.setId(point.getId());
		//form.setPointName(point.getName());

		model.addAttribute("dForm", form);

		this.auditor.before(request, "Visit Workflow Point Form", form);

		return "system/setting/visitworkflows/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("dForm") VisitWorkflowPointForm form,
			BindingResult result, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			return "system/setting/visitworkflows/edit";
		}

		//this.WorkflowPointBo.update(form);

		this.auditor.after(request, "Visit Workflow Point Form", form,
				this.userIdentity.getUsername(),form.getId());

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Visit Workflow Point updated!");

		return "redirect:/system/setting/visitworkflows/view/" + form.getId();
	} 

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		VisitWorkflowPoint point = this.visitWorkflowPointBo
				.getWorkflowPointById(id);

		if (null == point) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid Resource");
			return "redirect:/system/setting/visitworkflows";
		}

		model.addAttribute("point", point);
		//System.out.println(point.getPointUsers().size());

		return "system/setting/visitworkflows/view";
	}
}
