package org.calminfotech.hrunit.controllers;


import org.calminfotech.hrunit.boInterface.HrunitTypeBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class HrunitTypeController {

	@Autowired
	private HrunitTypeBo hrunitTypeBo;
	
	@RequestMapping(value = "/hrunittype/index", method = RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("hrunitType", hrunitTypeBo.fetchAll());
		return "system/hrunittype/index";
	}
}
