package org.calminfotech.hrunit.controllers;

import org.calminfotech.system.boInterface.GlobalItemBo;

import org.calminfotech.system.boInterface.GlobalItemUnitofMeasureBo;
import org.calminfotech.system.forms.UnitToItemForm;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/system/measurement/unittoitem")
public class UnitItemController {
	
	@Autowired
	private GlobalItemUnitofMeasureBo unit;
	
	@Autowired
	private GlobalItemBo item;
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model){
		UnitToItemForm unitToItemForm = new UnitToItemForm();
		model.addAttribute("unitList", unit.fetchAll());
		//model.addAttribute("itemList", item.fetchAllByOrganisation());
		model.addAttribute("unitToItemForm", unitToItemForm);
		return "/system/measurement/unittoitem/index";
	}
}
