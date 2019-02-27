package org.calminfotech.error.controllers;

import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/error/errorPage")
public class GlobalExceptionController {

	@Autowired
	private UserIdentity userIdentity;

	// error redirect view page
	public static final String DEFAULT_ERROR_VIEW = "error/errorPage";

	@RequestMapping(method = RequestMethod.GET)
	public String indexAction(Model model) {
		model.addAttribute("exception", this.userIdentity.getException().getLocalizedMessage());
		model.addAttribute("errormessage", userIdentity.getErrormessage());
		model.addAttribute("url", userIdentity.getUrl());
		model.addAttribute("timestamp", userIdentity.getTimestamp());
		return "/error/errorPage";
	}
}
