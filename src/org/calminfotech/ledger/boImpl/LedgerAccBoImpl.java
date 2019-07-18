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
	
	
	public List<LedgerAccount> fetchAll(int company_id){
		List<LedgerAccount> list = null;
		try {
			
			list = this.ledgerAccDao.fetchAll(company_id);	
		} catch (Exception e) {
			System.out.println("null");
		}
		
		return list;
	}
	
	public List<LedgerAccount> fetchTop100(int company_id){
		List<LedgerAccount> list = null;
		try {
			list = this.ledgerAccDao.fetchTop100(company_id);	
		} catch (Exception e) {
			System.out.println("null");
		}
		
		return list;
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
		ledgerAccount.setTotalingCode(ledgerAccForm.getTotalingCode());
		ledgerAccount.setAccountNo(ledgerAccForm.getAccountNo());
		ledgerAccount.setLedgerCatID(ledgerAccForm.getLedgerCatID());
		ledgerAccount.setName(ledgerAccForm.getName());
		if (ledgerAccForm.getIsActive() == 1) {
			ledgerAccount.setIsActive(true);
		} else {
			ledgerAccount.setIsActive(false);
		}
		
		
		ledgerAccount.setOrganisation(userIdentity.getOrganisation());
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
		ledgerAccount.setTotalingCode(ledgerAccForm.getTotalingCode());
		ledgerAccount.setAccountNo(ledgerAccForm.getAccountNo());
		ledgerAccount.setLedgerCatID(ledgerAccForm.getLedgerCatID());
		ledgerAccount.setName(ledgerAccForm.getName());
		if (ledgerAccForm.getIsActive() == 1) {
			ledgerAccount.setIsActive(true);
		} else {
			ledgerAccount.setIsActive(false);
		}
		
		ledgerAccount.setModified_by(userIdentity.getUser());
		ledgerAccount.setModify_date(new Date(System.currentTimeMillis()));
		
		this.ledgerAccDao.update(ledgerAccount);
		return ledgerAccount;
	}

	@Override
	public List<LedgerAccount> getAssetLedgers() {
	
		return this.ledgerAccDao.getAssetLedgers();
	}

	@Override
	public boolean isTotUsed(String code) {

		return this.ledgerAccDao.isTotUsed(code);
	}
	
	
	public void updateStatus(LedgerAccount ledgerAccount) {

		ledgerAccount.setIsActive(!ledgerAccount.getIsActive());
		
		
		ledgerAccount.setModified_by(userIdentity.getUser());
		ledgerAccount.setModify_date(new Date(System.currentTimeMillis()));
		
		this.ledgerAccDao.update(ledgerAccount);
	}

	public boolean isUsed(String accountNo) {
		return this.ledgerAccDao.isUsed(accountNo);
	}
}
