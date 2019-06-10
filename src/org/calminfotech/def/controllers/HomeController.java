package org.calminfotech.def.controllers;

import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/","/system"})
public class HomeController {

	/*private Organisation organisation;*/
	
	@Autowired
	UserIdentity userIdentity;
	
	@Autowired
	private UserBo userBo;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		//return "redirect: system/umgt/permission";
		
		
		User user = this.userBo.getUserById(2);
		
		this.userIdentity.setUser(user);
		this.userIdentity.setOrganisation(user.getOrganisation());
		
		return "default/index";
	}

}


