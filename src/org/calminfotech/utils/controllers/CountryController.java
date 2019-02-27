package org.calminfotech.utils.controllers;

import org.calminfotech.utils.CountryList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CountryController {
	
	@Autowired
	private CountryList countryList;
	
	@RequestMapping(value = "/country", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String index(Model model){
		model.addAttribute("country", countryList.fetchAll());
		return "system/country/index";		
	}
	
	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
	public String getCountryById(@PathVariable("id")Long id, Model model){
		Country country = countryList.getCountryById(id);
		model.addAttribute("countryId", country);
		return null;		
	}
	
}
