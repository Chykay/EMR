package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.daoInterface.GenLedgerDao;
import org.calminfotech.ledger.forms.GLPostingForm;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.utiility.LedgerException;
import org.calminfotech.ledger.utiility.LedgerUtility;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.SettingBo;
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
	public void GLEntry(GLEntry glEntry) throws LedgerException 
	{
		
		LedgerAccount ledgerAccount = this.ledgerAccBo.getLedgerByAccount_no(glEntry.getAccount_no());
		float amount = glEntry.getAmount();
		
		
		ledgerAccount.setModified_by(this.userIdentity.getUser());
		ledgerAccount.setModify_date(new Date(System.currentTimeMillis()));
		ledgerAccount.setAmount(this.getAmount(amount, glEntry.getAccount_no().charAt(0), glEntry.getPost_code()));
		

		if (glEntry.getPost_code().contains("001")) {
			glEntry.setPost_code("DR");
		} else if (glEntry.getPost_code().contains("002")) {
			glEntry.setPost_code("CR");
		}
		
		glEntry.setAmount(ledgerAccount.getAmount());
		this.genLedgerDao.GLEntry(glEntry);
		
		this.updateGLBalance(ledgerAccount, glEntry.getBranch());

		
		if (glEntry.getBranch() != glEntry.getOrganisation().getId()) {
			System.out.println("different branches: " + glEntry.getBranch() + " : " + glEntry.getOrganisation().getId());
			this.interbankBalancing(glEntry);
		}
		
	}

	@Override
	public void updateGLBalance(LedgerAccount ledgerAccount, int branch_id) throws LedgerException{
		
		GenLedgBalance genLedgBalance = this.getBalance(ledgerAccount.getAccount_no(), branch_id, this.userIdentity.getOrganisation().getOrgCoy().getId());
		genLedgBalance.setModify_date(new Date(System.currentTimeMillis()));
		genLedgBalance.setModified_by(userIdentity.getUser());
		
		
		genLedgBalance.setCurr_balance(ledgerAccount.getAmount() + genLedgBalance.getCurr_balance());
		this.genLedgerDao.updateGLBalance(genLedgBalance);
		
	}

	@Override
	public void GLPosting(GLPostingForm glPostingForm) throws LedgerException {
		GLEntry gLEntry1 = new GLEntry();
		GLEntry gLEntry2 = new GLEntry();
		
		String batch_no = LedgerUtility.getBatchNo();
		
		gLEntry1.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry1.setAccount_no(glPostingForm.getP_account_no());
		gLEntry1.setOrganisation(this.userIdentity.getOrganisation());
		gLEntry1.setBranch(this.organisationBo.getOrganisationById(glPostingForm.getP_branch_id()).getId());
		gLEntry1.setOrgCoy(this.userIdentity.getOrganisation().getOrgCoy());
		gLEntry1.setRef_no1(glPostingForm.getRef_no1());
		gLEntry1.setPost_code(glPostingForm.getP_post_code());
		gLEntry1.setAmount(Float.parseFloat(glPostingForm.getAmount().replace(",", "")));
		gLEntry1.setDescription(glPostingForm.getP_description());
		gLEntry1.setCreated_by(userIdentity.getUser());
		gLEntry1.setBatch_no(batch_no);
		gLEntry1.setPosting_date(DateUtils.formatStringToDate(glPostingForm.getPosting_date()));
		
		gLEntry2.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry2.setAccount_no(glPostingForm.getR_account_no());
		gLEntry2.setOrganisation(this.userIdentity.getOrganisation());
		gLEntry2.setBranch(this.organisationBo.getOrganisationById(glPostingForm.getR_branch_id()).getId());
		gLEntry2.setOrgCoy(this.userIdentity.getOrganisation().getOrgCoy());
		gLEntry2.setRef_no1(glPostingForm.getRef_no1());
		gLEntry2.setPost_code(glPostingForm.getR_post_code());
		gLEntry2.setAmount(Float.parseFloat(glPostingForm.getAmount().replace(",", "")));
		gLEntry2.setDescription(glPostingForm.getR_description());
		gLEntry2.setCreated_by(userIdentity.getUser());
		gLEntry2.setBatch_no(batch_no);
		gLEntry2.setPosting_date(DateUtils.formatStringToDate(glPostingForm.getPosting_date()));

		this.GLEntry(gLEntry1);
		this.GLEntry(gLEntry2);
		
	}
	
	
	@SuppressWarnings("null")
	public float getAmount(float amount, Character ledgerType, String postCode) throws LedgerException {

		/* debit */
		if (postCode.contains("001")) {
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
		if (postCode.contains("002")) {
			if (ledgerType == '1' || ledgerType == '5') {
				// credit of debit
				return amount *= -1;
			} else {
				//credit of credit 
				return amount *= 1;
			}
		}
		
		
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
		float amount = glEntry.getAmount() * -1;
		String glAccountNo = glEntry.getAccount_no();
		SettingsAssignment settingsAssignment = this.settingBo.fetchsettings("interbank-GLP", 2);
		String sysAccountNo = settingsAssignment.getSettings_value();
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
				

		gLEntry1.setAccount_no(sysAccountNo);
		if (glEntry.getPost_code().contains("DR")) {
			gLEntry1.setPost_code("002");
			gLEntry1.setAmount(this.getAmount(amount, glAccountNo.charAt(0), "002"));
		} else {
			gLEntry1.setPost_code("001");
			gLEntry1.setAmount(this.getAmount(amount, glAccountNo.charAt(0), "001"));
		}
		gLEntry1.setOrganisation(this.organisationBo.getOrganisationById(glEntry.getBranch()));
		gLEntry1.setOrgCoy(this.userIdentity.getOrganisation().getOrgCoy());
		gLEntry1.setBranch(glEntry.getBranch());
		gLEntry1.setCreated_by(user);		
		gLEntry1.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry1.setBatch_no(glEntry.getBatch_no());
		gLEntry1.setRef_no1(glEntry.getRef_no1());
		gLEntry1.setDescription(glEntry.getDescription().concat("-ITB"));
		gLEntry1.setPosting_date(glEntry.getPosting_date());
		
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
		
		gLEntry2.setAccount_no(sysAccountNo);
		gLEntry2.setPost_code(glEntry.getPost_code());
		if (glEntry.getPost_code().contains("DR")) {
			gLEntry2.setPost_code("001");
			gLEntry2.setAmount(this.getAmount(amount, glAccountNo.charAt(0), "001"));
		} else {
			gLEntry2.setPost_code("002");
			gLEntry2.setAmount(this.getAmount(amount, glAccountNo.charAt(0), "002"));
		}
		gLEntry2.setOrganisation(glEntry.getOrganisation());
		gLEntry2.setOrgCoy(this.userIdentity.getOrganisation().getOrgCoy());
		gLEntry2.setBranch(glEntry.getOrganisation().getId());
		gLEntry2.setCreated_by(user);
		gLEntry2.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry2.setBatch_no(glEntry.getBatch_no());
		gLEntry2.setRef_no1(glEntry.getRef_no1());
		gLEntry2.setDescription(glEntry.getDescription().concat("-ITB"));
		gLEntry2.setPosting_date(glEntry.getPosting_date());
		
		this.GLEntry(gLEntry1);
		this.GLEntry(gLEntry2);
	}
	
	public boolean getLedgerStat(String account_no, int branch_id, int company_id) throws LedgerException{
		
		return this.genLedgerDao.getLedgerStat(account_no, branch_id, company_id);
	}
	
	public List<GLEntry> getGLEntries() {
		return this.genLedgerDao.getGLEntries();
	}

	public List<GLEntry> getGLEntriesByBatch_no(String batch_no) {
		return this.genLedgerDao.getGLEntriesByBatch_no(batch_no);
	}


	public void reverseEntries(String batch_no) throws LedgerException {
		List<GLEntry> glEntries = this.getGLEntriesByBatch_no(batch_no);

		String new_batch_no = LedgerUtility.getBatchNo();
		System.out.println(glEntries.size() + " items");
		User user = this.userIdentity.getUser();
		
		for(GLEntry glEntry: glEntries){
			System.out.println(glEntry.getAccount_no());
			glEntry.setRef_no2("REVERSED");
			glEntry.setDescription("REVERSED-".concat(glEntry.getDescription()));
			
			this.genLedgerDao.GLEntry(glEntry);
			
			GLEntry glEntry1 = new GLEntry();
			glEntry1.setAccount_no(glEntry.getAccount_no());
			glEntry1.setAmount(glEntry.getAmount());
			glEntry1.setOrganisation(this.organisationBo.getOrganisationById(glEntry.getBranch()));
			glEntry1.setOrgCoy(user.getOrganisation().getOrgCoy());
			glEntry1.setBranch(glEntry.getBranch());
			glEntry1.setCreated_by(user);		
			glEntry1.setCreate_date(new Date(System.currentTimeMillis()));
			glEntry1.setBatch_no(new_batch_no);
			glEntry1.setRef_no1(glEntry.getRef_no1());
			glEntry1.setDescription(glEntry.getDescription());
			glEntry1.setPosting_date(glEntry.getPosting_date());
			
			if (glEntry.getPost_code().contains("DR")) {
				System.out.println("contains DR");
				glEntry1.setPost_code("002");
			} else {
				System.out.println("contains CR");
				glEntry1.setPost_code("001");
			}

			glEntry1.setRef_no2("REVERSAL");
			this.GLEntry(glEntry1);
		}
		
	}
}
