package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.calminfotech.ledger.boInterface.JournalEntryBo;
import org.calminfotech.ledger.daoInterface.JournalEntryDao;
import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;
import org.calminfotech.ledger.utiility.LedgerException;
import org.calminfotech.ledger.utiility.LedgerUtility;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class JournalEntryBoImpl implements JournalEntryBo{

	@Autowired
	private JournalEntryDao journalEntryDao;
	
	@Autowired
	private UserIdentity userIdentity;

	/*@Autowired
	private OrganisationBo organisationBo;

	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private CustomerAccBo cAccBo;
	
	@Autowired
	private SettingBo settingBo;
	
	@Autowired
	private UserBo userBo;

	@Autowired
	private TotAccDao totAccDao;*/


	@Override
	public List<JournalEntry> getJournalEntries() throws LedgerException {
		System.out.println(this.userIdentity.getUser().getUserId() + " get journal BO");	
		return this.journalEntryDao.getJournalEntries();
	}
	
	@Override
	public void journalEntry(Object journal) {
		System.out.println(this.userIdentity.getUser().getUserId() + " post journal BO");	
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		
        String json = gson.toJson(journal, LinkedHashMap.class);
		JsonElement jsonTree = parser.parse(json);
		String journalID = LedgerUtility.getBatchNo();

		String description = jsonTree.getAsJsonObject().get("journalDesc").getAsString();
		JsonArray entries = jsonTree.getAsJsonObject().get("journalEntries").getAsJsonArray();
		//  journal header as date, journal id, description
		
		JournalHeader journalHeader = new JournalHeader();
		journalHeader.setDescription(description);
		journalHeader.setDate(new Date(System.currentTimeMillis()));
		journalHeader.setJournal_id(journalID);
		try {
			this.saveHeader(journalHeader);
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		for (JsonElement jsonElement : entries) {
			JournalEntry journalEntry = new JournalEntry();
			JsonObject jEntryObj = jsonElement.getAsJsonObject();
			journalEntry.setAccount_type(jEntryObj.get("account_type").getAsString());
			journalEntry.setAccount_no(jEntryObj.get("account_no").getAsString());
			journalEntry.setAmount(Float.parseFloat(jEntryObj.get("amount").getAsString().replace(",", "")));
			journalEntry.setPost_code(jEntryObj.get("post_code").getAsString());
			journalEntry.setBranch_id(Integer.parseInt(jEntryObj.get("branch_id").getAsString()));
			journalEntry.setRef_no(jEntryObj.get("ref_no").getAsString());
			journalEntry.setDescription(jEntryObj.get("desc").getAsString());
			journalEntry.setCreate_date(new Date(System.currentTimeMillis()));
			journalEntry.setCreated_by(this.userIdentity.getUser());
			journalEntry.setJournal_id(journalID);
			
			try {
				this.saveJournalEntry(journalEntry);
			} catch (LedgerException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public JournalHeader saveHeader(JournalHeader journalHeader) throws LedgerException {
		this.journalEntryDao.saveHeader(journalHeader);
		return journalHeader;
	}

	@Override
	public JournalEntry saveJournalEntry(JournalEntry journalEntry) throws LedgerException {
		this.journalEntryDao.saveJournalEntry(journalEntry);
		return journalEntry;
	}

	@Override
	public List<JournalHeader> getJournalHeaders() throws LedgerException {
		return this.journalEntryDao.getJournalHeaders();
	}
}
