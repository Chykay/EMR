package org.calminfotech.ledger.daoInterface;

import java.util.List;

import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;

public interface JournalEntryDao {
	
	public List<JournalEntry> getJournalEntries();
	
	public void saveHeader(JournalHeader journalHeader);

	public void saveJournalEntry(JournalEntry journalEntry);

	public List<JournalHeader> getJournalHeaders();

}
