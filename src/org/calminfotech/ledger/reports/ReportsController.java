package org.calminfotech.ledger.reports;

import java.util.List;

import org.calminfotech.ledger.boInterface.TotAccBo;
import org.calminfotech.ledger.models.TotalingAccount;
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
		
		model.addAttribute("branchTB", this.reportsBo.getBranchTB(branchID));
		return "ledger/reports/tb/branch";
	}
	
	@Layout("layouts/reportblank")
	@RequestMapping(value = "/TB/{companyID}", method = RequestMethod.GET)
	public String companyTB(Model model, @PathVariable int companyID) {

		model.addAttribute("companyTB", this.reportsBo.getCompanyTB(companyID));
		return "ledger/reports/tb/company";
	}
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/bal_sheet", method = RequestMethod.GET)
	public String balanceSheet(Model model) {
		List<Organisation> branches = this.organisationBo.fetchAll(this.userIdentity.getOrganisation().getId());
		model.addAttribute("company", this.userIdentity.getOrganisation().getOrgCoy());
		model.addAttribute("branches", branches);
		return "ledger/reports/bal_sheet/index";
	}
	
	
	@Layout("layouts/reportblank")
	@RequestMapping(value = "/bal_sheet/branch/{branchID}/{type}", method = RequestMethod.GET)
	public String branchBalSheet(Model model, @PathVariable int branchID, @PathVariable String type) {

		
		model.addAttribute("branchBS", this.reportsBo.addReserve(branchID, type));

		return "ledger/reports/bal_sheet/branch";
	}
	
	
	@Layout("layouts/reportblank")
	@RequestMapping(value = "/bal_sheet/{companyID}/{type}", method = RequestMethod.GET)
	public String companyBalSheet(Model model, @PathVariable int companyID, @PathVariable String type) {

		model.addAttribute("companyBS", this.reportsBo.getCompanyCoA(companyID, type, "balSheet"));

		return "ledger/reports/bal_sheet/company";
	}
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/P_L", method = RequestMethod.GET)
	public String PandL(Model model) {
		List<Organisation> branches = this.organisationBo.fetchAll(this.userIdentity.getOrganisation().getId());
		model.addAttribute("company", this.userIdentity.getOrganisation().getOrgCoy());
		model.addAttribute("branches", branches);
		return "ledger/reports/P_L/index";
	}
	
	
	@Layout("layouts/reportblank")
	@RequestMapping(value = "/P_L/branch/{branchID}/{type}", method = RequestMethod.GET)
	public String branchPandL(Model model, @PathVariable int branchID, @PathVariable String type) {
		
		model.addAttribute("branchPL", this.reportsBo.getBranchCoA(branchID, type, "PandL"));

		return "ledger/reports/P_L/branch";
	}
	
	@Layout("layouts/reportblank")
	@RequestMapping(value = "/P_L/{companyID}/{type}", method = RequestMethod.GET)
	public String companyPandL(Model model, @PathVariable int companyID, @PathVariable String type) {

		model.addAttribute("companyPL", this.reportsBo.getCompanyCoA(companyID, type, "PandL"));

		return "ledger/reports/P_L/company";
	}
	
	
	@Layout("layouts/reportblank")
	@RequestMapping(value = "/GL/{accountNo}", method = RequestMethod.GET)
	public String generalLedger(Model model, @PathVariable String accountNo) {
		
		model.addAttribute("gLReport", this.reportsBo.GLReport(accountNo));

		return "ledger/reports/GL/report";
	}
}
