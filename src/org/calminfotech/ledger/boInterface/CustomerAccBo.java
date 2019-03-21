package org.calminfotech.ledger.boInterface;

import java.util.List;

import org.calminfotech.ledger.models.CustomerAccount;
import org.calminfotech.ledger.models.CustomerEntry;


public interface CustomerAccBo {
	public List<CustomerAccount> fetchAll(int branch_id, int company_id);
	
	public CustomerAccount getCustomerById(int id);
	
	public CustomerAccount getCustomerByAccount_no(String account_no);
	
	public CustomerAccount save(CustomerAccount customerAccount);
	
	public void delete(CustomerAccount genCustomerAccount);
	
	public CustomerAccount update(CustomerAccount customerAccount);

	public void CustEntry(CustomerEntry customerEntry);

	public CustomerAccount getBalance(String account_no, Integer branch_id, Integer company_id);
	
}
