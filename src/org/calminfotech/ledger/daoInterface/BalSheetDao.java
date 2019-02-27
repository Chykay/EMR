package org.calminfotech.ledger.daoInterface;

import java.util.List;

import org.calminfotech.ledger.models.BalSheetCat;

public interface BalSheetDao {
	public List<BalSheetCat> fetchAll();
	
	public BalSheetCat getLedgerById(int id);
	
	public void save(BalSheetCat balSheetCat);
	
	public void delete(BalSheetCat balSheetCat);
	
	public void update(BalSheetCat balSheetCat);
}
