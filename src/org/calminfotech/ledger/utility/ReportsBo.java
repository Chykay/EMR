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


	public BranchAccChart getBranchBalSheet(int branchID, String type, String chartType) {
		List<LedgerCategory> ledgerCategories = this.ledgerCatBo.fetchAllByOrg(branchID);
		List<AccChartEntry> rootBalSheets = new ArrayList<AccChartEntry>();
		List<AccChartEntry> descBalSheets = new ArrayList<AccChartEntry>();
		BranchAccChart branchAccChart = new BranchAccChart();
		
		for (LedgerCategory ledgerCategory : ledgerCategories) {

			AccChartEntry balSheet = new AccChartEntry();
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
		
		for (AccChartEntry accChartEntry : rootBalSheets) {
			AccChartEntry self = this.getBalSheets(descBalSheets, accChartEntry.getId(), type, chartType);
			if (self.getAccChartEntries().size() > 0) {
				accChartEntry.setAccChartEntries(self.getAccChartEntries());
				accChartEntry.setTotBalance(self.getTotBalance());
				accChartEntry.setHasChildren(1);
			} else {
				accChartEntry.setAccChartEntries(new ArrayList<AccChartEntry>());
				accChartEntry.setHasChildren(-1);
			}
		}
		
		branchAccChart.setAccChartEntries(rootBalSheets);
		branchAccChart.setName(this.organisationBo.getOrganisationById(branchID).getName());
		
		return branchAccChart;	
	}


	private AccChartEntry getBalSheets(List<AccChartEntry> descBalSheets, int parentID, String type, String chartType) {
		List<AccChartEntry> children = new ArrayList<AccChartEntry>();
		AccChartEntry parent = new AccChartEntry();
		float totBalance = 0;
		
		for (AccChartEntry accChartEntry : descBalSheets) {
			if(accChartEntry.getParentID() == parentID){
				AccChartEntry returnedSelf = this.getBalSheets(descBalSheets, accChartEntry.getId(), type, chartType);
				
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
			AccChartEntry balSheet = new AccChartEntry();
			balSheet.setName(ledgerAccount.getName());
			balSheet.setAccountNo(ledgerAccount.getAccountNo());
			balSheet.setAccChartEntries(new ArrayList<AccChartEntry>());
			try {
				balance = this.genLedgerBo.getBalance(ledgerAccount.getAccountNo(), ledgerAccount.getOrganisation().getId(), ledgerAccount.getOrgCoy().getId()).getCurrBalance();
				balSheet.setTotBalance(balance);
				totBalance += balance;
			} catch (LedgerException e) {
				e.printStackTrace();
			}
			balSheet.setHasChildren(0);
			accChartEntries.add(balSheet);
		}
		
		parent.setAccChartEntries(accChartEntries);
		parent.setTotBalance(totBalance);
		return parent;
	}

	
	public CompanyAccChart getCompanyBalSheet(int comp_id, String type, String chartType) {
		
		List<Organisation> organisations = this.organisationBo.fetchAll(comp_id);
		CompanyAccChart companyAccChart = new CompanyAccChart();
		List<BranchAccChart> branchAccCharts = new ArrayList<BranchAccChart>();
		
		for (Organisation organisation : organisations) {
			BranchAccChart branchAccChart = this.getBranchBalSheet(organisation.getId(), type, chartType);
			branchAccCharts.add(branchAccChart);
		}
		
		companyAccChart.setBranchAccCharts(branchAccCharts);
		companyAccChart.setName(this.userIdentity.getOrganisation().getOrgCoy().getName());
		
		return companyAccChart;
	}
}
