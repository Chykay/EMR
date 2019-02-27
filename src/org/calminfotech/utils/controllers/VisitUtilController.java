/*
package org.calminfotech.utils.controllers;

import java.util.List;
import java.util.Set;

import org.calminfotech.consultation.bo.VisitBo;
import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.system.boInterface.LoginSectionBo;
import org.calminfotech.system.boInterface.BillSchemeBo;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.BillScheme;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.calminfotech.views.boInterface.VwItemBo;
import org.calminfotech.views.models.VwItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/utilities/visit")
public class VisitUtilController {
	
	@Autowired
	private VisitBo visitBo;
	
	@Autowired
	private PatientBo patientBo;
	
	@Autowired
	private LoginSectionBo sectionBo;
	
	@Autowired
	private VwItemBo vwItemBo;
	
	@Autowired
	private BilliSchemeBo paymentSchemeBo;
	
	@RequestMapping(value = "/visitbypatientId/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getVisitByPatientId(@PathVariable("id") Integer id){
		String visitStr = "<option value='0'>Select Code</option>";
		Patient patient = this.patientBo.getPatientById(id);
		if(patient == null){
			return visitStr;
		}
		List<Visit> list = this.visitBo.fetchByPatientId(patient.getPatientId());
		for(Visit visit : list){
			visitStr += "<option value="+visit.getItemId()+">"+visit.getCode()+"</option>";
		}
		return visitStr;
	}
	
	@RequestMapping(value = "/section", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getsection(){
		String sectionStr = "<option value='0'>Select Section</option>";
		List<LoginSection> sectionList = this.sectionBo.fetchAllByOrganisation();
		if(sectionList == null){
			return sectionStr;
		}
		for(LoginSection loginSection : sectionList ){
			sectionStr += "<option value="+loginSection.getItemId()+">"+loginSection.getSession_name()+"</option>";
		}
		return sectionStr;
	}
	
	@RequestMapping(value = "/itembypoint/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getitem(@PathVariable("id")Integer id){
		String itemStr = "<option value='0'>Select Item</option>";
		List<VwItem> itemList = this.vwItemBo.fetchAllByPoint(id);
		if(itemList == null){
			return itemStr;
		}
		for(VwItem item : itemList ){
			itemStr += "<option value="+item.getItemId()+">"+item.getName()+"</option>";
		}
		return itemStr;
	}
	
	@RequestMapping(value = "/point/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getPoint(@PathVariable("id")Integer id){
		String pointStr = "<option value='0'>Select Point</option>";
		LoginSection section = this.sectionBo.getLoginSectionById(id);
		if(section == null){
			return pointStr;
		}
		Set<WorkflowPoint> pointList = section.getVwfwPoint();
		for(WorkflowPoint point : pointList){
			pointStr += "<option value ="+point.getItemId()+">"+point.getName()+"</option>";
		}
		return pointStr;
	}
	
	@RequestMapping(value = "/billing/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getBillingScheme(@PathVariable("id")Integer id){
		String billingStr = "<input type='text' class='col-xs-10 col-sm-7' id='billingId' placeholder='No NBilling' readonly/>";
		LoginSection section = this.sectionBo.getLoginSectionById(id);
		if(section == null){
			return billingStr;
		}
		BillingScheme billingScheme = this.paymentSchemeBo.getBillingSchemeById(section.getPscheme().getItemId());
		billingStr = "<input type='text' class='col-xs-10 col-sm-7' name='billingId' id='billingId' value="+billingScheme.getName()+" placeholder='No NBilling' readonly/>";
	return billingStr;
	}

}
*/