package org.calminfotech.hrunit.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.calminfotech.hrunit.boInterface.ClockinBo;
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;


import org.calminfotech.hrunit.boInterface.GetClockingUnitProcBo;
import org.calminfotech.hrunit.forms.GetClockingUnitProcForm;
import org.calminfotech.hrunit.models.ClockAssignment;
import org.calminfotech.hrunit.models.ClockinLog;
import org.calminfotech.hrunit.models.GetClockingUnitProc;

//import org.calminfotech.setup.boInterface.UnitCategoryBo;
import org.calminfotech.hrunit.boInterface.ClockinBo;
import org.calminfotech.system.forms.GetRoleAssignmentProcForm;
import org.calminfotech.system.forms.GetUserAssignmentProcForm;

import org.calminfotech.user.boInterface.PermissionBo;
import org.calminfotech.user.boInterface.RoleBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserProfileBo;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/hrunit/clocking")
public class ClockinController {
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Alert alert;
	
	@Autowired
	private GetClockingUnitProcBo getClockingUnitProcBo;

	@Autowired
	private StaffRegBoInterface  staffregBo ;

	@Autowired
	private RoleBo roleBo;
	
	@Autowired
	private UserProfileBo userprofileBo;
	
	
	
	@Autowired
	private ClockinBo clockinBo;
	@Autowired
	private UserBo userBo;

	@Autowired
	private HrunitCategoryBo unitCatBo;

	
	//users start from here
	@RequestMapping(value = {"/index"}, method = RequestMethod.GET)
	public String indexUser(Model model) {
		System.out.println("start clocking");
		//int userId = 46;
		List<GetClockingUnitProc> clockinProc =  this.getClockingUnitProcBo.fetchClockinUnit(userIdentity.getUserId());
		GetClockingUnitProcForm nf =new GetClockingUnitProcForm();
	nf.setHours(12);
	nf.setMinutes(0);
	
		model.addAttribute("unitClockin",clockinProc);
		//System.out.println("clocking size is" + clockinProc.size());
		model.addAttribute("clockinForm", nf);
		//model.addAttribute("users", this.userBo.fetchAll());
		return "hrunit/clocking/index";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String entryUserAction(
			@ModelAttribute("clockinForm") GetClockingUnitProcForm clockinForm,
			BindingResult result, RedirectAttributes redirectAttributes,HttpServletRequest request,
			Model model) {
		if (result.hasErrors()) {
			return "";
		}
		if (clockinForm.getSaveButton() == null) {
			return "redirect:/hrunit/clocking/request/"
					+ userIdentity.getUserId();
		}
		
		String bt = request.getParameter("saveButton");
		
		
		this.clockinBo.deleteAllCheckedValues(userIdentity.getUserId());
	    userIdentity.setCurrentPointId(1);
	    userIdentity.setCurrentPointName("");
	    userIdentity.setCurrentUnit(null);
	    userIdentity.setCurrentUnitId(null);
		
		
		ClockAssignment clockAssignment = new ClockAssignment();
		ClockinLog clocklog = new ClockinLog();
		
		Integer hours = clockinForm.getHours();
		if(hours==null || hours == 0){
			hours = 12;
		}
		Integer minutes = clockinForm.getMinutes();
		if(minutes==null || minutes==0){
			minutes = 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, hours);
		cal.add(Calendar.MINUTE, minutes);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		System.out.println("clockinForm.getMinutes()");
		//System.out.println(dateFormat.format(cal.getTime()));
		System.out.println("1***");
		String[] checkboxes = clockinForm.getUnitCheckboxVals();
		// iterate array list
		String desc;
		
		if("clockin".equals(bt)){
			desc="ClockIn";
		}
		else
		{
			desc="ClockOut";
		}
		
			if(checkboxes!=null){
				System.out.println("lengthis" + checkboxes.length);
		for (String checkbox : checkboxes) {
			
			 if("clockin".equals(bt)){
			//clockAssignment.setUserId(userIdentity.getUserId());
			clockAssignment.setClockInTime(new Date(System.currentTimeMillis()));
			clockAssignment.setUserprofile(this.userprofileBo.getUserProfileByUserId(userIdentity.getUserId()));
			clockAssignment.setOrganisationId(userIdentity.getOrganisation().getId());
			clockAssignment.setHrunit(this.unitCatBo.getHrunitCategoryById(Integer.parseInt(checkbox)));
			//clockAssignment.setPointId((Integer.parseInt(checkbox));
			
			clockAssignment.setClockInTime(new Date(System.currentTimeMillis()));
			clockAssignment.setExpClockOutTime(cal.getTime());
		
			clockinBo.save(clockAssignment);
			 }
			clocklog.setUsername(userIdentity.getUsername());
			clocklog.setClockTime(new Date(System.currentTimeMillis()));
			clocklog.setClockingType(desc);
			clocklog.setClockingUnit(Integer.parseInt(checkbox));
			clocklog.setOrganisationId(userIdentity.getOrganisation().getId());
			clocklog.setUserId(userIdentity.getUserId());
			this.clockinBo.save(clocklog);
			}	
			}
			/*if button is clin descrtiin=inj
					hfkkou
			boto save arord(useIdi,username,drescryi,,gfh,ghjk)*/
		// redirect
	
		model.addAttribute("unitClockin",this.getClockingUnitProcBo.fetchClockinUnit(userIdentity.getUserId()));
		model.addAttribute("clockinForm", new GetClockingUnitProcForm());
		//model.addAttribute("users", userBo.fetchAll());
		//return "redirect:/hrunit/clocking/request/ " + userIdentity.getUserId();
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Clocked Successfully!!!");
	return "redirect:/system";
	}
	

	@RequestMapping(value = "/request/{userId}", method = RequestMethod.GET)
	public String requestUser(@PathVariable("userId") Integer Id, Model model,
			HttpServletResponse response) {
		GetClockingUnitProcForm clockinForm = new GetClockingUnitProcForm();
		//uForm.setpUser(Id);
		
		model.addAttribute("unitClockin",this.getClockingUnitProcBo.fetchClockinUnit(Id));
		model.addAttribute("clockinForm", clockinForm);
		//model.addAttribute("users", userBo.fetchAll());
		return "hrunit/clocking/index";
	}

}
