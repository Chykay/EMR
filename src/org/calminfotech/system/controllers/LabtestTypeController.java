package org.calminfotech.system.controllers;


import org.calminfotech.system.boInterface.LabtestTypeBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class LabtestTypeController {

	@Autowired
	private LabtestTypeBo labtestTypeBo;
	
	@RequestMapping(value = "/labtesttype/index", method = RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("labtestType", labtestTypeBo.fetchAll());
		return "system/labtesttype/index";
	}
}
