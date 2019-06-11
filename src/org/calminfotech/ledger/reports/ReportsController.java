package org.calminfotech.ledger.reports;

import java.util.List;

import org.calminfotech.ledger.boInterface.TotAccBo;
import org.calminfotech.ledger.models.TotalingAccount;
import org.calminfotech.ledger.reports.models.BranchTB;
import org.calminfotech.ledger.utility.ReportsBo;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/ledger/reports")
public class ReportsController {
/*
	@Autowired
	private Authorize authorize;

	@Autowired
	private Alert alert;
*/
	@Autowired
	private UserIdentity userIdentity;

	/*@Autowired
	private Auditor auditor;
	*/
	@Autowired
	private TotAccBo totAccBo;

	@Autowired
	private ReportsBo reportsBo;
	
	@Autowired
	private OrganisationBo organisationBo;
	


	@Layout("layouts/reportblank")
	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public String listAll(Model model) {

		List<TotalingAccount> totalingAccounts = this.totAccBo.fetchAll();
		
		model.addAttribute("totalingAccounts", totalingAccounts);
		
		for (TotalingAccount totalingAccount : totalingAccounts) {
			System.out.println(totalingAccount.getName());
		}
		return "ledger/reports/sample";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/TB", method = RequestMethod.GET)
	public String trialBalance(Model model) {
		List<Organisation> branches = this.organisationBo.fetchAll(this.userIdentity.getOrganisation().getId());
		model.addAttribute("company", this.userIdentity.getOrganisation().getOrgCoy());
		model.addAttribute("branches", branches);
		return "ledger/reports/tb/index";
	}
	

	@Layout("layouts/reportblank")
	@RequestMapping(value = "/TB/branch/{branchID}", method = RequestMethod.GET)
	public String branchTB(Model model, @PathVariable int branchID) {
		BranchTB branchTB = this.reportsBo.getBranchTB(branchID);
		model.addAttribute("branchTB", branchTB);
		model.addAttribute("entries", branchTB.gettBalEntries());
		return "ledger/reports/tb/branch";
	}
	
	@Layout("layouts/reportblank")
	@RequestMapping(value = "/TB/{companyID}", method = RequestMethod.GET)
	public String companyTB(Model model, @PathVariable int companyID) {

		model.addAttribute("companyTB", this.reportsBo.getCompanyTB(companyID));
		return "ledger/reports/tb/company";
	}

	/*// this is to show invoice / bill list by due date form
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/searchcount")
	public String searchcountteform(Model model,
			RedirectAttributes redirectAttributes) {

		PatientreportsearchForm ctform = new PatientreportsearchForm();

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);

		cal.add(Calendar.DAY_OF_YEAR, +30);

		model.addAttribute("ctform", ctform);

		return "report/general/consultioncount";
	}

	// this is to show patient list by registration date result in table form
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/allcountprint/{datefrom}/{dateto}", method = RequestMethod.GET)
	public String printcount(@PathVariable("datefrom") String datefrom,
			@PathVariable("dateto") String dateto, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {

		
		 * System.out.println("My first date " +datefrom);
		 * System.out.println("My second  date " +dateto);
		 
		List<ConsultationCount> cnsultationCount = this.consultationCountDao
				.fetchAllconsultationcount(DateUtils.formatStringToDate(
						datefrom, "yyyy-MM-dd hh:mm"), DateUtils
						.formatStringToDate(dateto, "yyyy-MM-dd hh:mm"));

		model.addAttribute("counttable", cnsultationCount);

		// for the header sake

		model.addAttribute("datefrom", datefrom);
		model.addAttribute("dateto", dateto);
		
		 * int r; if (r == 0) { }
		 
		return "report/general/consultioncounprint";

	}
*/
}
