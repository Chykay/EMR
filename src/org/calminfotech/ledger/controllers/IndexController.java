package org.calminfotech.ledger.controllers;

import java.util.List;

import org.calminfotech.ledger.daoImpl.PermImpl;
import org.calminfotech.ledger.models.GPermission;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Layout(value = "layouts/datatable")
@RequestMapping(value = "/ledger")
public class IndexController {
	
	@Autowired
	private PermImpl permImpl;
	

	@RequestMapping(value = {"/permissions"}, method=RequestMethod.GET)
	public String perm(Model model) {
		List<GPermission> gPermissions = this.permImpl.fetchAll();
		model.addAttribute("permissions", gPermissions);
		return "/ledger/permission";
	}
	
	
}
