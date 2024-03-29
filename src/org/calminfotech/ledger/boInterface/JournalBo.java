package org.calminfotech.ledger.boInterface;

import java.util.List;

import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;
import org.calminfotech.ledger.utility.LedgerException;

public interface JournalBo {
	
	public List<JournalEntry> getJournalEntries() throws LedgerException;

	public JournalHeader saveHeader(JournalHeader journalHeader) throws LedgerException;
	
	public void updateHeader(JournalHeader journalHeader) throws LedgerException;

	public JournalEntry saveJournalEntry(JournalEntry journalEntry) throws LedgerException;

	public List<JournalHeader> fetchJournalHeaders() throws LedgerException;

	/*public void journalEntry(Object journal);*/

	public JournalHeader getJournalHeader(String journalID) throws LedgerException;

	public List<JournalEntry> getJournalEntriesByJournalID(String journalID) throws LedgerException;

	public void manageJournal(Object journal) throws LedgerException;
	
	public void removeEntries(String journalID) throws LedgerException;

	public void postJournal(String journalID) throws LedgerException;
}
