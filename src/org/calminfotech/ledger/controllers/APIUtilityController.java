package org.calminfotech.ledger.controllers;

import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.daoInterface.LedgerAccDao;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.utiility.LedgerException;
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
	
	@Autowired
	private LedgerAccBo ledgerAccBo;
	*/
	@Autowired
	private LedgerAccDao ledgerAccDao;

	@Autowired
	private GenLedgerBo genLedgerBo;
	
	@ResponseBody
	@RequestMapping(value = {"/balance/{account_no}"}, method=RequestMethod.GET, produces = "text/html")
	public String getBalance(@PathVariable String account_no){
		LedgerAccount ledgerAccount = this.ledgerAccDao.getLedgerByAccount_no(account_no);
		
		String balance = null;
		try {
			balance = String.valueOf(this.genLedgerBo.getBalance(ledgerAccount.getAccount_no(), ledgerAccount.getOrganisation().getId(), ledgerAccount.getOrgCoy().getId()).getCurr_balance());
			System.out.println(balance);
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		return balance;
	}
}
