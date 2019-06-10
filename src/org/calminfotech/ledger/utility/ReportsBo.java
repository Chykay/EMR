package org.calminfotech.ledger.utility;

import java.util.List;

import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.reports.models.BranchTB;
import org.calminfotech.ledger.reports.models.TrialBalEntry;
import org.calminfotech.system.boInterface.OrganisationBo;
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

	@SuppressWarnings("null")
	public BranchTB getBranchTB(int org_id) {
		List<TrialBalEntry> trialBalEntries = null;
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
				trialBalEntry.setPostCode(postCode);
				
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
	}
	

	@SuppressWarnings("null")
	public BranchTB getCompanyTB(int comp_id) {
		int org_id = 0;
		
		
		
		
		
		List<TrialBalEntry> trialBalEntries = null;
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
				trialBalEntry.setPostCode(postCode);
				
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
	}
	
	
}
