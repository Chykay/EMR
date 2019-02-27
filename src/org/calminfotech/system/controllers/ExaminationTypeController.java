package org.calminfotech.system.controllers;


import org.calminfotech.system.boInterface.ExaminationTypeBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class ExaminationTypeController {

	@Autowired
	private ExaminationTypeBo examinationTypeBo;
	
	@RequestMapping(value = "/examinationtype/index", method = RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("examinationType", examinationTypeBo.fetchAll());
		return "system/examinationtype/index";
	}
}
