package org.calminfotech.ledger.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.boInterface.CustomerAccBo;
import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.models.CustomerAccount;
import org.calminfotech.ledger.models.CustomerEntry;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.utiility.LedgerException;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/ledger")
public class APIUtilityController {
/*
	@Autowired
	private TotAccBo totAccBo;

	@Autowired
	private BalSheetCatBo balSheetCatBo;
	*/
	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private GenLedgerBo genLedgerBo;

	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private CustomerAccBo customerAccBo;
	
	@ResponseBody
	@RequestMapping(value = {"/balance/{account_no}/{branch_id}/{account_type}"}, method=RequestMethod.GET, produces = "text/html")
	public String getBalance(@PathVariable("account_no") String account_no, @PathVariable("branch_id") int branch_id, @PathVariable("account_type") String account_type){
		
		String balance = null;
		if (account_type.contains("CL")) {
			// CALL A DIFFERENT GET BAL
			Organisation org = this.customerAccBo.getCustomerByAccount_no(account_no).getOrganisation();
			
			balance = String.valueOf(this.customerAccBo.getBalance(account_no, org.getId(), org.getOrgCoy().getId()).getCurr_balance());
		} else {
			OrganisationCompany org = this.organisationBo.getOrganisationById(branch_id).getOrgCoy();
			try {
				balance = String.valueOf(this.genLedgerBo.getBalance(account_no, branch_id, org.getId()).getCurr_balance());
				
			} catch (LedgerException e) {
				e.printStackTrace();
			}
		}
		
		return balance;
	}
	
	@ResponseBody
	@RequestMapping(value = {"/status/{id}"}, method=RequestMethod.GET, produces = "text/html")
	public String ledgerStatus(@PathVariable("id") String id){
		LedgerAccount ledgerAccount = this.ledgerAccBo.getLedgerById(Integer.parseInt(id));
		String account_no = ledgerAccount.getAccount_no();
		Organisation org = ledgerAccount.getOrganisation();
		Integer branch_id = org.getId();
		Integer company_id = org.getOrgCoy().getId();
		
		try {
			return Boolean.toString(this.genLedgerBo.getLedgerStat(account_no, branch_id, company_id));
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = {"/fetch/{account_type}"}, method=RequestMethod.GET, produces = "text/html")
	public String fetchLedgers(@PathVariable("account_type") String account_type){
		Organisation org = this.userIdentity.getOrganisation();
		
		String accounts = "";
		if (account_type.contains("GL")) {
			List<LedgerAccount> ledgerAccounts = this.ledgerAccBo.fetchAll(org.getId(), org.getOrgCoy().getId());
			
			for (LedgerAccount ledgerAccount : ledgerAccounts) {
				accounts += "<option value='" + ledgerAccount.getAccount_no() + "'>"
						+ ledgerAccount.getAccount_no().concat("-") + ledgerAccount.getName() + "</option>";
			}
			
		} else {
			List<CustomerAccount> customerAccounts = this.customerAccBo.fetchAll(org.getId(), org.getOrgCoy().getId());
			for (CustomerAccount customerAccount : customerAccounts) {
				accounts += "<option value='" + customerAccount.getAccount_no() + "'>"
						+ customerAccount.getAccount_no() + "</option>";
			}
		}
		return accounts;
	}
	
	@ResponseBody
	@RequestMapping(value = {"/gen_ledger/listings/{account_no}/{start_date}/{end_date}"}, method=RequestMethod.GET, produces = "text/html")
	public String getGLListings(@PathVariable("account_no") String account_no, @PathVariable("start_date") String start_date, @PathVariable("end_date") String end_date){
		List<GLEntry>  glEntries = null;
		try {
			 glEntries = this.genLedgerBo.getGLEntriesByAccount_no(account_no);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("error oo");
		}
		
		String accounts = "<tr>";
		for (GLEntry glEntry : glEntries) {
			accounts += "<td>"
					+ glEntry.getAccount_no().concat("</td><td>") 
					+ Float.toString(glEntry.getAmount()).concat("</td><td>") 
					+ glEntry.getPost_code().concat("</td><td>") 
					+ Integer.toString(glEntry.getBranch()).concat("</td><td>") 
					+ glEntry.getBatch_no().concat("</td><td>") 
					+ glEntry.getRef_no1().concat("</td><td>") 
					+ glEntry.getDescription().concat("</td>");
		}
		return accounts.concat("</tr>");
	}
	
	@ResponseBody
	@RequestMapping(value = {"/customer_acc/listings/{prod_type}/{account_no}/{start_date}/{end_date}"}, method=RequestMethod.GET, produces = "text/html")
	public String getCustListings(@PathVariable("prod_type") String prod_type, @PathVariable("account_no") String account_no, @PathVariable("start_date") String start_date, @PathVariable("end_date") String end_date){
		System.out.println("API: " + prod_type.concat(" : ") + account_no.concat(" : ") + start_date.concat(" : "));
		
		try {
			Date sDate = new SimpleDateFormat("dd/MM/yyyy").parse(start_date);
			Date eDate = new SimpleDateFormat("dd/MM/yyyy").parse(end_date);
			
			System.out.println(sDate + " : " + eDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<CustomerEntry>  customerEntries = null;
		try {
			customerEntries = this.genLedgerBo.getCustEntriesByAccount_no(account_no, start_date, end_date);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("error oo");
		}
		
		
		String accounts = "";
		for (CustomerEntry customerEntry : customerEntries) {
			if (customerEntry.getRef_no2() == null) {
				customerEntry.setRef_no2("");
			}
			accounts += "<tr><td>"
					+ customerEntry.getAccount_no().concat("</td><td>") 
					+ Float.toString(customerEntry.getAmount()).concat("</td><td>") 
					+ customerEntry.getPost_code().concat("</td><td>") 
					+ customerEntry.getBatch_no().concat("</td><td>") 
					+ customerEntry.getRef_no2().concat("</td><td>") 
					+ customerEntry.getDescription().concat("</td></tr>");
		}
		return accounts.concat("");
	}
	
	
}
