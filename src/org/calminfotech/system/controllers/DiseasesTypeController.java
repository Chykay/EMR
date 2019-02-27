package org.calminfotech.system.controllers;


import org.calminfotech.system.boInterface.DiseasesTypeBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class DiseasesTypeController {

	@Autowired
	private DiseasesTypeBo diseasesTypeBo;
	
	@RequestMapping(value = "/diseasestype/index", method = RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("diseasesType", diseasesTypeBo.fetchAll());
		return "system/diseasestype/index";
	}
}
