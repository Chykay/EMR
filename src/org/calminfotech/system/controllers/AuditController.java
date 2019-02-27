package org.calminfotech.system.controllers;

import java.util.List;

import org.calminfotech.system.boInterface.AuditBo;
import org.calminfotech.system.models.Audit;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/umgt/audits")
public class AuditController {

	@Autowired
	private AuditBo auditBo;

	@Autowired
	private Alert alert;

	@Layout(value = "layouts/datatable")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexAction() {

		List<Audit> audits = auditBo.fetchAll();
		ModelAndView mv = new ModelAndView("system/umgt/audits/index");
		mv.addObject("audits", audits);
		return mv;
	}

	@RequestMapping(value = "/view/{auditId}", method = RequestMethod.GET)
	public String viewAction(@PathVariable("auditId") Integer auditId,
			final RedirectAttributes redirectAttributes, Model model) {

		Audit audit = auditBo.findByAuditId(auditId);
		if (audit == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Resources not found!");
			return "redirect:/system/audits";
		}
		model.addAttribute("audit", audit);

		return "system/umgt/audits/view";
	}

}
