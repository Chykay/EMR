package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.boInterface.BalSheetCatBo;
import org.calminfotech.ledger.daoInterface.BalSheetDao;
import org.calminfotech.ledger.forms.BalSheetForm;
import org.calminfotech.ledger.models.BalSheetCat;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalSheetCatBoImpl implements BalSheetCatBo {
	public List<BalSheetForm> balSheetCats;
	public BalSheetForm balSheetCat;
	
	@Autowired
	private BalSheetDao balSheetDao;
	
	@Autowired
	private UserIdentity userIdentity;
	
	public List<BalSheetCat> fetchAll(){
		return this.balSheetDao.fetchAll();
	}
	
	public List<BalSheetCat> fetchParents(int id){
		return this.balSheetDao.fetchParents(id);
	}
	
	public BalSheetCat getLedgerById(int id){
		return this.balSheetDao.getLedgerById(id);
	}
	
	public BalSheetCat save(BalSheetForm balSheetForm){
		BalSheetCat balSheetCat = new BalSheetCat();
		
		balSheetCat.setParentID(balSheetForm.getParentID());
		balSheetCat.setName(balSheetForm.getName());
		if (balSheetForm.getIsActive() == 1) {
			balSheetCat.setIsActive(true);
		} else {
			balSheetCat.setIsActive(false);
		}
		
		
		balSheetCat.setOrganisation(userIdentity.getOrganisation());
		System.out.println(userIdentity.getOrganisation().getId() + " and " + userIdentity.getOrganisation().getOrgCoy().getId());
		balSheetCat.setOrgCoy(userIdentity.getOrganisation().getOrgCoy());
		balSheetCat.setCreated_by(userIdentity.getUser());
		balSheetCat.setCreate_date(new Date(System.currentTimeMillis()));
		balSheetCat.setIs_deleted(false);
		
		this.balSheetDao.save(balSheetCat);
		return balSheetCat;
	}
	
	public void delete(BalSheetCat balSheetCat){
		this.balSheetDao.delete(balSheetCat);
	}
	
	public BalSheetCat update(BalSheetForm balSheetForm, int id){
		
		BalSheetCat balSheetCat = this.balSheetDao.getLedgerById(id);
	
		balSheetCat.setParentID(balSheetForm.getParentID());
		balSheetCat.setName(balSheetForm.getName());
		if (balSheetForm.getIsActive() == 1) {
			balSheetCat.setIsActive(true);
		} else {
			balSheetCat.setIsActive(false);
		}
		
		balSheetCat.setModified_by(userIdentity.getUser());
		balSheetCat.setModify_date(new Date(System.currentTimeMillis()));
		
		this.balSheetDao.update(balSheetCat);
		return balSheetCat;
	}
}
