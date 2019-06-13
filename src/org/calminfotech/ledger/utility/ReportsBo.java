package org.calminfotech.ledger.utility;

import java.util.ArrayList;
import java.util.List;

import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.LedgerCatBo;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.LedgerCategory;
import org.calminfotech.ledger.reports.models.BalanceSheet;
import org.calminfotech.ledger.reports.models.BranchBalSheet;
import org.calminfotech.ledger.reports.models.BranchTB;
import org.calminfotech.ledger.reports.models.CompanyTB;
import org.calminfotech.ledger.reports.models.TrialBalEntry;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportsBo {
	

	
	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private ReportsDao reportsDao;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private LedgerCatBo ledgerCatBo;
	
	@Autowired
	private GenLedgerBo genLedgerBo;

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
		branchTB.settBalEntries(trialBalEntries);
		branchTB.setTotBalance(balance);
		branchTB.setTotCredit(tot_credit);
		branchTB.setTotDebit(tot_debit);
		return branchTB;
	}
	

	public CompanyTB getCompanyTB(int comp_id) {
		
		List<Organisation> organisations = this.organisationBo.fetchAll(comp_id);
		CompanyTB companyTB = new CompanyTB();
		float tot_debit = 0, tot_credit = 0, balance = 0;
		List<BranchTB> branchTBs = new ArrayList<BranchTB>();
		
		for (Organisation organisation : organisations) {
			BranchTB branchTB = this.getBranchTB(organisation.getId());
			tot_credit += branchTB.getTotCredit();
			tot_debit += branchTB.getTotDebit();
			balance += branchTB.getTotBalance();
			branchTBs.add(branchTB);
		}
		
		companyTB.setBranchTBs(branchTBs);
		companyTB.setTotBalance(balance);
		companyTB.setTotCredit(tot_credit);
		companyTB.setTotDebit(tot_debit);
		companyTB.setName(this.userIdentity.getOrganisation().getOrgCoy().getName());
		
		return companyTB;
	}


	public BranchBalSheet getBranchBalSheet(int branchID) {
		List<LedgerCategory> ledgerCategories = this.ledgerCatBo.fetchAll();
		List<BalanceSheet> rootBalSheets = new ArrayList<BalanceSheet>();
		List<BalanceSheet> descBalSheets = new ArrayList<BalanceSheet>();
		
		for (LedgerCategory ledgerCategory : ledgerCategories) {

			BalanceSheet balSheet = new BalanceSheet();
			balSheet.setName(ledgerCategory.getName());
			balSheet.setAccountNo("");
			balSheet.setId(ledgerCategory.getId());
			
			if (ledgerCategory.getParentID() == 0) {
				rootBalSheets.add(balSheet);
			} else {
				balSheet.setParentID(ledgerCategory.getParentID());
				descBalSheets.add(balSheet);
			}
		}
		
		for (BalanceSheet balanceSheet : rootBalSheets) {
			BalanceSheet self = this.getBalSheets(descBalSheets, balanceSheet.getId());
			balanceSheet.setBalanceSheets(self.getBalanceSheets());
			balanceSheet.setTotBalance(self.getTotBalance());
		}
		
		
		for (BalanceSheet balanceSheet : rootBalSheets) {
			System.out.println(balanceSheet.getName() + ":" + balanceSheet.getTotBalance());
			if (balanceSheet.getBalanceSheets().size() > 0) {
				for (BalanceSheet balanceSheet2 : balanceSheet.getBalanceSheets()) {
					System.out.println("  " + balanceSheet2.getName() + ":" + balanceSheet2.getTotBalance());
					if (balanceSheet2.getBalanceSheets().size() > 0) {
						for (BalanceSheet balanceSheet3 : balanceSheet2.getBalanceSheets()) {
							System.out.println("    " + balanceSheet3.getName() + ":" + balanceSheet3.getTotBalance());
							if (balanceSheet3.getBalanceSheets().size() > 0) {
								for (BalanceSheet balanceSheet4 : balanceSheet3.getBalanceSheets()) {
									System.out.println("    " + balanceSheet4.getName() + ": " + balanceSheet4.getTotBalance());
									
								}
							} else {
								System.out.println("  no children" );
							}
						}
					} else {
						System.out.println("  no children" );
					}
				}
			} else {
				System.out.println("no children" );

			}
		}
		return null;	
	}


	private BalanceSheet getBalSheets(List<BalanceSheet> descBalSheets, int parentID) {
		List<BalanceSheet> children = new ArrayList<BalanceSheet>();
		BalanceSheet parent = new BalanceSheet();
		float totBalance = 0;
		
		for (BalanceSheet balanceSheet : descBalSheets) {
			if(balanceSheet.getParentID() == parentID){
				BalanceSheet returnedSelf = this.getBalSheets(descBalSheets, balanceSheet.getId());
				
				if (returnedSelf.getBalanceSheets().size() > 0) {
					balanceSheet.setBalanceSheets(returnedSelf.getBalanceSheets());
				} else {
					returnedSelf = this.getLedgers(balanceSheet.getId());
					balanceSheet.setBalanceSheets(returnedSelf.getBalanceSheets());
				}
				
				balanceSheet.setTotBalance(returnedSelf.getTotBalance());
				totBalance += returnedSelf.getTotBalance();
				children.add(balanceSheet);
			} 
		}
		
		parent.setTotBalance(totBalance);
		parent.setBalanceSheets(children);
		return parent;
	}


	private BalanceSheet getLedgers(Integer parentID) {
		BalanceSheet parent = new BalanceSheet();
		List<BalanceSheet> balanceSheets = new ArrayList<BalanceSheet>();
		List<LedgerAccount> childLedgers = this.reportsDao.getGLBalancesByParent(parentID);
		float totBalance = 0, balance;
		
		for (LedgerAccount ledgerAccount : childLedgers) {
			BalanceSheet balSheet = new BalanceSheet();
			balSheet.setName(ledgerAccount.getName());
			balSheet.setAccountNo(ledgerAccount.getAccountNo());
			balSheet.setBalanceSheets(new ArrayList<BalanceSheet>());
			try {
				balance = this.genLedgerBo.getBalance(ledgerAccount.getAccountNo(), ledgerAccount.getOrganisation().getId(), ledgerAccount.getOrgCoy().getId()).getCurrBalance();
				balSheet.setTotBalance(balance);
				totBalance += balance;
			} catch (LedgerException e) {
				e.printStackTrace();
			}
			
			balanceSheets.add(balSheet);
		}
		
		parent.setBalanceSheets(balanceSheets);
		parent.setTotBalance(totBalance);
		return parent;
	}


	
	
}
