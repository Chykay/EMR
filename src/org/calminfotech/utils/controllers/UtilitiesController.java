package org.calminfotech.utils.controllers;

import java.util.List;
import java.util.Set;

import org.calminfotech.hrunit.models.Staffgroup;
import org.calminfotech.hrunit.models.Staffgroupranking;
import org.calminfotech.hrunit.models.Staffgroupspecialization;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.StaffgroupList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/utilities/staffgroup")
public class UtilitiesController {

	@Autowired
	private StaffgroupList staffgroupBo;
	

	@RequestMapping(value = "/{sg}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getRankandspecial(@PathVariable("sg") Integer sg) {
		String Strr = "<option value='0'>Select...</option>";
		Staffgroup staffgroup  = staffgroupBo.getStaffgroupById(sg);
		if (staffgroup == null)
			return Strr;
		
		
		for (Staffgroupranking sgr : staffgroup.getStaffgroupranking()) {
			Strr += "<option value='" + sgr.getId() + "'>"
					+ sgr.getName() + "</option>";
		}
		
		
String Strs = "<option value='0'>Select...</option>";
		


		for (Staffgroupspecialization sgs : staffgroup.getStaffgroupspecialization()) {
			Strs += "<option value='" + sgs.getId() + "'>"
					+ sgs.getName() + "</option>";
		}
		
		
		
		
		return Strr + "##" + Strs;
	}

	
}