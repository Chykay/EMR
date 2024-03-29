package org.calminfotech.ledger.boInterface;

import java.util.List;

import org.calminfotech.ledger.forms.LedgerCatForm;
import org.calminfotech.ledger.models.LedgerCategory;


public interface LedgerCatBo {
	public List<LedgerCategory> fetchAll();

	public List<LedgerCategory> fetchParents(int id);

	public LedgerCategory getLedgerById(int id);

	public List<LedgerCategory> getCatsWithoutLedgerChildren();

	public List<LedgerCategory> getCatsWithoutCatsChildren();
	
	public LedgerCategory save(LedgerCatForm ledgerCatForm);
	
	public void delete(LedgerCategory ledgerCategory);
	
	public LedgerCategory update(LedgerCatForm ledgerCatForm, int id);

}
