package org.calminfotech.system.controllers;


import org.calminfotech.system.boInterface.BedTypeBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class BedTypeController {

	@Autowired
	private BedTypeBo bedTypeBo;
	
	@RequestMapping(value = "/bedtype/index", method = RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("bedType", bedTypeBo.fetchAll());
		return "system/bedtype/index";
	}
}
