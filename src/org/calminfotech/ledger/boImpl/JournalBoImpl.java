package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.calminfotech.ledger.boInterface.CustomerAccBo;
import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.JournalBo;
import org.calminfotech.ledger.daoInterface.JournalDao;
import org.calminfotech.ledger.models.CustomerAccount;
import org.calminfotech.ledger.models.CustomerEntry;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;
import org.calminfotech.ledger.utiility.LedgerException;
import org.calminfotech.ledger.utiility.LedgerUtility;
import org.calminfotech.system.boInterface.SettingBo;
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
	
	@Autowired
	private GenLedgerBo genLedgerBo;
	
	@Autowired
	private CustomerAccBo cAccBo;

	@Autowired
	private SettingBo settingBo;
	/*@Autowired
	private OrganisationBo organisationBo;

	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	
	@Autowired
	private this.useridentity.getUser()Bo this.useridentity.getUser()Bo;

	@Autowired
	private TotAccDao totAccDao;*/


	@Override
	public List<JournalEntry> getJournalEntries() throws LedgerException {
		return this.journalDao.getJournalEntries();
	}
	
	/*@Override
	public void journalEntry(Object journal) {
		System.out.println(this.useridentity.getUser().getthis.useridentity.getUser()Id() + " post journal BO");	
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
		
		
	}*/

	@Override
	public JournalHeader saveHeader(JournalHeader journalHeader) throws LedgerException {
		journalHeader.setCreated_by(this.userIdentity.getUser());
		journalHeader.setJournalID(LedgerUtility.getBatchNo());
		journalHeader.setDate(new Date(System.currentTimeMillis()));
		journalHeader.setCreate_date(new Date(System.currentTimeMillis()));
		journalHeader.setOrganisation(this.userIdentity.getUser().getOrganisation());
		journalHeader.setOrgCoy(this.userIdentity.getUser().getOrganisation().getOrgCoy());
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
		return this.journalDao.fetchJournalHeaders();
	}

	@Override
	public JournalHeader getJournalHeader(String journalID) throws LedgerException {
		return this.journalDao.getJournalHeader(journalID);
	}

	@Override
	public List<JournalEntry> getJournalEntriesByJournalID(String journalID) throws LedgerException {
		return this.journalDao.getJournalEntriesByJournalID(journalID);
	}

	@Override
	public void manageJournal(Object journal) throws LedgerException {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		String journalID, description;
		
        String json = gson.toJson(journal, LinkedHashMap.class);
		JsonElement jsonTree = parser.parse(json);

		JsonObject header = jsonTree.getAsJsonObject().get("journalHeader").getAsJsonObject();
		JsonArray entries = jsonTree.getAsJsonObject().get("journalEntries").getAsJsonArray();
		
		journalID = header.get("journalID").getAsString();
		description = header.get("description").getAsString();
		System.out.println(description + " description");
		
		JournalHeader journalHeader = this.getJournalHeader(journalID);
		journalHeader.setDescription(description);
		this.updateHeader(journalHeader);
		
		this.removeEntries(journalID);
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
			journalEntry.setOrganisation(this.userIdentity.getUser().getOrganisation());
			journalEntry.setOrgCoy(this.userIdentity.getUser().getOrganisation().getOrgCoy());
			
			try {
				this.saveJournalEntry(journalEntry);
			} catch (LedgerException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void updateHeader(JournalHeader journalHeader) {
		this.journalDao.updateHeader(journalHeader);
	}

	public void removeEntries(String journalID) {
		this.journalDao.removeEntries(journalID);
	}

	@Override
	public void postJournal(String journalID) throws LedgerException {
		JournalHeader journalHeader = this.getJournalHeader(journalID);
		journalHeader.setStatus("POSTED");
		this.updateHeader(journalHeader);
		
		List<JournalEntry> jEntries = this.getJournalEntriesByJournalID(journalID);
		String batch_no = LedgerUtility.getBatchNo();

		GLEntry gLEntry = new GLEntry();
		for (JournalEntry journalEntry : jEntries) {
			gLEntry.setCreate_date(new Date(System.currentTimeMillis()));
			gLEntry.setAccountNo(journalEntry.getAccountNo());
			gLEntry.setOrganisation(this.userIdentity.getUser().getOrganisation());
			gLEntry.setOrgCoy(this.userIdentity.getUser().getOrganisation().getOrgCoy());
			gLEntry.setRefNo1(journalEntry.getRefNo());
			gLEntry.setPostCode(journalEntry.getPostCode());
			gLEntry.setAmount(journalEntry.getAmount());
			gLEntry.setDescription(journalEntry.getDescription());
			gLEntry.setCreated_by(this.userIdentity.getUser());
			gLEntry.setBatchNo(batch_no);
			gLEntry.setPostingDate(new Date(System.currentTimeMillis()));
			gLEntry.setRef_no3(journalID);
			gLEntry.setBranch(journalEntry.getBranchID());
			
			if (journalEntry.getAccountType().contains("CA")) {
				CustomerEntry customerEntry= new CustomerEntry();
				customerEntry.setAccountNo(journalEntry.getAccountNo());
				customerEntry.setAmount(journalEntry.getAmount());
				customerEntry.setBatchNo(batch_no);
				customerEntry.setCreate_date(new Date(System.currentTimeMillis()));
				customerEntry.setOrganisation(journalEntry.getOrganisation());
				customerEntry.setOrgCoy(journalEntry.getOrganisation().getOrgCoy());
				customerEntry.setPostCode(journalEntry.getPostCode());
				customerEntry.setDescription(journalEntry.getDescription());
				customerEntry.setCreated_by(userIdentity.getUser());
				customerEntry.setPostingDate(new Date(System.currentTimeMillis()));
				customerEntry.setRefNo2(journalID);
				
				this.cAccBo.CustEntry(customerEntry);

				String customerGl = this.settingBo.fetchsettings("customer-GLP", 2).getSettings_value();
				float amount;
				if (journalEntry.getPostCode().contains("DR")) {
					amount = journalEntry.getAmount() * -1;
				} else {
					amount = journalEntry.getAmount();
				}
				CustomerAccount customerAccount = this.cAccBo.getCustomerByAccount_no(journalEntry.getAccountNo());
				customerAccount.setCurrBalance(customerAccount.getCurr_balance() + amount);
				
				this.cAccBo.update(customerAccount);
				
				gLEntry.setBranch(customerAccount.getOrganisation().getId());
				gLEntry.setProductID(journalEntry.getAccountNo());
				gLEntry.setAccountNo(customerGl);
			} 
			this.genLedgerBo.GLEntry(gLEntry);
		}
	}
}
