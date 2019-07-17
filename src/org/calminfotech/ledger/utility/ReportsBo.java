package org.calminfotech.ledger.utility;

import java.util.ArrayList;
import java.util.List;

import org.calminfotech.ledger.boInterface.GLSetupBo;
import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.LedgerCatBo;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.forms.LedgerCatForm;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.LedgerCategory;
import org.calminfotech.ledger.reports.models.AccChartEntry;
import org.calminfotech.ledger.reports.models.BranchAccChart;
import org.calminfotech.ledger.reports.models.BranchTB;
import org.calminfotech.ledger.reports.models.CompanyAccChart;
import org.calminfotech.ledger.reports.models.CompanyTB;
import org.calminfotech.ledger.reports.models.TBReport;
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
	
	@Autowired
	private GLSetupBo glSetupBo;

	public BranchTB getBranchTB(int org_id) {
		List<TrialBalEntry> trialBalEntries = new ArrayList<TrialBalEntry>();
		float tot_debit = 0, tot_credit = 0, balance = 0;
		BranchTB branchTB = new BranchTB();
		
		List<GenLedgBalance> genLedgBalances = this.reportsDao.getGLBalances(org_id);
		
		for (GenLedgBalance genLedgBalance : genLedgBalances) {
			String accountNo = genLedgBalance.getGLAccountNo();
			if (accountNo.charAt(0) == '6') {
				continue;
			}
			
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
		branchTB.setCompanyName(this.organisationBo.getOrganisationById(org_id).getOrgCoy().getName());
		branchTB.setEntries(trialBalEntries);
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


	public BranchAccChart getBranchCoA(int branchID, String type, String chartType) {
		List<LedgerCategory> ledgerCategories = this.ledgerCatBo.fetchAll();

		List<AccChartEntry> rootAccCharts = new ArrayList<AccChartEntry>();
		List<AccChartEntry> emptyRootAccCharts = new ArrayList<AccChartEntry>();
		List<AccChartEntry> descAccCharts = new ArrayList<AccChartEntry>();
		BranchAccChart branchAccChart = new BranchAccChart();
		//float totbalance = 0;
		
		for (LedgerCategory ledgerCategory : ledgerCategories) {

			AccChartEntry accChartEntry = new AccChartEntry();
			accChartEntry.setName(ledgerCategory.getName());
			accChartEntry.setAccountNo("");
			accChartEntry.setId(ledgerCategory.getId());
			
			if (ledgerCategory.getParentID() == 0) {
				rootAccCharts.add(accChartEntry);
			} else {
				accChartEntry.setParentID(ledgerCategory.getParentID());
				descAccCharts.add(accChartEntry);
			}
		}
		
		for (AccChartEntry accChartEntry : rootAccCharts) {

			AccChartEntry self = this.getChartOfAccs(descAccCharts, accChartEntry.getId(), type, chartType);
			accChartEntry.setShow(self.getShow());
		
			if (self.getAccChartEntries().size() > 0) {
				accChartEntry.setAccChartEntries(self.getAccChartEntries());
				accChartEntry.setTotBalance(self.getTotBalance());
				accChartEntry.setHasChildren(1);
			} else {

				AccChartEntry returnedSelf = this.getChildLedgers(accChartEntry.getId(), type, chartType);
				
				if (returnedSelf.getAccChartEntries().size() > 0) {
					if (type.equals("full")) {
						accChartEntry.setAccChartEntries(returnedSelf.getAccChartEntries());
					} else {
						accChartEntry.setAccChartEntries(new ArrayList<AccChartEntry>());
					}
					accChartEntry.setTotBalance(returnedSelf.getTotBalance());
					accChartEntry.setShow(1);
					accChartEntry.setHasChildren(1);
				} else {
					accChartEntry.setAccChartEntries(new ArrayList<AccChartEntry>());
					accChartEntry.setHasChildren(-1);
				}
				
			}
			
			if (accChartEntry.getShow() == null) 
				emptyRootAccCharts.add(accChartEntry);
			//totbalance += accChartEntry.getTotBalance();
		}
		
/*
		for (AccChartEntry accChartEntry : rootAccCharts) {
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
		}
		*/
		System.out.println("AND NOW");
		rootAccCharts.removeAll(emptyRootAccCharts);
		
	
		
		branchAccChart.setAccChartEntries(rootAccCharts);
		branchAccChart.setBalance(rootAccCharts.get(0).getTotBalance() - rootAccCharts.get(1).getTotBalance());
		System.out.println(branchAccChart.getBalance() + " OVER HERE");
		branchAccChart.setName(this.organisationBo.getOrganisationById(branchID).getName());
		branchAccChart.setCompanyName(this.userIdentity.getOrganisation().getOrgCoy().getName());
		
		return branchAccChart;	
	}



	private AccChartEntry getChartOfAccs(List<AccChartEntry> descAccCharts, int parentID, String type, String chartType) {

		List<AccChartEntry> children = new ArrayList<AccChartEntry>();
		AccChartEntry parent = new AccChartEntry();
		float totBalance = 0;
		
		for (AccChartEntry accChartEntry : descAccCharts) {
			if(accChartEntry.getParentID() == parentID){
				AccChartEntry returnedSelf = this.getChartOfAccs(descAccCharts, accChartEntry.getId(), type, chartType);
				accChartEntry.setShow(returnedSelf.getShow());
				
				if (returnedSelf.getAccChartEntries().size() > 0) {
					accChartEntry.setAccChartEntries(returnedSelf.getAccChartEntries());
					accChartEntry.setHasChildren(1);
				} else {
					returnedSelf = this.getChildLedgers(accChartEntry.getId(), type, chartType);
					
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


	private AccChartEntry getChildLedgers(Integer parentID, String type, String chartType) {
		AccChartEntry parent = new AccChartEntry();
		List<AccChartEntry> accChartEntries = new ArrayList<AccChartEntry>();
		List<LedgerAccount> childLedgers = new ArrayList<LedgerAccount>();
		float totBalance = 0, balance;
		
		if (chartType.equals("balSheet")) {
			childLedgers = this.reportsDao.getGLBalancesByParentR(parentID, "1", "2");
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


	public CompanyAccChart getCompanyCoA(int comp_id, String type, String chartType) {

		
		List<Organisation> organisations = this.organisationBo.fetchAll(comp_id);
		CompanyAccChart companyAccChart = new CompanyAccChart();
		List<BranchAccChart> branchAccCharts = new ArrayList<BranchAccChart>();
		
		for (Organisation organisation : organisations) {

			BranchAccChart branchAccChart = this.getBranchCoA(organisation.getId(), type, chartType);

			branchAccCharts.add(branchAccChart);
		}
		
		companyAccChart.setBranchAccCharts(branchAccCharts);
		companyAccChart.setName(this.userIdentity.getOrganisation().getOrgCoy().getName());
		
		return companyAccChart;
	}


	public TBReport GLReport(String accountNo) {
		TBReport tbReport = new TBReport();
		List<TrialBalEntry> trialBalEntries = new ArrayList<TrialBalEntry>();
		float totDebit = 0, totCredit = 0;
		List<GLEntry> glEntries = new ArrayList<GLEntry>();
		try {
			glEntries = this.genLedgerBo.getGLEntriesListing(accountNo, "", "2222-09-09");
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		for (GLEntry glEntry : glEntries) {
			String postCode = glEntry.getPostCode();
			float amount = Math.abs(glEntry.getAmount());
			
			TrialBalEntry trialBalEntry = new TrialBalEntry();
			trialBalEntry.setAccountNo(accountNo);
			trialBalEntry.setName(glEntry.getName());
			
			
			if (postCode.equals("DR")) {
				trialBalEntry.setDebit(amount);
				totDebit += amount;
			} else {
				trialBalEntry.setCredit(amount);
				totCredit += amount;
			}
				
			
			trialBalEntries.add(trialBalEntry);
		}
		
		tbReport.setName(accountNo);
		tbReport.setEntries(trialBalEntries);
		tbReport.setTotCredit(totCredit);
		tbReport.setTotDebit(totDebit);
		tbReport.setTotBalance(totCredit - totDebit);
		return tbReport;
	}
	
	public BranchAccChart addReserve(int branchID, String type) {
		Organisation org = this.userIdentity.getOrganisation();
		BranchAccChart branchAccChart = new BranchAccChart();
		float reserve = this.getBranchCoA(branchID, type, "PandL").getBalance();
		float balance = 0;
		Boolean removeTemp = false;
		int catID = 0, ledgerID = 0;
		
		LedgerAccount reserveGL = this.glSetupBo.getReserveGL();
		
		if (reserveGL == null) {
			removeTemp = true;
			// TODO NOt yet complete, try create a new category, get the id, set a field, if field is set, remove the category at the last line, just before the return
			LedgerCatForm ledgerCatForm = new LedgerCatForm();
			ledgerCatForm.setName("RESERVE");
			ledgerCatForm.setParentID(0);
			catID = this.ledgerCatBo.save(ledgerCatForm).getId();
			
			LedgerAccForm ledgerAccForm = new LedgerAccForm();
			
			ledgerAccForm.setCode("000");
			ledgerAccForm.setTotalingCode("0000");
			ledgerAccForm.setAccountNo("6-0000-000");
			ledgerAccForm.setLedgerCatID(catID);
			ledgerAccForm.setName("Reserve");
			ledgerAccForm.setIsActive(1);
			
			ledgerID = this.ledgerAccBo.save(ledgerAccForm).getId();
		}
		try {
			balance = this.genLedgerBo.getBalance(reserveGL.getAccountNo(), org.getId(), org.getOrgCoy().getId()).getCurrBalance();
			reserveGL.setAmount(reserve - balance);
			this.genLedgerBo.updateGLBalance(reserveGL, org.getId());
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		
		branchAccChart = this.getBranchCoA(branchID, type, "balSheet");
		
		
		if (removeTemp) {
			this.ledgerCatBo.delete(this.ledgerCatBo.getLedgerById(catID));
			
			this.ledgerAccBo.delete(this.ledgerAccBo.getLedgerById(ledgerID));
		}
		return branchAccChart;
	}
}
