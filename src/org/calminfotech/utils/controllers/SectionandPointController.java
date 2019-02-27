/*
package org.calminfotech.utils.controllers;

import java.util.List;
import java.util.Set;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemType;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.system.models.LoginSectionPoint;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.calminfotech.utils.GlobalItemTypeList;
import org.calminfotech.utils.LoginSectionPointList;
import org.calminfotech.utils.SectionList;
import org.calminfotech.utils.WorkflowPointList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/utilities/section")
public class SectionandPointController {
	
	@Autowired
	private SectionList sectionList; 
		
	@Autowired
	private LoginSectionPointList loginSectionPointList; 
	
	

	@RequestMapping(value = "/section", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getsection() {
		String SectionStr = "<option value='0'>Select Section</option>";
		List<LoginSection> sectnList = sectionList.fetchAll();
		for (LoginSection LS : sectnList) {
			SectionStr += "<option value='" + LS.getLoginSectionPoint() + "'>"
					+ LS.getSession_name() + "</option>";
		}
		return SectionStr;
	}
	
	@RequestMapping(value = "/sectionpoint", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getsectionpoint() {
		String PointStr = "<option value='0'>Select Section Points</option>";
		List<LoginSectionPoint> list = loginSectionPointList.fetchAll();
		for (LoginSectionPoint VWP : list) {
			PointStr += "<option value='" + VWP.getItemId()
					+ "'>" + VWP.getWorkflowPoint().getName() + "</option>";
		}
		return PointStr;
	}
	
	
	@RequestMapping(value = "/section/id/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getPointbySection(@PathVariable("id") Integer id) {
		String PointStr = "<option value='0'>Select Section Point</option>";

		LoginSection LS = sectionList.getLoginSectionById(id);

		if (LS == null)
			return PointStr;

		Set<LoginSectionPoint> list = LS.getLoginSectionPoint();
		

		for (LoginSectionPoint section : list) {
			PointStr += "<option value='" + section.getItemId()
					+ "'>" + section.getWorkflowPoint().getName() + "</option>";
		}
		return PointStr;
	}
	

	@RequestMapping(value = "/pointbysection/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getpointbysectionId(@PathVariable("id") Integer id) {
		String PointStr = "<option value='0'>Select Section Point</option>";

	
		LoginSection LS = sectionList.getLoginSectionById(id);
		
		
		if (LS == null)
			return PointStr;
		
		Set<LoginSectionPoint> list = LS.getLoginSectionPoint();

		for (LoginSectionPoint LSP : list) {
			PointStr += "<option value='" + LSP.getItemId()
					+ "'>" + LSP.getWorkflowPoint().getName() + "</option>";
		}
		return PointStr;
	}
	
	
}
*/