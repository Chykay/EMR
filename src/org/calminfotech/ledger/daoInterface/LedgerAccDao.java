package org.calminfotech.ledger.daoInterface;

import java.util.List;

import org.calminfotech.ledger.models.LedgerAccount;

public interface LedgerAccDao {

	public List<LedgerAccount> fetchAll(int branch_id, int company_id);

	public List<LedgerAccount> fetchTop100(int branch_id, int company_id);

	public LedgerAccount getLedgerById(int id);
	
	public LedgerAccount getLedgerByAccount_no(String account_no);
	
	public void save(LedgerAccount ledgerAccount);
	
	public void delete(LedgerAccount ledgerAccount);
	
	public void update(LedgerAccount ledgerAccount);

	public List<LedgerAccount> getAssetLedgers();
}
