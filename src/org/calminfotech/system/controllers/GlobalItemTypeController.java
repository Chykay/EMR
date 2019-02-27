package org.calminfotech.system.controllers;

import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GlobalItemTypeController {
	
	@Autowired
	private GlobalItemTypeBo catTypeBo;
	
	@RequestMapping(value = "/categorytype/index", method = RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("catType", catTypeBo.fetchAll());
		return "system/categorytype/index";
	}

}
