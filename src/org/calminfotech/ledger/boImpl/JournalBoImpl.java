package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.calminfotech.ledger.boInterface.JournalBo;
import org.calminfotech.ledger.daoInterface.JournalDao;
import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;
import org.calminfotech.ledger.utiility.LedgerException;
import org.calminfotech.ledger.utiility.LedgerUtility;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class JournalBoImpl implements JournalBo{

	@Autowired
	private JournalDao journalDao;
	
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
		return this.journalDao.getJournalEntries();
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
		journalHeader.setJournalID(journalID);
		try {
			this.saveHeader(journalHeader);
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		for (JsonElement jsonElement : entries) {
			JournalEntry journalEntry = new JournalEntry();
			JsonObject jEntryObj = jsonElement.getAsJsonObject();
			journalEntry.setAccountType(jEntryObj.get("account_type").getAsString());
			journalEntry.setAccountNo(jEntryObj.get("account_no").getAsString());
			journalEntry.setAmount(Float.parseFloat(jEntryObj.get("amount").getAsString().replace(",", "")));
			journalEntry.setPostCode(jEntryObj.get("post_code").getAsString());
			journalEntry.setBranchID(Integer.parseInt(jEntryObj.get("branch_id").getAsString()));
			journalEntry.setRefNo(jEntryObj.get("ref_no").getAsString());
			journalEntry.setDescription(jEntryObj.get("desc").getAsString());
			journalEntry.setCreate_date(new Date(System.currentTimeMillis()));
			journalEntry.setCreated_by(this.userIdentity.getUser());
			journalEntry.setJournalID(journalID);
			
			try {
				this.saveJournalEntry(journalEntry);
			} catch (LedgerException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public JournalHeader saveHeader(JournalHeader journalHeader) throws LedgerException {
		User user = this.userIdentity.getUser();
		journalHeader.setCreated_by(user);
		journalHeader.setJournalID(LedgerUtility.getBatchNo());
		journalHeader.setDate(new Date(System.currentTimeMillis()));
		journalHeader.setCreate_date(new Date(System.currentTimeMillis()));
		journalHeader.setOrganisation(user.getOrganisation());
		journalHeader.setOrgCoy(user.getOrganisation().getOrgCoy());
		journalHeader.setTotCredit(0);
		journalHeader.setTotDebit(0);
		journalHeader.setStatus("NOT POSTED");
		
		this.journalDao.saveHeader(journalHeader);
		return journalHeader;
	}

	@Override
	public JournalEntry saveJournalEntry(JournalEntry journalEntry) throws LedgerException {
		this.journalDao.saveJournalEntry(journalEntry);
		return journalEntry;
	}

	@Override
	public List<JournalHeader> fetchJournalHeaders() throws LedgerException {
		return this.journalDao.getJournalHeaders();
	}

	@Override
	public JournalHeader getJournalHeader(String id) throws LedgerException {
		return this.journalDao.getJournalHeader(id);
	}

	@Override
	public List<JournalEntry> getJournalEntriesByJournalID(String id) throws LedgerException {
		return this.journalDao.getJournalEntriesByJournalID(id);
	}

	@Override
	public void manageJournal(Object journal) throws LedgerException {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		
        String json = gson.toJson(journal, LinkedHashMap.class);
		JsonElement jsonTree = parser.parse(json);
		//String journalID = LedgerUtility.getBatchNo();

		JsonArray header = jsonTree.getAsJsonObject().get("journalHeader").getAsJsonArray();
		JsonArray entries = jsonTree.getAsJsonObject().get("journalEntries").getAsJsonArray();

		System.out.println("id: " + header.get(0).getAsString());
		System.out.println("description: " + header.get(1).getAsString());
		
		for (JsonElement jsonElement : entries) {
			System.out.println(jsonElement.getAsJsonObject().get("account_no").getAsString());
		}
		
	}
}
