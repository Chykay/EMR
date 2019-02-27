package org.calminfotech.ledger.boInterface;

import java.util.List;

import org.calminfotech.ledger.forms.GenLedgerForm;
import org.calminfotech.ledger.models.GeneralLedger;


public interface GenLedgerBo {
	public List<GeneralLedger> fetchAll();
	
	public GeneralLedger getLedgerById(int id);
	
	public GeneralLedger save(GenLedgerForm genLedgerForm);
	
	public void delete(GeneralLedger genLedgerAccount);
	
	public GeneralLedger update(GenLedgerForm genLedgerForm, int id);
}
