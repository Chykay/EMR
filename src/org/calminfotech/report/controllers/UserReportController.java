package org.calminfotech.report.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;
import org.calminfotech.hrunit.models.StaffRegistration;/*
import org.calminfotech.inventory.serviceInterface.InventoryManagerInterface;
import org.calminfotech.patient.forms.PatientreportsearchForm;*/
import org.calminfotech.report.utils.UserReportDao;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.ClockedUsersList;
import org.calminfotech.utils.SearchUtility;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/userreport")
public class UserReportController {

	@Autowired
	private Authorize authorize;

	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;

	@Autowired
	private GlobalItemBo globalItemBo;
/*
	@Autowired
	private InventoryManagerInterface InventoryManagerInter;*/

	@Autowired
	private SearchUtility searchUtilBo;

	@Autowired
	private ClockedUsersList clockuserBo;

	@Autowired
	private UserReportDao userrepdao;

	@Autowired
	StaffRegBoInterface staffRegBo;

	// this is to show patient list by registration date form
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/search")
	public String viewAction3(Model model, RedirectAttributes redirectAttributes) {
/*
		PatientreportsearchForm PRSForm = new PatientreportsearchForm();
		model.addAttribute("userreportForm", PRSForm);

*/
		return "report/user/usersearch";
	}

	// this is to show patient list by registration date result in table form
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/alluserbystatus/{status}", method = RequestMethod.GET)
	public String searchPatient(@PathVariable("status") String status,
			Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {

		List<User> userresult = userrepdao.fetchAllUsetbystatus(status);

		model.addAttribute("userresult", userresult);

		model.addAttribute("statusname", userresult.get(0).getStatus());

		return "report/user/userlistprint";

	}

	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/stafflist", method = RequestMethod.GET)
	public String listAllstaff(Model model) {
		List<StaffRegistration> staffRegistration = this.staffRegBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("staffreg", staffRegistration);
		return "report/user/stafflist";
	}

}
