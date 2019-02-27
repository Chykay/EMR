package org.calminfotech.user.controllers;

import java.util.List;

import org.calminfotech.user.boInterface.UserLoginSessionBo;
import org.calminfotech.user.models.UserLoginSession;
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
@RequestMapping(value = "/system/umgt/loginSession")
public class UserLoginSessionController {

	@Autowired
	private UserLoginSessionBo userLoginSessionBo;

	@Autowired
	private Alert alert;

	@Layout(value = "layouts/datatable")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexAction() {

		List<UserLoginSession> logTrails = userLoginSessionBo.fetchAll();
		ModelAndView mv = new ModelAndView("system/umgt/userLoginSession/index");
		mv.addObject("logTrails", logTrails);
		return mv;
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String viewAction(@PathVariable("id") Integer id,
			final RedirectAttributes redirectAttributes, Model model) {

		UserLoginSession loginTrail = this.userLoginSessionBo.getUserLoginSessionById(id);
		if (loginTrail == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Resources not found!");
			return "redirect:/system/audits";
		}
		model.addAttribute("loginTrail", loginTrail);

		return "system/umgt/userLoginSession/view";
	}

}
