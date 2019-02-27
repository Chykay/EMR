package org.calminfotech.utils.controllers;

import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.EmailDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/utilities/verifydomain")
public class DomainController {

	@Autowired
	private EmailDomain emailDomain;

	@Autowired
	private UserIdentity userIdentity;

	@ResponseBody
	@RequestMapping(value = "/organisationForm/{org}/{admin}/")
	public boolean verify(@PathVariable("org") String org,
			@PathVariable("admin") String admin) {
		boolean status = false;
		status = this.emailDomain.verification(org, admin);
		return status;
	}

	@ResponseBody
	@RequestMapping(value = "/userForm/{email}/")
	public boolean verificationFromDB(@PathVariable("email") String email) {
		boolean status = false;
		status = this.emailDomain.verification(email, userIdentity
				.getOrganisation().getOrgCoy().getDomain());
		return status;
	}

}
