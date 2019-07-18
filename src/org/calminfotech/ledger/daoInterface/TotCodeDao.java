package org.calminfotech.ledger.daoInterface;

import java.util.List;

import org.calminfotech.ledger.models.TotalingCode;

public interface TotCodeDao {

	public List<TotalingCode> fetchAll();

	public List<TotalingCode> fetchAllActive();
	
	public TotalingCode getLedgerById(int id);
	
	public void save(TotalingCode totalingCode);
	
	public void delete(TotalingCode totalingCode);
	
	public void update(TotalingCode totalingCode);
}
