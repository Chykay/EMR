package org.calminfotech.ledger.daoInterface;

import java.util.List;

import org.calminfotech.ledger.models.GeneralLedger;

public interface GenLedgerDao {

	public List<GeneralLedger> fetchAll();
	
	public GeneralLedger getLedgerById(int id);
	
	public void save(GeneralLedger generalLedger);
	
	public void delete(GeneralLedger generalLedger);
	
	public void update(GeneralLedger generalLedger);
}
