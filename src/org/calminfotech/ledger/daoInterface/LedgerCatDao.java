package org.calminfotech.ledger.daoInterface;

import java.util.List;

import org.calminfotech.ledger.models.LedgerCategory;

public interface LedgerCatDao {
	public List<LedgerCategory> fetchAll();

	public List<LedgerCategory> fetchParents(int id);

	public LedgerCategory getLedgerById(int id);
	
	public void save(LedgerCategory balSheetCat);
	
	public void delete(LedgerCategory balSheetCat);
	
	public void update(LedgerCategory balSheetCat);
}
