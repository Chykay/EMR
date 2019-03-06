package org.calminfotech.ledger.boInterface;

import org.calminfotech.ledger.forms.GLPostingForm;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.utiility.LedgerException;

public interface GenLedgerBo {
	
	public void GLPosting(GLPostingForm glPostingForm) throws LedgerException;
	
	public GenLedgBalance getBalance(String account_no, int branch_id, int company_id) throws LedgerException;
	
	public void GLEntry(GLEntry glEntry) throws LedgerException;
	
	public void updateGLBalance(LedgerAccount ledgerAccount) throws LedgerException;
	
}
