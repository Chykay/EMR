package org.calminfotech.ledger.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.calminfotech.ledger.boInterface.GLMappingBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.LedgerCatBo;
import org.calminfotech.ledger.boInterface.LedgerPostingBo;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.forms.LedgerCatForm;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.LedgerCategory;
import org.calminfotech.ledger.reports.models.AccChartEntry;
import org.calminfotech.ledger.reports.models.AccChartReport;
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
	private LedgerPostingBo ledgerPostingBo;
	
	@Autowired
	private GLMappingBo glSetupBo;

	public TBReport getBranchTB(int org_id) {
		List<TrialBalEntry> trialBalEntries = new ArrayList<TrialBalEntry>();
		float tot_debit = 0, tot_credit = 0, balance = 0;
		TBReport tbReport = new TBReport();
		
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
		
		
		tbReport.setBranchName(this.organisationBo.getOrganisationById(org_id).getName());
		tbReport.setCompanyName(this.organisationBo.getOrganisationById(org_id).getOrgCoy().getName());
		tbReport.setEntries(trialBalEntries);
		tbReport.setTotBalance(tot_credit - tot_debit);
		tbReport.setTotCredit(tot_credit);
		tbReport.setTotDebit(tot_debit);
		return tbReport;
	}
	
	public TBReport getCompanyTB(int comp_id) {
		List<TrialBalEntry> trialBalEntries = new ArrayList<TrialBalEntry>();
		float tot_debit = 0, tot_credit = 0, balance = 0;
		TBReport tbReport = new TBReport();
		
		List<Object> genLedgBalances = this.reportsDao.getGLBalancesCompany();
		 Iterator<Object> itr = genLedgBalances.iterator();
	      while(itr.hasNext()){
	        Object[] obj = (Object[]) itr.next();
	        
	        String accountNo = String.valueOf(obj[0]);
			if (accountNo.charAt(0) == '6') {
				continue;
			}
			
			balance = Float.parseFloat(String.valueOf(obj[1])); 
			
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
		
		
		tbReport.setCompanyName(this.userIdentity.getOrganisation().getOrgCoy().getName());
		tbReport.setEntries(trialBalEntries);
		tbReport.setTotBalance(tot_credit - tot_debit);
		tbReport.setTotCredit(tot_credit);
		tbReport.setTotDebit(tot_debit);
		return tbReport;
	}

	public TBReport GLReport(String accountNo) {
		TBReport tbReport = new TBReport();
		List<TrialBalEntry> trialBalEntries = new ArrayList<TrialBalEntry>();
		float totDebit = 0, totCredit = 0;
		List<GLEntry> glEntries = new ArrayList<GLEntry>();
		
		LedgerAccount ledgerAccount = this.ledgerAccBo.getLedgerByAccount_no(accountNo);
		
		try {
			glEntries = this.ledgerPostingBo.getGLEntriesListing(accountNo, "", "2222-09-09");
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		for (GLEntry glEntry : glEntries) {
			String postCode = glEntry.getPostCode();
			float amount = Math.abs(glEntry.getAmount());
			
			TrialBalEntry trialBalEntry = new TrialBalEntry();
			trialBalEntry.setAccountNo(accountNo);
			trialBalEntry.setName(glEntry.getName());
			trialBalEntry.setCreateDate(glEntry.getCreate_date().toString());
			trialBalEntry.setEffectiveDate(glEntry.getPostingDate().toString());
			
			if (postCode.equals("DR")) {
				trialBalEntry.setDebit(amount);
				totDebit += amount;
			} else {
				trialBalEntry.setCredit(amount);
				totCredit += amount;
			}
				
			
			trialBalEntries.add(trialBalEntry);
		}
		
		tbReport.setName(ledgerAccount.getName() + "( " + ledgerAccount.getAccountNo() +")");
		tbReport.setEntries(trialBalEntries);
		tbReport.setTotCredit(totCredit);
		tbReport.setTotDebit(totDebit);
		tbReport.setTotBalance(totCredit - totDebit);
		tbReport.setBranchName(this.userIdentity.getOrganisation().getName());
		tbReport.setCompanyName(this.userIdentity.getOrganisation().getOrgCoy().getName());
		return tbReport;
	}
	
	public TBReport GLReportCompany(Integer companyID, String accountNo) {
		TBReport tbReport = new TBReport();
		List<TrialBalEntry> trialBalEntries = new ArrayList<TrialBalEntry>();
		float totDebit = 0, totCredit = 0;
		List<GLEntry> glEntries = new ArrayList<GLEntry>();
		
		LedgerAccount ledgerAccount = this.ledgerAccBo.getLedgerByAccount_no(accountNo);
		
		try {
			glEntries = this.ledgerPostingBo.getGLEntriesListingCom(accountNo, "", "2222-09-09");
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		for (GLEntry glEntry : glEntries) {
			String postCode = glEntry.getPostCode();
			float amount = Math.abs(glEntry.getAmount());
			
			TrialBalEntry trialBalEntry = new TrialBalEntry();
			trialBalEntry.setAccountNo(accountNo);
			trialBalEntry.setName(glEntry.getName());
			trialBalEntry.setCreateDate(glEntry.getCreate_date().toString());
			trialBalEntry.setEffectiveDate(glEntry.getPostingDate().toString());
			
			if (postCode.equals("DR")) {
				trialBalEntry.setDebit(amount);
				totDebit += amount;
			} else {
				trialBalEntry.setCredit(amount);
				totCredit += amount;
			}
				
			
			trialBalEntries.add(trialBalEntry);
		}
		
		tbReport.setName(ledgerAccount.getName() + "( " + ledgerAccount.getAccountNo() +")");
		tbReport.setEntries(trialBalEntries);
		tbReport.setTotCredit(totCredit);
		tbReport.setTotDebit(totDebit);
		tbReport.setTotBalance(totCredit - totDebit);
		tbReport.setCompanyName(this.userIdentity.getOrganisation().getOrgCoy().getName());
		return tbReport;
	}

	public AccChartReport getCoAReport(int branchID, String viewType, String chartType, String rangeType) {
		List<LedgerCategory> ledgerCategories = this.ledgerCatBo.fetchAll();

		List<AccChartEntry> rootAccCharts = new ArrayList<AccChartEntry>();
		List<AccChartEntry> emptyRootAccCharts = new ArrayList<AccChartEntry>();
		List<AccChartEntry> descAccCharts = new ArrayList<AccChartEntry>();
		AccChartReport accChartReport = new AccChartReport();
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

			AccChartEntry self = this.getCoACategories(descAccCharts, accChartEntry.getId(), viewType, chartType, rangeType, branchID);
			accChartEntry.setShow(self.getShow());
		
			if (self.getAccChartEntries().size() > 0) {
				accChartEntry.setAccChartEntries(self.getAccChartEntries());
				accChartEntry.setTotBalance(self.getTotBalance());
				accChartEntry.setHasChildren(1);
			} else {

				AccChartEntry returnedSelf = this.getCoALedgers(accChartEntry.getId(), viewType, chartType, rangeType, branchID);
				
				if (returnedSelf.getAccChartEntries().size() > 0) {
					if (viewType.equals("full")) {
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
		
	
		
		accChartReport.setAccChartEntries(rootAccCharts);
		accChartReport.setBranchName(this.organisationBo.getOrganisationById(branchID).getName());
		accChartReport.setCompanyName(this.userIdentity.getOrganisation().getOrgCoy().getName());
		
		//ONLY USED FOR P_L
		accChartReport.setBalance(Math.abs(rootAccCharts.get(0).getTotBalance()) - Math.abs(rootAccCharts.get(1).getTotBalance()));
		
		return accChartReport;	
	}

	private AccChartEntry getCoACategories(List<AccChartEntry> descAccCharts, int parentID, String viewType, String chartType, String rangeType, int branchID) {

		List<AccChartEntry> children = new ArrayList<AccChartEntry>();
		AccChartEntry parent = new AccChartEntry();
		float totBalance = 0;
		
		for (AccChartEntry accChartEntry : descAccCharts) {
			if(accChartEntry.getParentID() == parentID){
				AccChartEntry returnedSelf = this.getCoACategories(descAccCharts, accChartEntry.getId(), viewType, chartType, rangeType, branchID);
				accChartEntry.setShow(returnedSelf.getShow());
				
				if (returnedSelf.getAccChartEntries().size() > 0) {
					accChartEntry.setAccChartEntries(returnedSelf.getAccChartEntries());
					accChartEntry.setHasChildren(1);
				} else {
					returnedSelf = this.getCoALedgers(accChartEntry.getId(), viewType, chartType, rangeType, branchID);
					
					if (returnedSelf.getAccChartEntries().size() > 0) {
						if (viewType.equals("full")) {
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

	private AccChartEntry getCoALedgers(Integer parentID, String viewType, String chartType, String rangeType, int branchID) {
		AccChartEntry parent = new AccChartEntry();
		List<AccChartEntry> accChartEntries = new ArrayList<AccChartEntry>();
		List<LedgerAccount> childLedgers = new ArrayList<LedgerAccount>();
		float totBalance = 0, balance = 0;
		
		if (chartType.equals("balSheet")) {
			
			childLedgers = this.reportsDao.getGLBalancesByParentReserve(parentID, "1", "2", "3");
			
		} else {
			
			childLedgers = this.reportsDao.getGLBalancesByParent(parentID, "4", "5");
			
		}
		
		for (LedgerAccount ledgerAccount : childLedgers) {
			AccChartEntry accChartEntry = new AccChartEntry();
			accChartEntry.setName(ledgerAccount.getName());
			accChartEntry.setAccountNo(ledgerAccount.getAccountNo());
			accChartEntry.setAccChartEntries(new ArrayList<AccChartEntry>());
			try {
				if (rangeType.equals("branch")) 
					balance = this.ledgerPostingBo.getBalance(ledgerAccount.getAccountNo(), branchID, ledgerAccount.getOrgCoy().getId()).getCurrBalance();
				else
					balance = this.ledgerPostingBo.getBalanceCompany(ledgerAccount.getAccountNo(), ledgerAccount.getOrgCoy().getId()).getCurrBalance();
				
				System.out.println("KUYO" + balance);
					
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


	public AccChartReport getCoAReportWithReserveGL(int branchID, String viewType, String rangeType) {
		
		Organisation org = this.userIdentity.getOrganisation();
		AccChartReport accChartReport = new AccChartReport();
		
		//GET THE VALUE OF THE PandL
		float reserve = this.getCoAReport(branchID, viewType, "PandL", rangeType).getBalance();
		System.out.println(reserve + "RESERVE");
		float balance = 0;
		Boolean removetemp = false;
		int tempCatID = 0, tempLedgerID = 0;
		
		//GET THE RESERVE GL, IF IT DOES NOT EXIST, CREATE A TEMP GL AND A TEMP CATEGORY
		LedgerAccount reserveGL = this.glSetupBo.getReserveGL();
		
		if (reserveGL == null) {
			System.out.println("the issue");
			removetemp = true;

			LedgerCatForm ledgerCatForm = new LedgerCatForm();
			ledgerCatForm.setName("RESERVE");
			ledgerCatForm.setParentID(0);
			tempCatID = this.ledgerCatBo.save(ledgerCatForm).getId();
			
			LedgerAccForm ledgerAccForm = new LedgerAccForm();
			
			ledgerAccForm.setCode("000");
			ledgerAccForm.setTotalingCode("0000");
			ledgerAccForm.setAccountNo("6-0000-000");
			ledgerAccForm.setLedgerCatID(tempCatID);
			ledgerAccForm.setName("Reserve");
			ledgerAccForm.setIsActive(1);
			
			tempLedgerID = this.ledgerAccBo.save(ledgerAccForm).getId();
		}
		
		// MAKE SURE THE RESERVE GL BALANCE IS UP TO DATE
		try {
			if (rangeType.equals("branch")) 
				balance = this.ledgerPostingBo.getBalance(reserveGL.getAccountNo(), branchID, org.getOrgCoy().getId()).getCurrBalance();
			else
				balance = this.ledgerPostingBo.getBalanceCompany(reserveGL.getAccountNo(), org.getOrgCoy().getId()).getCurrBalance();
			
			reserveGL.setAmount(reserve - balance);
			this.ledgerPostingBo.updateGLBalance(reserveGL, branchID);
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		//THE REPORT WILL NOW CONTAIN A UPDATED RESERVE GL 
		accChartReport = this.getCoAReport(branchID, viewType, "balSheet", rangeType);
		
		
		if (removetemp) {
			this.ledgerCatBo.delete(this.ledgerCatBo.getLedgerById(tempCatID));
			
			this.ledgerAccBo.delete(this.ledgerAccBo.getLedgerById(tempLedgerID));
		}
		return accChartReport;
	}

}
