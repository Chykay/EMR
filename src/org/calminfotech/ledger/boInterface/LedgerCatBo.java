package org.calminfotech.ledger.boInterface;

import java.util.ArrayList;
import java.util.List;

import org.calminfotech.ledger.forms.LedgerCatForm;
import org.calminfotech.ledger.models.LedgerCategory;


public interface LedgerCatBo {
	public List<LedgerCategory> fetchAll();

	public List<LedgerCategory> fetchAllByOrg(int orgID);

	public List<LedgerCategory> fetchParents(int id);
	
	public LedgerCategory getLedgerById(int id);
	
	public LedgerCategory save(LedgerCatForm balSheetForm);
	
	public void delete(LedgerCategory totalingAccount);
	
	public LedgerCategory update(LedgerCatForm balSheetForm, int id);

	List<LedgerCategory> fetchByLedgType(ArrayList<Integer> ledgerTypes);
}
