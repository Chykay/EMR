package org.calminfotech.system.controllers;


import org.calminfotech.system.boInterface.AllergyTypeBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class AllergyTypeController {

	@Autowired
	private AllergyTypeBo allergyTypeBo;
	
	@RequestMapping(value = "/allergytype/index", method = RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("allergyType", allergyTypeBo.fetchAll());
		return "system/allergytype/index";
	}
}
