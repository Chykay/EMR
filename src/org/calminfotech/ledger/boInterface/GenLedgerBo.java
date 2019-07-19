package org.calminfotech.ledger.boInterface;

import java.util.List;

import org.calminfotech.ledger.forms.GLPostingForm;
import org.calminfotech.ledger.models.CustomerEntry;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.utility.LedgerException;

public interface GenLedgerBo {
	
	public void GLPosting(GLPostingForm glPostingForm) throws LedgerException;
	
	public GenLedgBalance getBalance(String account_no, int branch_id, int company_id) throws LedgerException;
	
	public void GLEntry(GLEntry glEntry) throws LedgerException;
	
	public void updateGLBalance(LedgerAccount ledgerAccount, int branch_id) throws LedgerException;
	
	public boolean getLedgerStat(String account_no, int branch_id, int company_id) throws LedgerException;

	public List<GLEntry> getGLEntries(int org_id) throws LedgerException;

	public List<GLEntry> getGLEntriesByBatch_no(String batch_no) throws LedgerException;
	
	public void GLReversal(String batch_no) throws LedgerException;

	public List<CustomerEntry> getCustEntries() throws LedgerException;

	public void CustReversal(String batch_no) throws LedgerException;

	public List<JournalEntry> getJournalEntries() throws LedgerException;
/*
	public List<GLEntry> getGLEntriesByAccount_no(String account_no) throws LedgerException;*/

	public List<GLEntry> getGLEntriesListing(String account_no, String start_date, String end_date) throws LedgerException;

	public List<CustomerEntry> getCustEntriesListing(String account_no, String start_date, String end_date) throws LedgerException;

	public List<GLEntry> getEntriesForGL(Integer org_id, String accountNo) throws LedgerException;

	public List<GLEntry> getEntriesForGLCompany(int company_id, String accountNo)throws LedgerException;

	List<GLEntry> getGLEntriesListingCom(String account_no, String start_date, String end_date) throws LedgerException;
}
