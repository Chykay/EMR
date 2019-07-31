package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.calminfotech.billing.boInterface.CustomerTransactionBo;
import org.calminfotech.billing.boInterface.HmoTransactionBo;
import org.calminfotech.billing.boInterface.VendorTransactionBo;
import org.calminfotech.billing.models.CustomerTransaction;
import org.calminfotech.billing.models.VendorTransaction;
import org.calminfotech.hmo.boInterface.HmoBo;
import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.hmo.models.HmoTransaction;
import org.calminfotech.inventory.exceptions.RecordNotFoundException;
import org.calminfotech.inventory.models.Vendor;
import org.calminfotech.inventory.serviceInterface.VendorManagerInterface;
import org.calminfotech.ledger.boInterface.JournalBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.LedgerPostingBo;
import org.calminfotech.ledger.daoInterface.JournalDao;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;
import org.calminfotech.ledger.utility.LedgerException;
import org.calminfotech.ledger.utility.LedgerUtility;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.AutoGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private LedgerPostingBo ledgerPostingBo;

	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private PatientBo patientBo;
	
	@Autowired
	private CustomerTransactionBo customerTransactionBo;

	@Autowired
	private HmoBo hmoBo;
	
	@Autowired
	private HmoTransactionBo hmoTranBo;

	@Autowired
	private VendorTransactionBo vendorTranBo;

	@Autowired
	private VendorManagerInterface vendorBo;
	
	@Autowired
	private SettingBo settingBo;

	@Autowired
	private LedgerAccBo ledgerAccBo;
	/*@Autowired
	private CustomerAccBo cAccBo;

	@Autowired
	private PatientPaymentOptionBo patientPaymentOptionBo;
	
	@Autowired
	private SettingBo settingBo;

	@Autowired
	private Alert alert;
	
	
	
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
		String journalID, description, action;
		float totDebit = 0, totCredit = 0;
		
        String json = gson.toJson(journal, LinkedHashMap.class);
		JsonElement jsonTree = parser.parse(json);

		JsonObject header = jsonTree.getAsJsonObject().get("journalHeader").getAsJsonObject();
		JsonArray entries = jsonTree.getAsJsonObject().get("journalEntries").getAsJsonArray();
		
		journalID = header.get("journalID").getAsString();
		description = header.get("description").getAsString();
		action = header.get("action").getAsString();
		
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
			journalEntry.setBranch(this.organisationBo.getOrganisationById(Integer.parseInt(jEntryObj.get("branch_id").getAsString())));
			journalEntry.setRefNo(jEntryObj.get("ref_no").getAsString());
			journalEntry.setDescription(jEntryObj.get("desc").getAsString());
			journalEntry.setCreate_date(new Date(System.currentTimeMillis()));
			journalEntry.setCreated_by(this.userIdentity.getUser());
			journalEntry.setJournalID(journalID);
			journalEntry.setOrganisation(this.userIdentity.getUser().getOrganisation());
			journalEntry.setOrgCoy(this.userIdentity.getUser().getOrganisation().getOrgCoy());
			
			if (journalEntry.getPostCode().equals("DR")) {
				totDebit += journalEntry.getAmount();
			} else {
				totCredit += journalEntry.getAmount();
			}
			try {
				this.saveJournalEntry(journalEntry);
			} catch (LedgerException e) {
				e.printStackTrace();
			}
		}
		
		if (action.contains("post")) {
			if (Math.abs(totCredit) - Math.abs(totDebit) == 0) {
				Boolean interfaceAccountCheck = this.ledgerAccBo.productInterfaceAccCheck();
				
				if(interfaceAccountCheck){
					try {
						this.postJournal(journalID);
					} catch (LedgerException e) {
						System.out.println(e.getExceptionMsg());
					}
				} else {
					// set in alert "oga do your interface"
				}
			} else {
				throw new LedgerException("Total credit and total debit are not equal");
			}
		}
		
		//return true;
		
	}

	public void updateHeader(JournalHeader journalHeader) {
		this.journalDao.updateHeader(journalHeader);
	}

	public void removeEntries(String journalID) {
		this.journalDao.removeEntries(journalID);
	}

	@Transactional
	public void postJournal(String journalID) throws LedgerException {
		Organisation org = this.userIdentity.getOrganisation();
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
			gLEntry.setBranch(journalEntry.getBranch());
			
			if (journalEntry.getAccountType().equals("CA")) {
				CustomerTransaction custtran = new CustomerTransaction();
				
				Double crl = 0.00;
				Double gcrl = 0.00;
				// Double pcrl = 0.00;

				SettingsAssignment setass1 = this.settingBo.fetchsettings(
						"CREDIT_LIMIT", userIdentity.getOrganisation().getId());

				if (setass1 != null) {
					try {
						gcrl = Double.parseDouble(setass1.getSettings_value());
					} catch (Exception e)

					{
						gcrl = 0.00;

					}
				}

				try {
					Double vt = this.patientBo.getPatientById(Integer.parseInt(journalEntry.getAccountNo()))
							.getCreditlimit().doubleValue();
					if (vt != 0.00) {
						crl = vt;
					} else {
						crl = gcrl;
					}
				} catch (Exception e) {
					crl = gcrl;
				}
				custtran.setEffectivedate(new Date(System.currentTimeMillis()));
				custtran.setDescription(journalEntry.getDescription());
				custtran.setPatient(this.patientBo.getPatientById(Integer.parseInt(journalEntry.getAccountNo())));
				custtran.setDrcr(journalEntry.getPostCode());
				custtran.setCode(new AutoGenerate().mygen());

				if (journalEntry.getPostCode().equalsIgnoreCase("dr")) {
					
					if (((Double) custtran.getPatient().getMfig()
							.get("totcustavailablebal") + journalEntry.getAmount()) > crl) {
						throw new LedgerException("payment limit exceeded");
					
						//return "redirect:/transaction/customer";

					}

					custtran.setAmount((double) journalEntry.getAmount());
					custtran.setTrantype("withdrawal");

				} else {
					custtran.setAmount((double) -journalEntry.getAmount());
					custtran.setTrantype("deposit");

				}

				custtran.setCreatedBy(userIdentity.getUsername());
				custtran.setUser(userIdentity.getUser());
				custtran.setCreatedDate(new Date(System.currentTimeMillis()));
				custtran.setOrganisation(userIdentity.getOrganisation());
				custtran.setPaymode(null);
				custtran.setDeleted(false);

				this.customerTransactionBo.save(custtran);
				
				String customerGl = this.settingBo.fetchsettings("PATIENT_REC_ACT", org.getOrgCoy().getId()).getSettings_value();
				Patient patient = this.patientBo.getPatientById(Integer.parseInt(journalEntry.getAccountNo()));
				
				gLEntry.setBranch(patient.getOrganisation());
				gLEntry.setAccountNo(customerGl);
			} else if (journalEntry.getAccountType().equals("VA")){
				System.out.println("Vendor");
				VendorTransaction custtran = new VendorTransaction();

				custtran.setEffectivedate(journalEntry.getCreate_date());
				custtran.setDescription(journalEntry.getDescription());
				try {
					custtran.setVendor(this.vendorBo.getVendorDetailsById(Integer.parseInt(journalEntry
							.getAccountNo())));
				} catch (RecordNotFoundException e) {

					/*alert.setAlert(redirectAttributes, Alert.DANGER,
							"Error saving Customer Information!");
				
					return "redirect:/transaction/vendor";*/
				}
				custtran.setDrcr(journalEntry.getPostCode());

				if (journalEntry.getPostCode().equalsIgnoreCase("dr")) {
					custtran.setAmount((double) -journalEntry.getAmount());
					custtran.setTrantype("Payment");
				} else {
					custtran.setAmount((double) journalEntry.getAmount());
					custtran.setTrantype("Supply");
				}

				// custtran.setTranrefno()
				custtran.setCode(new AutoGenerate().mygen());

				custtran.setCreatedBy(userIdentity.getUsername());
				custtran.setUser(userIdentity.getUser());
				custtran.setCreatedDate(new Date(System.currentTimeMillis()));
				// custtran.setCreatedDate(new GregorianCalendar().getTime());
				custtran.setOrganisation(userIdentity.getOrganisation());

				//custtran.setPaymode(this.pa.getPaymodeTypeById(journalEntry
				//		.getPaymode_id()));
				custtran.setDeleted(false);

				this.vendorTranBo.save(custtran);
				
				String vendorGL = this.settingBo.fetchsettings("VENDOR_PAY_ACT", org.getOrgCoy().getId()).getSettings_value();
				Vendor vendor = new Vendor();
				
				try {
					vendor = this.vendorBo.getVendorDetailsById(Integer.parseInt(journalEntry.getAccountNo()));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RecordNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				gLEntry.setBranch(vendor.getOrganisation());
				gLEntry.setAccountNo(vendorGL);
			} else if (journalEntry.getAccountType().equals("HA")){
				System.out.println("HMO");

				HmoTransaction hmotran = new HmoTransaction();
				Double crl = 0.00;
				Double gcrl = 0.00;
				Double pcrl = 0.00;
				
				hmotran.setEffectivedate(new Date(System.currentTimeMillis()));
				hmotran.setDescription(journalEntry.getDescription());
				hmotran.setHmo(this.hmoBo.getHmoById(Integer.parseInt(journalEntry.getAccountNo())));
				hmotran.setDrcr(journalEntry.getPostCode());
				hmotran.setTranrefno(new AutoGenerate().mygen());

				if (journalEntry.getPostCode().equalsIgnoreCase("Dr")) {

					System.out.print ("DEBIT OOOO");
					System.out.print (journalEntry.getAmount());
					System.out.print ("DEBIT OOOO");
					
					hmotran.setAmount((double) journalEntry.getAmount());
					hmotran.setTrantype("claims");

				} else {

					System.out.print ("CREDIT  OOOO");
					System.out.print (-journalEntry.getAmount());
					System.out.print ("CREDIT  OOOO");
					hmotran.setAmount((double) -(journalEntry.getAmount()));

					hmotran.setTrantype("claimspaid");
				}


				hmotran.setCreatedBy(userIdentity.getUsername());
				hmotran.setUser(userIdentity.getUser());
				hmotran.setCreatedDate(new Date(System.currentTimeMillis()));
				hmotran.setOrganisation(userIdentity.getOrganisation());
				//hmotran.setPaymode(this.paymodeBo.getPaymodeTypeById(journalEntry
				//		.getPaymode_id()));
				hmotran.setDeleted(false);

				this.hmoTranBo.save(hmotran);
				String HMOGL = this.settingBo.fetchsettings("HMO_REC_ACT", org.getOrgCoy().getId()).getSettings_value();
				
				Hmo hmo = this.hmoBo.getHmoById(Integer.parseInt(journalEntry.getAccountNo()));
				
				gLEntry.setBranch(hmo.getOrganisation());
				gLEntry.setAccountNo(HMOGL);
			}
			/*if (journalEntry.getAccountType().contains("CA")) {
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
				gLEntry.setAccountNo(customerGl);
			} */
			
			try {
				this.ledgerPostingBo.GLEntry(gLEntry);
			} catch (LedgerException e) {
				System.out.println("Here here olaKolade" + e.getExceptionMsg());	
				throw new LedgerException("abraham");
			}
		}
	}
}
