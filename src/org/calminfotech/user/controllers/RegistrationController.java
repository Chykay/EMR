package org.calminfotech.user.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user/registration")
public class RegistrationController {

	// Display registration form
	@RequestMapping(method = RequestMethod.GET)
	public String registration() {
		return null;
	}

	// Process the registration form. Do email from here
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register() {
		return null;
	}

}
