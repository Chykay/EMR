package org.calminfotech.ledger.utility;

import java.util.ArrayList;
import java.util.List;

import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.reports.models.BranchTB;
import org.calminfotech.ledger.reports.models.CompanyTB;
import org.calminfotech.ledger.reports.models.TrialBalEntry;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportsBo {
	

	@Autowired
	private GenLedgerBo genLedgerBo;
	
	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private ReportsDao reportsDao;

	public BranchTB getBranchTB(int org_id) {
		List<TrialBalEntry> trialBalEntries = new ArrayList<TrialBalEntry>();
		float tot_debit = 0, tot_credit = 0, balance = 0;
		BranchTB branchTB = new BranchTB();
		
		List<GenLedgBalance> genLedgBalances = this.reportsDao.getGLBalances(org_id);
		
		for (GenLedgBalance genLedgBalance : genLedgBalances) {
			String accountNo = genLedgBalance.getGLAccountNo();
			TrialBalEntry trialBalEntry = new TrialBalEntry();
			System.out.println(accountNo);
			trialBalEntry.setName(this.ledgerAccBo.getLedgerByAccount_no(accountNo).getName());
			trialBalEntry.setAccountNo(accountNo);
			
			balance = genLedgBalance.getCurrBalance();
			
			if (accountNo.charAt(0) == '1' || accountNo.charAt(0) ==  '5') {
				trialBalEntry.setDebit(balance);
				tot_debit += balance;
			} else {
				trialBalEntry.setCredit(balance);
				tot_credit += balance;
			}
			trialBalEntries.add(trialBalEntry);
		}
		
		
		branchTB.setName(this.organisationBo.getOrganisationById(org_id).getName());
		branchTB.setTBalEntries(trialBalEntries);
		branchTB.setTotBalance(balance);
		branchTB.setTotCredit(tot_credit);
		branchTB.setTotDebit(tot_debit);
		return branchTB;
	}
	

	@SuppressWarnings("null")
	public CompanyTB getCompanyTB(int comp_id) {
		
		List<Organisation> organisations = this.organisationBo.fetchAll(comp_id);
		
		for (Organisation organisation : organisations) {
			this.getBranchTB(organisation.getId());
		}
		
		
		/*List<TrialBalEntry> trialBalEntries = null;
		float tot_debit = 0, tot_credit = 0, balance = 0;
		BranchTB branchTB = new BranchTB();
		
		try {
			List<GLEntry> glEntries = this.genLedgerBo.getGLEntries(org_id);
			for (GLEntry glEntry : glEntries) {
				String postCode = glEntry.getPostCode(), accountNo = glEntry.getAccountNo();
				float amount = glEntry.getAmount();
				TrialBalEntry trialBalEntry = new TrialBalEntry();
				
				trialBalEntry.setName(this.ledgerAccBo.getLedgerByAccount_no(accountNo).getName());
				trialBalEntry.setAccountNo(accountNo);
				
				if (postCode.equals("DR")) {
					trialBalEntry.setDebit(amount);
					tot_debit += amount;
				} else {
					trialBalEntry.setCredit(amount);
					tot_credit += amount;
				}
				
				trialBalEntries.add(trialBalEntry);
			}
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		branchTB.setName(this.organisationBo.getOrganisationById(org_id).getName());
		branchTB.setTBalEntries(trialBalEntries);
		branchTB.setTotBalance(balance);
		branchTB.setTotCredit(tot_credit);
		branchTB.setTotDebit(tot_debit);
		return branchTB;
	*/
	}
	
}
