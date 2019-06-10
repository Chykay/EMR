package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.boInterface.TotAccBo;
import org.calminfotech.ledger.daoInterface.TotAccDao;
import org.calminfotech.ledger.forms.TotalingForm;
import org.calminfotech.ledger.models.LedgerType;
import org.calminfotech.ledger.models.TotalingAccount;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotAccBoImpl implements  TotAccBo{
	public List<TotalingForm> totalingAccounts;
	public TotalingForm totalingAccount;
	
	@Autowired
	private TotAccDao totAccDao;
	
	@Autowired
	private UserIdentity userIdentity;
	
	public List<TotalingAccount> fetchAll(){
		return this.totAccDao.fetchAll();
	}
	
	public TotalingAccount getLedgerById(int id){
		return this.totAccDao.getLedgerById(id);
	}
	
	public TotalingAccount save(TotalingForm totalingForm){
		TotalingAccount totalingAccount = new TotalingAccount();
		
		LedgerType ledgerType = new LedgerType();
		ledgerType.setId(totalingForm.getLedgerType());
		
		totalingAccount.setLedgerType(ledgerType);
		totalingAccount.setCode(totalingForm.getCode());
		totalingAccount.setName(totalingForm.getName());
		if (totalingForm.getIsActive() == 1) {
			totalingAccount.setIsActive(true);
		} else {
			totalingAccount.setIsActive(false);
		}
		
		
		totalingAccount.setOrganisation(userIdentity.getOrganisation());
		System.out.print(userIdentity.getOrganisation().getName());
		System.out.println(": " + userIdentity.getOrganisation().getOrgCoy().getName());
		totalingAccount.setOrgCoy(userIdentity.getOrganisation().getOrgCoy());
		totalingAccount.setCreated_by(userIdentity.getUser());
		totalingAccount.setCreate_date(new Date(System.currentTimeMillis()));
		totalingAccount.setIs_deleted(false);
		
		this.totAccDao.save(totalingAccount);
		return totalingAccount;
	}
	
	public void delete(TotalingAccount totalingAccount){
		this.totAccDao.delete(totalingAccount);
	}
	
	public TotalingAccount update(TotalingForm totalingForm, int id){
		
		TotalingAccount totalingAccount = this.totAccDao.getLedgerById(id);
		

		LedgerType ledgerType = new LedgerType();
		ledgerType.setId(totalingForm.getLedgerType());
		
		totalingAccount.setLedgerType(ledgerType);
		totalingAccount.setCode(totalingForm.getCode());
		totalingAccount.setName(totalingForm.getName());
		if (totalingForm.getIsActive() == 1) {
			totalingAccount.setIsActive(true);
		} else {
			totalingAccount.setIsActive(false);
		}
		
		totalingAccount.setModified_by(userIdentity.getUser());
		totalingAccount.setModify_date(new Date(System.currentTimeMillis()));
		
		this.totAccDao.update(totalingAccount);
		return totalingAccount;
	}
}
