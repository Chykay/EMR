package org.calminfotech.ledger.boInterface;

import java.util.List;

import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.LedgerAccount;


public interface LedgerAccBo {
	public List<LedgerAccount> fetchAll(int branch_id, int company_id);
	
	public LedgerAccount getLedgerById(int id);
	
	public LedgerAccount getLedgerByAccount_no(String account_no);
	
	public LedgerAccount save(LedgerAccForm ledgerAccForm);
	
	public void delete(LedgerAccount genLedgerAccount);
	
	public LedgerAccount update(LedgerAccForm ledgerAccForm, int id);

	public List<LedgerAccount> fetchTop100(int branch_id, int company_id);

	public List<LedgerAccount> getAssetLedgers();
	
}
