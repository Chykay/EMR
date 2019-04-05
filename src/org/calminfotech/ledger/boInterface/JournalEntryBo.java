package org.calminfotech.ledger.boInterface;

import java.util.List;

import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;
import org.calminfotech.ledger.utiility.LedgerException;

public interface JournalEntryBo {
	
	public List<JournalEntry> getJournalEntries() throws LedgerException;
	
	public JournalHeader saveHeader(JournalHeader journalHeader) throws LedgerException;

	public JournalEntry saveJournalEntry(JournalEntry journalEntry) throws LedgerException;

	public List<JournalHeader> getJournalHeaders() throws LedgerException;

	public void journalEntry(Object journal);
}
