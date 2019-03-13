package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.daoInterface.LedgerAccDao;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LedgerAccBoImpl implements LedgerAccBo {
	public List<LedgerAccForm> generalLedgers;
	public LedgerAccForm generalLedger;
	
	@Autowired
	private LedgerAccDao ledgerAccDao;

	@Autowired
	private UserIdentity userIdentity;
	
	
	public List<LedgerAccount> fetchAll(int branch_id, int company_id){
		return this.ledgerAccDao.fetchAll(branch_id, company_id);
	}
	
	public LedgerAccount getLedgerById(int id){
		return this.ledgerAccDao.getLedgerById(id);
	}
	
	public LedgerAccount getLedgerByAccount_no(String account_no){
		return this.ledgerAccDao.getLedgerByAccount_no(account_no);
	}
	
	public LedgerAccount save(LedgerAccForm ledgerAccForm){
		LedgerAccount ledgerAccount = new LedgerAccount();
		
		ledgerAccount.setCode(ledgerAccForm.getCode());
		ledgerAccount.setTotaling_code(ledgerAccForm.getTotaling_code());
		ledgerAccount.setAccount_no(ledgerAccForm.getAccount_no());
		ledgerAccount.setBal_sheet_cat_id(ledgerAccForm.getBal_sheet_cat_id());
		ledgerAccount.setName(ledgerAccForm.getName());
		if (ledgerAccForm.getIs_active() == 1) {
			ledgerAccount.setIs_active(true);
		} else {
			ledgerAccount.setIs_active(false);
		}
		
		
		ledgerAccount.setOrganisation(userIdentity.getOrganisation());
		System.out.println(userIdentity.getOrganisation().getId() + " and " + userIdentity.getOrganisation().getOrgCoy().getId());
		ledgerAccount.setOrgCoy(userIdentity.getOrganisation().getOrgCoy());
		ledgerAccount.setCreated_by(userIdentity.getUser());
		ledgerAccount.setCreate_date(new Date(System.currentTimeMillis()));
		ledgerAccount.setIs_deleted(false);
		ledgerAccount.setBalance(0);
		
		this.ledgerAccDao.save(ledgerAccount);
/*
		try {
			this.genLedgerBo.updateGLBalance(ledgerAccount);
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		*/
		return ledgerAccount;
	}
	
	public void delete(LedgerAccount ledgerAccount){
		this.ledgerAccDao.delete(ledgerAccount);
	}
	
	public LedgerAccount update(LedgerAccForm ledgerAccForm, int id){
		
		LedgerAccount ledgerAccount = this.ledgerAccDao.getLedgerById(id);
		

		ledgerAccount.setCode(ledgerAccForm.getCode());
		ledgerAccount.setTotaling_code(ledgerAccForm.getTotaling_code());
		ledgerAccount.setAccount_no(ledgerAccForm.getAccount_no());
		ledgerAccount.setBal_sheet_cat_id(ledgerAccForm.getBal_sheet_cat_id());
		ledgerAccount.setName(ledgerAccForm.getName());
		if (ledgerAccForm.getIs_active() == 1) {
			ledgerAccount.setIs_active(true);
		} else {
			ledgerAccount.setIs_active(false);
		}
		
		ledgerAccount.setModified_by(userIdentity.getUser());
		ledgerAccount.setModify_date(new Date(System.currentTimeMillis()));
		
		this.ledgerAccDao.update(ledgerAccount);
		return ledgerAccount;
	}
}
