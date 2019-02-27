package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.daoInterface.GenLedgerDao;
import org.calminfotech.ledger.forms.GenLedgerForm;
import org.calminfotech.ledger.models.GeneralLedger;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GenLedgerBoImpl implements GenLedgerBo {
	public List<GenLedgerForm> generalLedgers;
	public GenLedgerForm generalLedger;
	
	@Autowired
	private GenLedgerDao genLedgerDao;
	
	@Autowired
	private UserIdentity userIdentity;
	
	public List<GeneralLedger> fetchAll(){
		return this.genLedgerDao.fetchAll();
	}
	
	public GeneralLedger getLedgerById(int id){
		return this.genLedgerDao.getLedgerById(id);
	}
	
	public GeneralLedger save(GenLedgerForm genLedgerForm){
		GeneralLedger generalLedger = new GeneralLedger();
		
		generalLedger.setCode(genLedgerForm.getCode());
		generalLedger.setTotaling_code(genLedgerForm.getTotaling_code());
		generalLedger.setAccount_no(genLedgerForm.getAccount_no());
		generalLedger.setBal_sheet_cat_id(genLedgerForm.getBal_sheet_cat_id());
		generalLedger.setName(genLedgerForm.getName());
		if (genLedgerForm.getIs_active() == 1) {
			generalLedger.setIs_active(true);
		} else {
			generalLedger.setIs_active(false);
		}
		
		
		generalLedger.setOrganisation(userIdentity.getOrganisation());
		System.out.println(userIdentity.getOrganisation().getId() + " and " + userIdentity.getOrganisation().getOrgCoy().getId());
		generalLedger.setOrgCoy(userIdentity.getOrganisation().getOrgCoy());
		generalLedger.setCreated_by(userIdentity.getUser());
		generalLedger.setCreate_date(new Date(System.currentTimeMillis()));
		generalLedger.setIs_deleted(false);
		
		this.genLedgerDao.save(generalLedger);
		return generalLedger;
	}
	
	public void delete(GeneralLedger generalLedger){
		this.genLedgerDao.delete(generalLedger);
	}
	
	public GeneralLedger update(GenLedgerForm genLedgerForm, int id){
		
		GeneralLedger generalLedger = this.genLedgerDao.getLedgerById(id);
		

		generalLedger.setCode(genLedgerForm.getCode());
		generalLedger.setTotaling_code(genLedgerForm.getTotaling_code());
		generalLedger.setAccount_no(genLedgerForm.getAccount_no());
		generalLedger.setBal_sheet_cat_id(genLedgerForm.getBal_sheet_cat_id());
		generalLedger.setName(genLedgerForm.getName());
		if (genLedgerForm.getIs_active() == 1) {
			generalLedger.setIs_active(true);
		} else {
			generalLedger.setIs_active(false);
		}
		
		generalLedger.setModified_by(userIdentity.getUser());
		generalLedger.setModify_date(new Date(System.currentTimeMillis()));
		
		this.genLedgerDao.update(generalLedger);
		return generalLedger;
	}
}
