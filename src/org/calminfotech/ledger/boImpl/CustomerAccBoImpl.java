package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.boInterface.CustomerAccBo;
import org.calminfotech.ledger.daoInterface.CustomerAccDao;
import org.calminfotech.ledger.models.CustomerAccount;
import org.calminfotech.ledger.models.CustomerEntry;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccBoImpl implements CustomerAccBo {
	public List<CustomerAccount> generalCustomers;
	public CustomerAccount generalCustomer;
	
	@Autowired
	private CustomerAccDao customerAccDao;

	@Autowired
	private UserIdentity userIdentity;
	
	
	public List<CustomerAccount> fetchAll(int branch_id, int company_id){
		return this.customerAccDao.fetchAll(branch_id, company_id);
	}
	
	public CustomerAccount getCustomerById(int id){
		return this.customerAccDao.getLedgerById(id);
	}
	
	public CustomerAccount getCustomerByAccount_no(String account_no){
		return this.customerAccDao.getLedgerByAccount_no(account_no);
	}
	
	public CustomerAccount save(CustomerAccount customerAccount){
		
		customerAccount.setOrganisation(userIdentity.getOrganisation());
		System.out.println(userIdentity.getOrganisation().getId() + " and " + userIdentity.getOrganisation().getOrgCoy().getId());
		customerAccount.setOrgCoy(userIdentity.getOrganisation().getOrgCoy());
		customerAccount.setCreated_by(userIdentity.getUser());
		customerAccount.setCreate_date(new Date(System.currentTimeMillis()));
		customerAccount.setIs_deleted(false);
		customerAccount.setCurr_balance(0);
		
		this.customerAccDao.save(customerAccount);
/*
		try {
			this.genCustomerBo.updateGLBalance(customerAccount);
		} catch (CustomerException e) {
			e.printStackTrace();
		}
		*/
		return customerAccount;
	}
	
	public void delete(CustomerAccount customerAccount){
		this.customerAccDao.delete(customerAccount);
	}
	
	public CustomerAccount update(CustomerAccount cA){
		
		CustomerAccount customerAccount = this.customerAccDao.getLedgerByAccount_no(cA.getAccount_no());
		
		customerAccount.setModified_by(userIdentity.getUser());
		customerAccount.setModify_date(new Date(System.currentTimeMillis()));
		
		this.customerAccDao.update(customerAccount);
		return customerAccount;
	}

	@Override
	public void CustEntry(CustomerEntry customerEntry) {

		this.customerAccDao.CustEntry(customerEntry);
		
	}

	@Override
	public CustomerAccount getBalance(String account_no, Integer branch_id, Integer company_id) {
		// TODO Auto-generated method stub
		return this.customerAccDao.getBalance(account_no, branch_id, company_id);
	}
}
