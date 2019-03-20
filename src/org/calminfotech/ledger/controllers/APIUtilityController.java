package org.calminfotech.ledger.controllers;

import java.util.List;

import org.calminfotech.ledger.boInterface.CustomerAccBo;
import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.models.CustomerAccount;
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
	@RequestMapping(value = {"/balance/{account_no}/{branch_id}"}, method=RequestMethod.GET, produces = "text/html")
	public String getBalance(@PathVariable("account_no") String account_no, @PathVariable("branch_id") int branch_id){
		OrganisationCompany org = this.organisationBo.getOrganisationById(branch_id).getOrgCoy();
		
		String balance = null;
		try {
			balance = String.valueOf(this.genLedgerBo.getBalance(account_no, branch_id, org.getId()).getCurr_balance());
			
		} catch (LedgerException e) {
			e.printStackTrace();
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
	
	
	
}
