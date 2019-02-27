package org.calminfotech.ledger.boInterface;

import java.util.List;

import org.calminfotech.ledger.forms.BalSheetForm;
import org.calminfotech.ledger.models.BalSheetCat;


public interface BalSheetCatBo {
	public List<BalSheetCat> fetchAll();
	
	public BalSheetCat getLedgerById(int id);
	
	public BalSheetCat save(BalSheetForm balSheetForm);
	
	public void delete(BalSheetCat totalingAccount);
	
	public BalSheetCat update(BalSheetForm balSheetForm, int id);
}
