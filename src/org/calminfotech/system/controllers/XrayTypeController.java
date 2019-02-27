package org.calminfotech.system.controllers;


import org.calminfotech.system.boInterface.XrayTypeBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class XrayTypeController {

	@Autowired
	private XrayTypeBo xrayTypeBo;
	
	@RequestMapping(value = "/xraytype/index", method = RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("xrayType", xrayTypeBo.fetchAll());
		return "system/xraytype/index";
	}
}
