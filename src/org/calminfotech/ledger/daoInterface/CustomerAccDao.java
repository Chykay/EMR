package org.calminfotech.ledger.daoInterface;

import java.util.List;

import org.calminfotech.ledger.models.CustomerAccount;
import org.calminfotech.ledger.models.CustomerEntry;

public interface CustomerAccDao {

	public List<CustomerAccount> fetchAll(int branch_id, int company_id);

	public CustomerAccount getLedgerById(int id);
	
	public CustomerAccount getLedgerByAccount_no(String account_no);
	
	public CustomerAccount save(CustomerAccount customerAccount);
	
	public void delete(CustomerAccount customerAccount);
	
	public void update(CustomerAccount customerAccount);

	public void CustEntry(CustomerEntry customerEntry);

	public CustomerAccount getBalance(String account_no, Integer branch_id, Integer company_id);
}
