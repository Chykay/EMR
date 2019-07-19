package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.boInterface.CustomerAccBo;
import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.daoInterface.GenLedgerDao;
import org.calminfotech.ledger.forms.GLPostingForm;
import org.calminfotech.ledger.models.CustomerEntry;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.utility.LedgerException;
import org.calminfotech.ledger.utility.LedgerUtility;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenLedgerBoImpl implements GenLedgerBo{

	@Autowired
	private GenLedgerDao genLedgerDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private OrganisationBo organisationBo;

	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private CustomerAccBo cAccBo;
	
	@Autowired
	private SettingBo settingBo;
	
	/*@Autowired
	private UserBo userBo;*/
	
	/*
	@Autowired
	private TotAccDao totAccDao;*/
	
	@Override
	public GenLedgBalance getBalance(String account_no, int branch_id, int company_id)  throws LedgerException{
		return this.genLedgerDao.getBalance(account_no, branch_id, company_id);
	}
	
	@Override
	public void GLPosting(GLPostingForm glPostingForm) throws LedgerException {
		GLEntry gLEntry1 = new GLEntry();
		GLEntry gLEntry2 = new GLEntry();
		
		String batch_no = LedgerUtility.getBatchNo();
		/*String account1 = glPostingForm.getPAccountType();
		String account2 = glPostingForm.getRAccountType();*/
		
		
		gLEntry1.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry1.setAccountNo(glPostingForm.getPAccountNo());
		gLEntry1.setOrganisation(this.userIdentity.getOrganisation());
		gLEntry1.setOrgCoy(this.userIdentity.getOrganisation().getOrgCoy());
		gLEntry1.setRefNo1(glPostingForm.getRefNo1());
		gLEntry1.setPostCode(glPostingForm.getPPostCode());
		gLEntry1.setAmount(Float.parseFloat(glPostingForm.getAmount().replace(",", "")));
		gLEntry1.setDescription(glPostingForm.getPDescription());
		gLEntry1.setCreated_by(userIdentity.getUser());
		gLEntry1.setBatchNo(batch_no);
		System.out.println(glPostingForm.getPostingDate());
		gLEntry1.setPostingDate(DateUtils.formatStringToDate(glPostingForm.getPostingDate()));
		
		
		
		gLEntry2.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry2.setAccountNo(glPostingForm.getRAccountNo());
		System.out.println(gLEntry2.getAccountNo());
		gLEntry2.setOrganisation(this.userIdentity.getOrganisation());
		gLEntry2.setOrgCoy(this.userIdentity.getOrganisation().getOrgCoy());
		gLEntry2.setRefNo1(glPostingForm.getRefNo1());
		gLEntry2.setPostCode(glPostingForm.getRPostCode());
		gLEntry2.setAmount(Float.parseFloat(glPostingForm.getAmount().replace(",", "")));
		gLEntry2.setDescription(glPostingForm.getRDescription());
		gLEntry2.setCreated_by(userIdentity.getUser());
		gLEntry2.setBatchNo(batch_no);
		gLEntry2.setPostingDate(DateUtils.formatStringToDate(glPostingForm.getPostingDate()));
		
		
		/*if (account1.equals("CA")) {
			CustomerEntry customerEntry= new CustomerEntry();
			customerEntry.setAccountNo(glPostingForm.getPAccountNo());
			customerEntry.setAmount(Float.parseFloat(glPostingForm.getAmount().replace(",", "")));
			customerEntry.setBatchNo(batch_no);
			customerEntry.setCreate_date(new Date(System.currentTimeMillis()));
			customerEntry.setOrganisation(this.userIdentity.getOrganisation());
			customerEntry.setOrgCoy(this.userIdentity.getOrganisation().getOrgCoy());
			customerEntry.setPostCode(glPostingForm.getPPostCode());
			customerEntry.setDescription(glPostingForm.getPDescription());
			customerEntry.setCreated_by(userIdentity.getUser());
			customerEntry.setPostingDate(DateUtils.formatStringToDate(glPostingForm.getPostingDate()));
			
			this.cAccBo.CustEntry(customerEntry);

			String customerGl = this.settingBo.fetchsettings("customer-GLP", 2).getSettings_value();
			float amount;
			if (glPostingForm.getPPostCode().equals("DR")) {
				amount = Float.parseFloat(glPostingForm.getAmount().replace(",", "")) * -1;
			} else {
				amount = Float.parseFloat(glPostingForm.getAmount().replace(",", ""));
			}
			CustomerAccount customerAccount = this.cAccBo.getCustomerByAccount_no(glPostingForm.getPAccountNo());
			customerAccount.setCurrBalance(customerAccount.getCurr_balance() + amount);
			
			this.cAccBo.update(customerAccount);
			
			gLEntry1.setBranch(customerAccount.getOrganisation().getId());
			gLEntry1.setProductID(glPostingForm.getPAccountNo());
			gLEntry1.setAccountNo(customerGl);
		} else {*/
		gLEntry1.setBranch(this.organisationBo.getOrganisationById(glPostingForm.getPBranchID()));
		//}
		
		/*if (account2.equals("CA")) {
			System.out.println("customer account 2 ");
			CustomerEntry customerEntry= new CustomerEntry();
			customerEntry.setAmount(Float.parseFloat(glPostingForm.getAmount().replace(",", "")));
			customerEntry.setBatchNo(batch_no);
			customerEntry.setCreate_date(new Date(System.currentTimeMillis()));
			customerEntry.setAccountNo(glPostingForm.getRAccountNo());
			customerEntry.setOrganisation(this.userIdentity.getOrganisation());
			customerEntry.setOrgCoy(this.userIdentity.getOrganisation().getOrgCoy());
			customerEntry.setPostCode(glPostingForm.getRPostCode());
			customerEntry.setDescription(glPostingForm.getRDescription());
			customerEntry.setCreated_by(userIdentity.getUser());
			customerEntry.setPostingDate(DateUtils.formatStringToDate(glPostingForm.getPostingDate()));
			
			this.cAccBo.CustEntry(customerEntry);
			
			String customerGl = this.settingBo.fetchsettings("customer-GLP", 2).getSettings_value();
			CustomerAccount customerAccount = this.cAccBo.getCustomerByAccount_no(glPostingForm.getRAccountNo());
			customerAccount.setCurrBalance(customerAccount.getCurr_balance() + Float.parseFloat(glPostingForm.getAmount().replace(",", "")));
			
			this.cAccBo.update(customerAccount);
			
			gLEntry2.setBranch(customerAccount.getOrganisation().getId());
			gLEntry2.setProductID(glPostingForm.getPAccountNo());
			gLEntry2.setAccountNo(customerGl);
		} else {*/
			
			gLEntry2.setBranch(this.organisationBo.getOrganisationById(glPostingForm.getRBranchID()));
		//}


		this.GLEntry(gLEntry1);
		this.GLEntry(gLEntry2);
	}
	

	@Override
	public void GLEntry(GLEntry glEntry) throws LedgerException 
	{
		LedgerAccount ledgerAccount = new LedgerAccount();
		float amount = glEntry.getAmount();
		System.out.println(glEntry.getAccountNo());
		try {

			ledgerAccount = this.ledgerAccBo.getLedgerByAccount_no(glEntry.getAccountNo());
			ledgerAccount.setAmount(this.getAmount(amount, glEntry.getAccountNo().charAt(0), glEntry.getPostCode()));
			
		} catch (Exception e) {
			throw new LedgerException("Account number '" + glEntry.getAccountNo() + "' does not exist in Ledger accounts");
		}
		
		
		
		glEntry.setAmount(ledgerAccount.getAmount());
		this.genLedgerDao.GLEntry(glEntry);
		
		this.updateGLBalance(ledgerAccount, glEntry.getBranch().getId());

		
		if (glEntry.getBranch().getId() != glEntry.getOrganisation().getId()) {
			System.out.println("different branches: " + glEntry.getBranch() + " : " + glEntry.getOrganisation().getId());
			System.out.println(Math.abs(glEntry.getAmount()));
			glEntry.setAmount(Math.abs(glEntry.getAmount()));
			this.interbankBalancing(glEntry);
		}
		
	}

	@Override
	public void updateGLBalance(LedgerAccount ledgerAccount, int branch_id) throws LedgerException{
		
		GenLedgBalance genLedgBalance = this.getBalance(ledgerAccount.getAccountNo(), branch_id, this.userIdentity.getOrganisation().getOrgCoy().getId());
		genLedgBalance.setModify_date(new Date(System.currentTimeMillis()));
		genLedgBalance.setModified_by(userIdentity.getUser());
		
		
		genLedgBalance.setCurrBalance(ledgerAccount.getAmount() + genLedgBalance.getCurrBalance());
		this.genLedgerDao.updateGLBalance(genLedgBalance);
		
	}

	
	
	@SuppressWarnings("null")
	public float getAmount(float amount, Character ledgerType, String postCode) throws LedgerException {

		/* debit */
		if (postCode.equals("DR")) {
			System.out.println("debit");
			if (ledgerType == '1' || ledgerType == '5') {
				System.out.println("debit of debit");
				// debit of debit
				return amount *= 1;
			} else {
				System.out.println("debit of credit");
				//debit of credit 
				return amount *= -1;
			}
		}
		
		
		/* credit */
		if (postCode.equals("CR")) {
			if (ledgerType == '1' || ledgerType == '5') {
				// credit of debit
				return amount *= -1;
			} else {
				//credit of credit 
				return amount *= 1;
			}
		}
		
		System.out.print("this is the wahala");
		return (Float) null;
	}
	
	public void interbankBalancing(GLEntry glEntry) throws LedgerException {

		// inter branch posting when the branch and the organisation ARE different
		/*
		 * Initialization
		 */
		/*SettingsAssignment settingsAssignment = new SettingsAssignment();
		settingsAssignment.setSettings_code("interbank-GLP");
		*/
		
		Organisation org = this.userIdentity.getOrganisation();
		float amount = glEntry.getAmount();
		SettingsAssignment settingsAssignment = this.settingBo.fetchsettings("interbank-GLP", org.getOrgCoy().getId());
		String sysAccountNo = settingsAssignment.getSettings_value();
		this.getBalance(sysAccountNo, org.getId(), org.getOrgCoy().getId());
		User user = new User();
		user.setUserId(1);
		
		GLEntry gLEntry1 = new GLEntry();
		GLEntry gLEntry2 = new GLEntry();
		/*
		 * 
		 * first GL entry
		 * change accoun no to interbranch ledger gotten from system settings inter_bGL
		 * invert postcode (debit to credit)
		 * run getAmount again
		 * created by will be 0 (system user)
		 * branch and org id will be original branch id
		 * 
		 */
				

		System.out.println(sysAccountNo);
		gLEntry1.setAccountNo(sysAccountNo);
		if (glEntry.getPostCode().equals("DR")) {
			gLEntry1.setPostCode("CR");
			gLEntry1.setAmount(amount);
			/*System.out.println("amount two: " + this.getAmount(amount, sysAccountNo.charAt(0), "CR"));
		*/} else {
			gLEntry1.setPostCode("DR");
			gLEntry1.setAmount(amount);
			/*System.out.println("amount three: " + this.getAmount(amount, sysAccountNo.charAt(0), "DR"));
		*/}
		gLEntry1.setOrganisation(glEntry.getBranch());
		gLEntry1.setOrgCoy(this.userIdentity.getOrganisation().getOrgCoy());
		gLEntry1.setBranch(glEntry.getBranch());
		gLEntry1.setCreated_by(user);		
		gLEntry1.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry1.setBatchNo(glEntry.getBatchNo());
		gLEntry1.setRefNo1(glEntry.getRefNo1());
		gLEntry1.setDescription(glEntry.getDescription().concat("-ITB"));
		gLEntry1.setPostingDate(glEntry.getPostingDate());
		
		/*
		 * 
		 * second GL entry
		 * change accoun no to interbranch ledger gotten from system settings
		 * do not invert postcode (debit to credit)
		 * run getAmount again
		 * created by will be 0 (system user)
		 * branch and org id will be original org id
		 * 
		 */
		
		gLEntry2.setAccountNo(sysAccountNo);
		gLEntry2.setPostCode(glEntry.getPostCode());
		if (glEntry.getPostCode().equals("DR")) {
			gLEntry2.setPostCode("DR");
			gLEntry2.setAmount(amount);
			/*System.out.println("amount four: " + this.getAmount(amount, sysAccountNo.charAt(0), "DR"));
		*/} else {
			gLEntry2.setPostCode("CR");
			gLEntry2.setAmount(amount);
			/*System.out.println("amount five: " + this.getAmount(amount, sysAccountNo.charAt(0), "CR"));
		*/}
		gLEntry2.setOrganisation(glEntry.getOrganisation());
		gLEntry2.setOrgCoy(this.userIdentity.getOrganisation().getOrgCoy());
		gLEntry2.setBranch(glEntry.getOrganisation());
		gLEntry2.setCreated_by(user);
		gLEntry2.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry2.setBatchNo(glEntry.getBatchNo());
		gLEntry2.setRefNo1(glEntry.getRefNo1());
		gLEntry2.setDescription(glEntry.getDescription().concat("-ITB"));
		gLEntry2.setPostingDate(glEntry.getPostingDate());
		
		this.GLEntry(gLEntry1);
		this.GLEntry(gLEntry2);
	}
	
	public boolean getLedgerStat(String account_no, int branch_id, int company_id) throws LedgerException{
		
		return this.genLedgerDao.getLedgerStat(account_no, branch_id, company_id);
	}
	
	public List<GLEntry> getGLEntries(int org_id) {
		return this.addNameToGLEntries(this.genLedgerDao.getGLEntries(org_id));
	}

	public List<GLEntry> getGLEntriesByBatch_no(String batch_no) {
		return this.genLedgerDao.getGLEntriesByBatch_no(batch_no);
	}


	public void GLReversal(String batch_no) throws LedgerException {
		List<GLEntry> glEntries = this.getGLEntriesByBatch_no(batch_no);

		String new_batch_no = LedgerUtility.getBatchNo();
		System.out.println(glEntries.size() + " items");
		User user = this.userIdentity.getUser();
		
		for(GLEntry glEntry: glEntries){
			System.out.println(glEntry.getAccountNo());
			String desc = glEntry.getDescription();
			glEntry.setRefNo2("REVERSED");
			glEntry.setDescription("REVERSED-".concat(desc));
			
			this.genLedgerDao.GLEntry(glEntry);
			
			GLEntry glEntry1 = new GLEntry();
			glEntry1.setAccountNo(glEntry.getAccountNo());
			glEntry1.setAmount(glEntry.getAmount());
			glEntry1.setOrganisation(glEntry.getBranch());
			glEntry1.setOrgCoy(user.getOrganisation().getOrgCoy());
			glEntry1.setBranch(glEntry.getBranch());
			glEntry1.setCreated_by(user);		
			glEntry1.setCreate_date(new Date(System.currentTimeMillis()));
			glEntry1.setBatchNo(new_batch_no);
			glEntry1.setRefNo1(glEntry.getRefNo1());
			glEntry1.setDescription("REVERSAL-".concat(desc));
			glEntry1.setPostingDate(new Date(System.currentTimeMillis()));
			
			if (glEntry.getPostCode().equals("DR")) {
				System.out.println("equals DR");
				glEntry1.setPostCode("CR");
			} else {
				System.out.println("equals CR");
				glEntry1.setPostCode("DR");
			}

			glEntry1.setRefNo2("REVERSAL");
			this.GLEntry(glEntry1);
		}
		
	}

	@Override
	public List<CustomerEntry> getCustEntries() {

		return this.genLedgerDao.getCustEntries();
	}

	@Override
	public void CustReversal(String batch_no) throws LedgerException {
		List<GLEntry> glEntries = this.getGLEntriesByBatch_no(batch_no);
		List<CustomerEntry> customerEntries = this.getCustEntriesByBatch_no(batch_no);

		String new_batch_no = LedgerUtility.getBatchNo();
		User user = this.userIdentity.getUser();
		
		for(CustomerEntry customerEntry: customerEntries){
			String desc = customerEntry.getDescription();
			customerEntry.setRefNo2("REVERSED");
			customerEntry.setDescription("REVERSED-".concat(desc));
			
			this.cAccBo.CustEntry(customerEntry);
			
			CustomerEntry customerEntry1 = new CustomerEntry();
			customerEntry1.setAccountNo(customerEntry.getAccountNo());
			customerEntry1.setAmount(customerEntry.getAmount());
			customerEntry1.setOrganisation(this.organisationBo.getOrganisationById(customerEntry.getOrganisation().getId()));
			customerEntry1.setOrgCoy(user.getOrganisation().getOrgCoy());
			customerEntry1.setCreated_by(user);		
			customerEntry1.setCreate_date(new Date(System.currentTimeMillis()));
			customerEntry1.setBatchNo(new_batch_no);
			customerEntry1.setDescription("REVERSAL-".concat(desc));
			customerEntry1.setPostingDate(new Date(System.currentTimeMillis()));
			
			if (customerEntry.getPostCode().equals("DR")) {
				System.out.println("equals DR");
				customerEntry1.setPostCode("CR");
			} else {
				System.out.println("equals CR");
				customerEntry1.setPostCode("DR");
			}

			customerEntry1.setRefNo2("REVERSAL");
			this.cAccBo.CustEntry(customerEntry1);
		}
		
		for(GLEntry glEntry: glEntries){
			System.out.println(glEntry.getAccountNo());
			String desc = glEntry.getDescription();
			glEntry.setRefNo2("REVERSED");
			glEntry.setDescription("REVERSED-".concat(desc));
			
			this.genLedgerDao.GLEntry(glEntry);
			
			GLEntry glEntry1 = new GLEntry();
			glEntry1.setAccountNo(glEntry.getAccountNo());
			glEntry1.setAmount(Math.abs(glEntry.getAmount()));
			glEntry1.setOrganisation(glEntry.getBranch());
			glEntry1.setOrgCoy(user.getOrganisation().getOrgCoy());
			glEntry1.setBranch(glEntry.getBranch());
			glEntry1.setCreated_by(user);		
			glEntry1.setCreate_date(new Date(System.currentTimeMillis()));
			glEntry1.setBatchNo(new_batch_no);
			glEntry1.setRefNo1(glEntry.getRefNo1());
			glEntry1.setDescription("REVERSAL-".concat(desc));
			glEntry1.setPostingDate(new Date(System.currentTimeMillis()));
			
			if (glEntry.getPostCode().equals("DR")) {
				System.out.println("equals DR");
				glEntry1.setPostCode("CR");
			} else {
				System.out.println("equals CR");
				glEntry1.setPostCode("DR");
			}

			glEntry1.setRefNo2("REVERSAL");
			this.GLEntry(glEntry1);
		}
		
		
		
	}

	private List<CustomerEntry> getCustEntriesByBatch_no(String batch_no) {
		return this.genLedgerDao.getCustEntriesByBatch_no(batch_no);
	}

	@Override
	public List<JournalEntry> getJournalEntries() throws LedgerException {
		return this.genLedgerDao.getJournalEntries();
	}

	@Override
	public List<GLEntry> getGLEntriesListing(String account_no, String start_date, String end_date) throws LedgerException {
		return this.genLedgerDao.getGLEntriesListing(account_no, start_date, end_date);
	}
	
	@Override
	public List<GLEntry> getGLEntriesListingCom(String account_no, String start_date, String end_date) throws LedgerException {
		return this.genLedgerDao.getGLEntriesListingCom(account_no, start_date, end_date);
	}

	@Override
	public List<CustomerEntry> getCustEntriesListing(String account_no, String start_date, String end_date) throws LedgerException {
		return this.genLedgerDao.getCustEntriesListing(account_no, start_date, end_date);
	}
	
	public List<GLEntry> addNameToGLEntries(List<GLEntry> glEntries) {
		
		for (GLEntry glEntry : glEntries) {
			glEntry.setName(this.ledgerAccBo.getLedgerByAccount_no(glEntry.getAccountNo()).getName());
			
		}
		
		return glEntries;
	}

	@Override
	public List<GLEntry> getEntriesForGL(Integer org_id, String accountNo) {
		return this.addNameToGLEntries(this.genLedgerDao.getEntriesForGL(org_id, accountNo));
	}

	@Override
	public List<GLEntry> getEntriesForGLCompany(int company_id, String accountNo) {
		return this.addNameToGLEntries(this.genLedgerDao.getEntriesForGLCompany(company_id, accountNo));
	}
}
