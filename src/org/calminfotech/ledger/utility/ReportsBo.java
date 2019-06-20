package org.calminfotech.ledger.utility;

import java.util.ArrayList;
import java.util.List;

import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.LedgerCatBo;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.LedgerCategory;
import org.calminfotech.ledger.reports.models.AccChartEntry;
import org.calminfotech.ledger.reports.models.BranchAccChart;
import org.calminfotech.ledger.reports.models.BranchTB;
import org.calminfotech.ledger.reports.models.CompanyAccChart;
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
			balance = genLedgBalance.getCurrBalance();
			TrialBalEntry trialBalEntry = new TrialBalEntry();
			trialBalEntry.setName(this.ledgerAccBo.getLedgerByAccount_no(accountNo).getName());
			trialBalEntry.setAccountNo(accountNo);
			
			
			if (accountNo.charAt(0) == '1' || accountNo.charAt(0) ==  '5') {
				if (balance >= 0) {
					trialBalEntry.setDebit(balance);
					tot_debit += balance;		
				} else {
					balance = Math.abs(balance);
					trialBalEntry.setCredit(balance);
					tot_credit += balance;
				}
			} else {
				if (balance >= 0) {
					trialBalEntry.setCredit(balance);
					tot_credit += balance;		
				} else {
					balance = Math.abs(balance);
					trialBalEntry.setDebit(balance);
					tot_debit += balance;
				}
				
			}
			trialBalEntries.add(trialBalEntry);
		}
		
		
		branchTB.setName(this.organisationBo.getOrganisationById(org_id).getName());
		branchTB.settBalEntries(trialBalEntries);
		branchTB.setTotBalance(tot_credit - tot_debit);
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

	public BranchAccChart getBranchAccChart(int branchID, String type, String chartType) {
		List<LedgerCategory> ledgerCategories = this.ledgerCatBo.fetchAllByOrg(branchID);
		List<AccChartEntry> rootBalSheets = new ArrayList<AccChartEntry>();
		List<AccChartEntry> descBalSheets = new ArrayList<AccChartEntry>();
		BranchAccChart branchAccChart = new BranchAccChart();
		
		for (LedgerCategory ledgerCategory : ledgerCategories) {

			AccChartEntry accChartEntry = new AccChartEntry();
			accChartEntry.setName(ledgerCategory.getName());
			accChartEntry.setAccountNo("");
			accChartEntry.setId(ledgerCategory.getId());
			
			if (ledgerCategory.getParentID() == 0) {
				rootBalSheets.add(accChartEntry);
			} else {
				accChartEntry.setParentID(ledgerCategory.getParentID());
				descBalSheets.add(accChartEntry);
			}
		}
		
		for (AccChartEntry accChartEntry : rootBalSheets) {
			AccChartEntry self = this.getChartOfAccs(descBalSheets, accChartEntry.getId(), type, chartType);
			accChartEntry.setShow(self.getShow());
			
			if (self.getAccChartEntries().size() > 0) {
				accChartEntry.setAccChartEntries(self.getAccChartEntries());
				accChartEntry.setTotBalance(self.getTotBalance());
				accChartEntry.setHasChildren(1);
			} else {
				accChartEntry.setAccChartEntries(new ArrayList<AccChartEntry>());
				accChartEntry.setHasChildren(-1);
			}
		}
		
		/*for (AccChartEntry accChartEntry : rootBalSheets) {
			System.out.println("here");
			System.out.println(accChartEntry.getName() + ":" + accChartEntry.getShow() + ":" + accChartEntry.getHasChildren());
			if (accChartEntry.getAccChartEntries() != null && accChartEntry.getAccChartEntries().size() > 0) {
				for (AccChartEntry accChartEntry2 : accChartEntry.getAccChartEntries()) {
					System.out.println("  " + accChartEntry2.getName() + ":" + accChartEntry2.getShow() + ":" + accChartEntry2.getHasChildren());
					
					if (accChartEntry2.getAccChartEntries() != null && accChartEntry2.getAccChartEntries().size() > 0) {
						for (AccChartEntry accChartEntry3 : accChartEntry2.getAccChartEntries()) {
							System.out.println("    " + accChartEntry3.getName() + ":" + accChartEntry3.getShow() + ":" + accChartEntry3.getHasChildren());
							if (accChartEntry3.getAccChartEntries() != null && accChartEntry3.getAccChartEntries().size() > 0) {
								for (AccChartEntry accChartEntry4 : accChartEntry3.getAccChartEntries()) {
									System.out.println("    " + accChartEntry4.getName() + ": " + accChartEntry4.getShow() + ": " + accChartEntry4.getHasChildren());
									
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
		}*/
		branchAccChart.setAccChartEntries(rootBalSheets);
		branchAccChart.setName(this.organisationBo.getOrganisationById(branchID).getName());
		
		return branchAccChart;	
	}


	private AccChartEntry getChartOfAccs(List<AccChartEntry> descBalSheets, int parentID, String type, String chartType) {
		List<AccChartEntry> children = new ArrayList<AccChartEntry>();
		AccChartEntry parent = new AccChartEntry();
		float totBalance = 0;
		
		for (AccChartEntry accChartEntry : descBalSheets) {
			if(accChartEntry.getParentID() == parentID){
				AccChartEntry returnedSelf = this.getChartOfAccs(descBalSheets, accChartEntry.getId(), type, chartType);
				accChartEntry.setShow(returnedSelf.getShow());
				
				if (returnedSelf.getAccChartEntries().size() > 0) {
					accChartEntry.setAccChartEntries(returnedSelf.getAccChartEntries());
					accChartEntry.setHasChildren(1);
				} else {
					returnedSelf = this.getLedgers(accChartEntry.getId(), type, chartType);
					
					if (returnedSelf.getAccChartEntries().size() > 0) {
						if (type.equals("full")) {
							accChartEntry.setAccChartEntries(returnedSelf.getAccChartEntries());
						} else {
							accChartEntry.setAccChartEntries(new ArrayList<AccChartEntry>());
						}
						accChartEntry.setShow(1);
						parent.setShow(1);
						accChartEntry.setHasChildren(1);
					} else {
						accChartEntry.setAccChartEntries(new ArrayList<AccChartEntry>());
						accChartEntry.setHasChildren(-1);
					}
				}
				
				accChartEntry.setTotBalance(returnedSelf.getTotBalance());
				totBalance += returnedSelf.getTotBalance();
				children.add(accChartEntry);
			} 
		}
		
		parent.setTotBalance(totBalance);
		parent.setAccChartEntries(children);
		return parent;
	}


	private AccChartEntry getLedgers(Integer parentID, String type, String chartType) {
		AccChartEntry parent = new AccChartEntry();
		List<AccChartEntry> accChartEntries = new ArrayList<AccChartEntry>();
		List<LedgerAccount> childLedgers = new ArrayList<LedgerAccount>();
		float totBalance = 0, balance;
		
		if (chartType.equals("balSheet")) {
			childLedgers = this.reportsDao.getGLBalancesByParent(parentID, "1", "2");
		} else {
			childLedgers = this.reportsDao.getGLBalancesByParent(parentID, "4", "5");
		}
		
		for (LedgerAccount ledgerAccount : childLedgers) {
			AccChartEntry accChartEntry = new AccChartEntry();
			accChartEntry.setName(ledgerAccount.getName());
			accChartEntry.setAccountNo(ledgerAccount.getAccountNo());
			accChartEntry.setAccChartEntries(new ArrayList<AccChartEntry>());
			try {
				balance = this.genLedgerBo.getBalance(ledgerAccount.getAccountNo(), ledgerAccount.getOrganisation().getId(), ledgerAccount.getOrgCoy().getId()).getCurrBalance();
				accChartEntry.setTotBalance(balance);
				totBalance += balance;
			} catch (LedgerException e) {
				e.printStackTrace();
			}
			accChartEntry.setShow(1);
			accChartEntry.setHasChildren(0);
			accChartEntries.add(accChartEntry);
		}
		
		parent.setAccChartEntries(accChartEntries);
		parent.setTotBalance(totBalance);
		return parent;
	}

	
	public CompanyAccChart getCompanyAccChart(int comp_id, String type, String chartType) {
		
		List<Organisation> organisations = this.organisationBo.fetchAll(comp_id);
		CompanyAccChart companyAccChart = new CompanyAccChart();
		List<BranchAccChart> branchAccCharts = new ArrayList<BranchAccChart>();
		
		for (Organisation organisation : organisations) {
			BranchAccChart branchAccChart = this.getBranchAccChart(organisation.getId(), type, chartType);
			branchAccCharts.add(branchAccChart);
		}
		
		companyAccChart.setBranchAccCharts(branchAccCharts);
		companyAccChart.setName(this.userIdentity.getOrganisation().getOrgCoy().getName());
		
		return companyAccChart;
	}
}
