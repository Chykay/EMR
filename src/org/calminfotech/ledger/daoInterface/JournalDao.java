package org.calminfotech.ledger.daoInterface;

import java.util.List;

import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;

public interface JournalDao {
	
	public List<JournalEntry> getJournalEntries();
	
	public void saveHeader(JournalHeader journalHeader);

	public void saveJournalEntry(JournalEntry journalEntry);

	public List<JournalHeader> fetchJournalHeaders();

	public JournalHeader getJournalHeader(String journalID);

	public List<JournalEntry> getJournalEntriesByJournalID(String journalID);

	public void removeEntries(String journalID);

	void updateHeader(JournalHeader journalHeader);

}
