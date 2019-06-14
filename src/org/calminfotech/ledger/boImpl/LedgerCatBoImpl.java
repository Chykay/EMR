package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.boInterface.LedgerCatBo;
import org.calminfotech.ledger.daoInterface.LedgerCatDao;
import org.calminfotech.ledger.forms.LedgerCatForm;
import org.calminfotech.ledger.models.LedgerCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LedgerCatBoImpl implements LedgerCatBo {
	public List<LedgerCatForm> ledgerCategorys;
	public LedgerCatForm ledgerCategory;
	
	@Autowired
	private LedgerCatDao ledgerCatDao;
	
	@Autowired
	private UserIdentity userIdentity;
	
	public List<LedgerCategory> fetchAll(){
		return this.ledgerCatDao.fetchAll();
	}
	
	public List<LedgerCategory> fetchParents(int id){
		return this.ledgerCatDao.fetchParents(id);
	}
	
	public LedgerCategory getLedgerById(int id){
		return this.ledgerCatDao.getLedgerById(id);
	}
	
	public LedgerCategory save(LedgerCatForm balSheetForm){
		LedgerCategory ledgerCategory = new LedgerCategory();
		
		ledgerCategory.setParentID(balSheetForm.getParentID());
		ledgerCategory.setName(balSheetForm.getName());
		if (balSheetForm.getIsActive() == 1) {
			ledgerCategory.setIsActive(true);
		} else {
			ledgerCategory.setIsActive(false);
		}
		
		
		ledgerCategory.setOrganisation(userIdentity.getOrganisation());
		System.out.println(userIdentity.getOrganisation().getId() + " and " + userIdentity.getOrganisation().getOrgCoy().getId());
		ledgerCategory.setOrgCoy(userIdentity.getOrganisation().getOrgCoy());
		ledgerCategory.setCreated_by(userIdentity.getUser());
		ledgerCategory.setCreate_date(new Date(System.currentTimeMillis()));
		ledgerCategory.setIs_deleted(false);
		
		this.ledgerCatDao.save(ledgerCategory);
		return ledgerCategory;
	}
	
	public void delete(LedgerCategory ledgerCategory){
		this.ledgerCatDao.delete(ledgerCategory);
	}
	
	public LedgerCategory update(LedgerCatForm balSheetForm, int id){
		
		LedgerCategory ledgerCategory = this.ledgerCatDao.getLedgerById(id);
	
		ledgerCategory.setParentID(balSheetForm.getParentID());
		ledgerCategory.setName(balSheetForm.getName());
		if (balSheetForm.getIsActive() == 1) {
			ledgerCategory.setIsActive(true);
		} else {
			ledgerCategory.setIsActive(false);
		}
		
		ledgerCategory.setModified_by(userIdentity.getUser());
		ledgerCategory.setModify_date(new Date(System.currentTimeMillis()));
		
		this.ledgerCatDao.update(ledgerCategory);
		return ledgerCategory;
	}

	@Override
	public List<LedgerCategory> fetchAllByOrg(int orgID) {
		
		return this.ledgerCatDao.fetchAllByOrg(orgID);
		
	}
}
