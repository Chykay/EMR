package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.LedgerTotallingBo;
import org.calminfotech.ledger.daoInterface.LedgerTotallingDao;
import org.calminfotech.ledger.forms.TotalingForm;
import org.calminfotech.ledger.models.LedgerType;
import org.calminfotech.ledger.models.TotalingCode;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LedgerTotallingBoImpl implements  LedgerTotallingBo{
	public List<TotalingForm> totalingAccounts;
	public TotalingForm totalingAccount;
	
	@Autowired
	private LedgerTotallingDao ledgerTotallingDao;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private LedgerAccBo ledgerAccBo;

	public List<TotalingCode> fetchAll(){
		return this.ledgerTotallingDao.fetchAll();
	}

	public List<TotalingCode> fetchAllActive(){
		return this.ledgerTotallingDao.fetchAllActive();
	}
	
	public TotalingCode getLedgerById(int id){
		return this.ledgerTotallingDao.getLedgerById(id);
	}
	
	public TotalingCode save(TotalingForm totalingForm){
		TotalingCode totalingCode = new TotalingCode();
		
		LedgerType ledgerType = new LedgerType();
		ledgerType.setId(totalingForm.getLedgerType());
		
		totalingCode.setLedgerType(ledgerType);
		totalingCode.setCode(totalingForm.getCode());
		totalingCode.setName(totalingForm.getName());
		if (totalingForm.getIsActive() == 1) {
			totalingCode.setIsActive(true);
		} else {
			totalingCode.setIsActive(false);
		}
		
		
		totalingCode.setOrganisation(userIdentity.getOrganisation());
		System.out.print(userIdentity.getOrganisation().getName());
		System.out.println(": " + userIdentity.getOrganisation().getOrgCoy().getName());
		totalingCode.setOrgCoy(userIdentity.getOrganisation().getOrgCoy());
		totalingCode.setCreated_by(userIdentity.getUser());
		totalingCode.setCreate_date(new Date(System.currentTimeMillis()));
		totalingCode.setIs_deleted(false);
		
		this.ledgerTotallingDao.save(totalingCode);
		return totalingCode;
	}
	
	public void delete(TotalingCode totalingCode){
		this.ledgerTotallingDao.delete(totalingCode);
	}
	
	public TotalingCode update(TotalingForm totalingForm, int id){
		TotalingCode totalingCode = this.getLedgerById(id);
		
		LedgerType ledgerType = new LedgerType();
		ledgerType.setId(totalingForm.getLedgerType());
		
		totalingCode.setLedgerType(ledgerType);
		totalingCode.setCode(totalingForm.getCode());
		totalingCode.setName(totalingForm.getName());
		if (totalingForm.getIsActive() == 1) {
			totalingCode.setIsActive(true);
		} else {
			totalingCode.setIsActive(false);
		}
		
		totalingCode.setModified_by(userIdentity.getUser());
		totalingCode.setModify_date(new Date(System.currentTimeMillis()));

		this.ledgerTotallingDao.update(totalingCode);
		return totalingCode;
		
	}
	
	public TotalingCode updateStatus(TotalingCode totalingCode) {

		totalingCode.setIsActive(!totalingCode.getIsActive());
		
		
		totalingCode.setModified_by(userIdentity.getUser());
		totalingCode.setModify_date(new Date(System.currentTimeMillis()));
		
		this.ledgerTotallingDao.update(totalingCode);
		return totalingCode;
	}

	@Override
	public boolean isUsed(TotalingCode totalingCode) {
		return this.ledgerAccBo.isTotUsed(totalingCode.getCode());
		
	}
}
