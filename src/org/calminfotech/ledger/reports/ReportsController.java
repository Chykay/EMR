package org.calminfotech.ledger.reports;

import java.util.ArrayList;
import java.util.List;

import org.calminfotech.hmo.boInterface.HmoBo;
import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.models.Vendor;
import org.calminfotech.inventory.serviceInterface.VendorManagerInterface;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.LedgerTotallingBo;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.utility.ReportsBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.models.Patient;
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
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private UserIdentity userIdentity;

	/*@Autowired
	private Auditor auditor;
	*/
	@Autowired
	private LedgerTotallingBo ledgerTotallingBo;

	@Autowired
	private ReportsBo reportsBo;
	
	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private PatientBo patientBo;
	
	@Autowired
	private HmoBo hmoBo;
	
	@Autowired
	private VendorManagerInterface vendorManagerInterface;
	
	/*@Autowired
	private */
	

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

		
		model.addAttribute("branchBS", this.reportsBo.getCoAReportWithReserveGL(branchID, type, "branch"));

		return "ledger/reports/bal_sheet/branch";
	}
	
	@Layout("layouts/reportblank")
	@RequestMapping(value = "/bal_sheet/{companyID}/{type}", method = RequestMethod.GET)
	public String companyBalSheet(Model model, @PathVariable int companyID, @PathVariable String type) {

		model.addAttribute("companyBS", this.reportsBo.getCoAReportWithReserveGL(this.userIdentity.getOrganisation().getId(), type, "company"));

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
		
		model.addAttribute("branchPL", this.reportsBo.getCoAReport(branchID, type, "PandL", "branch"));

		return "ledger/reports/P_L/branch";
	}
	
	@Layout("layouts/reportblank")
	@RequestMapping(value = "/P_L/{companyID}/{type}", method = RequestMethod.GET)
	public String companyPandL(Model model, @PathVariable int companyID, @PathVariable String type) {

		model.addAttribute("companyPL",  this.reportsBo.getCoAReport(this.userIdentity.getOrganisation().getId(), type, "PandL", "company"));

		return "ledger/reports/P_L/company";
	}
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/GL", method = RequestMethod.GET)
	public String indexGL(Model model) {
		Organisation org = userIdentity.getOrganisation();
		System.out.println(org.getId() + " : " + org.getOrgCoy().getId());
		
		if (this.ledgerAccBo.fetchTop100(org.getOrgCoy().getId()) != null) {
			List<LedgerAccount> ledgerAccounts = this.ledgerAccBo.fetchTop100(org.getOrgCoy().getId());
			model.addAttribute("accounts", ledgerAccounts);
		} 
		
		return "ledger/reports/GL/index";
	}

	@Layout("layouts/reportblank")
	@RequestMapping(value = "/GL/{accountNo}", method = RequestMethod.GET)
	public String generalLedger(Model model, @PathVariable String accountNo) {
		
		model.addAttribute("gLReport", this.reportsBo.GLReport(accountNo));

		return "ledger/reports/GL/report";
	}

	@Layout("layouts/reportblank")
	@RequestMapping(value = "/GL/{company_id}/{accountNo}", method = RequestMethod.GET)
	public String generalLedgerCompany(Model model, @PathVariable Integer company_id, @PathVariable String accountNo) {
		
		model.addAttribute("gLReport", this.reportsBo.GLReportCompany(company_id, accountNo));

		return "ledger/reports/GL/com_report";
	}
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public String indexCustomer(Model model) {
		Organisation org = userIdentity.getOrganisation();
		
			List<Patient> patients = this.patientBo.fetchAllByOrganisation(org.getId());
			model.addAttribute("patients", patients);
		
		
		return "ledger/reports/patient/index";
	}

	@Layout("layouts/reportblank")
	@RequestMapping(value = "/patients/{id}/{start_date}/{end_date}", method = RequestMethod.GET)
	public String customer(Model model, @PathVariable("id") int id, @PathVariable("start_date") String startDate,
			@PathVariable("end_date") String endDate ) {
		
		model.addAttribute("customerReport", this.reportsBo.customerReport(id, startDate, endDate));

		return "ledger/reports/patient/report";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/hmos", method = RequestMethod.GET)
	public String indexHMO(Model model) {
		Organisation org = userIdentity.getOrganisation();
		
			List<Hmo> hmos = this.hmoBo.fetchAll(org);
			model.addAttribute("hmos", hmos);
		
		
		return "ledger/reports/HMO/index";
	}

	@Layout("layouts/reportblank")
	@RequestMapping(value = "/hmos/{id}/{start_date}/{end_date}", method = RequestMethod.GET)
	public String hmo(Model model, @PathVariable("id") int id, @PathVariable("start_date") String startDate,
			@PathVariable("end_date") String endDate ) {
		
		model.addAttribute("hmoReport", this.reportsBo.hmoReport(id, startDate, endDate));

		return "ledger/reports/HMO/report";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/vendors", method = RequestMethod.GET)
	public String indexVendor(Model model) {
		
			List<Vendor> vendors = new ArrayList<Vendor>();
			try {
				vendors = this.vendorManagerInterface.getVendorsList();
			} catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			model.addAttribute("vendors", vendors);
		
		
		return "ledger/reports/vendor/index";
	}

	@Layout("layouts/reportblank")
	@RequestMapping(value = "/vendors/{id}/{start_date}/{end_date}", method = RequestMethod.GET)
	public String vendor(Model model, @PathVariable("id") int id, @PathVariable("start_date") String startDate,
			@PathVariable("end_date") String endDate ) {
		
		model.addAttribute("vendorReport", this.reportsBo.vendorReport(id, startDate, endDate));

		return "ledger/reports/vendor/report";
	}

	/*@Layout("layouts/reportblank")
	@RequestMapping(value = "/GL/{company_id}/{accountNo}", method = RequestMethod.GET)
	public String generalLedgerCompany(Model model, @PathVariable Integer company_id, @PathVariable String accountNo) {
		
		model.addAttribute("gLReport", this.reportsBo.GLReportCompany(company_id, accountNo));

		return "ledger/reports/GL/com_report";
	}*/
}
