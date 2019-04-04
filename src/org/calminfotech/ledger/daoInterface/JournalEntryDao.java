package org.calminfotech.ledger.daoInterface;

import java.util.List;

import org.calminfotech.ledger.models.CustomerEntry;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.JournalEntry;

public interface JournalEntryDao {
	public GenLedgBalance getBalance(String account_no, int branch_id, int company_id);
	
	public void updateGLBalance(GenLedgBalance genLedgBalance);
	
	public void GLEntry(GLEntry glEntry);

	public GenLedgBalance saveGLBalance(GenLedgBalance genLedgBalance);
	
	public boolean getLedgerStat(String account_no, int branch_id, int company_id);
	
	public List<GLEntry> getGLEntries();

	public List<GLEntry> getGLEntriesByBatch_no(String batch_no);

	public List<CustomerEntry> getCustEntries();

	public List<CustomerEntry> getCustEntriesByBatch_no(String batch_no);

	public List<JournalEntry> getJournalEntries();

	public List<GLEntry> getGLEntriesListing(String account_no, String start_date, String end_date);

	public List<CustomerEntry> getCustEntriesListing(String account_no, String start_date, String end_date);
}
