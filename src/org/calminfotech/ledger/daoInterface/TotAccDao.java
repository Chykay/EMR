package org.calminfotech.ledger.daoInterface;

import java.util.List;

import org.calminfotech.ledger.models.TotalingAccount;

public interface TotAccDao {

	public List<TotalingAccount> fetchAll();
	
	public TotalingAccount getLedgerById(int id);
	
	public void save(TotalingAccount totalingAccount);
	
	public void delete(TotalingAccount totalingAccount);
	
	public void update(TotalingAccount totalingAccount);
}
