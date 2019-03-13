package org.calminfotech.ledger.boImpl;

import java.util.Date;
import java.util.Random;

import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.daoInterface.GenLedgerDao;
import org.calminfotech.ledger.forms.GLPostingForm;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.utiility.LedgerException;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
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
		
		
		System.out.println("Post cods: " + glEntry.getPost_code());
		ledgerAccount.setModified_by(this.userIdentity.getUser());
		ledgerAccount.setModify_date(new Date(System.currentTimeMillis()));
		
		ledgerAccount.setAmount(this.getAmount(amount, glEntry.getAccount_no().charAt(0), glEntry.getPost_code()));
		

		System.out.println("Post codeee: " + glEntry.getPost_code());
		if (glEntry.getPost_code().equals("001")) {
			glEntry.setPost_code("DR");
		} else if (glEntry.getPost_code().equals("002")) {
			glEntry.setPost_code("CR");
		}
		
		glEntry.setAmount(ledgerAccount.getAmount());
		this.genLedgerDao.GLEntry(glEntry);
		
		this.updateGLBalance(ledgerAccount, glEntry.getOrganisation().getId());

		
		if (glEntry.getBranch() == glEntry.getOrganisation().getId()) {
			System.out.println("different branches");
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
		
		String batch_no = new Random(System.currentTimeMillis()).toString();
		
		gLEntry1.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry1.setAccount_no(glPostingForm.getP_account_no());
		gLEntry1.setOrganisation(this.userIdentity.getOrganisation());
		gLEntry1.setBranch(this.organisationBo.getOrganisationById(glPostingForm.getP_branch_id()).getId());
		gLEntry1.setRef_no1(glPostingForm.getRef_no1());
		gLEntry1.setPost_code(glPostingForm.getP_post_code());
		gLEntry1.setAmount(Float.parseFloat(glPostingForm.getAmount().replace(",", "")));
		gLEntry1.setDescription(glPostingForm.getP_description());
		gLEntry1.setCreated_by(userIdentity.getUser());
		gLEntry1.setBatch_no(batch_no);
		System.out.println(batch_no + " : " + gLEntry1.getPost_code());
		
		gLEntry2.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry2.setAccount_no(glPostingForm.getR_account_no());
		gLEntry2.setOrganisation(this.userIdentity.getOrganisation());
		gLEntry2.setBranch(this.organisationBo.getOrganisationById(glPostingForm.getR_branch_id()).getId());
		gLEntry2.setRef_no1(glPostingForm.getRef_no1());
		gLEntry2.setPost_code(glPostingForm.getR_post_code());
		gLEntry2.setAmount(Float.parseFloat(glPostingForm.getAmount().replace(",", "")));
		gLEntry2.setDescription(glPostingForm.getR_description());
		gLEntry2.setCreated_by(userIdentity.getUser());
		gLEntry2.setBatch_no(batch_no);
		System.out.println(batch_no + " : " + gLEntry2.getPost_code());

		this.GLEntry(gLEntry1);
		this.GLEntry(gLEntry2);
		
	}
	
	
	public float getAmount(float amount, Character ledgerType, String postCode) throws LedgerException {

		System.out.println( amount + " : " +  ledgerType + " : " +  postCode);
		
		if (postCode.contains("001")) {
			if ((ledgerType == '1' || ledgerType == '5')) {
				// debit of debit
				return amount *= 1;
			} else {
				//debit of credit 
				return amount *= -1;
			}
		}
		
		/*if (postCode.equals("001") && !(ledgerType == '1' || ledgerType == '5')) {
			
		}*/
		
		// credit of credit
		if (postCode.equals("002")) {
			if ((ledgerType == '1' || ledgerType == '5')) {
				// credit of debit
				return amount *= -1;
			} else {
				//credit of credit 
				return amount *= 1;
			}
		}
		
		//credit of debit
		/*if (postCode.equals("002") && (ledgerType == '1' || ledgerType == '5')) {
			return amount *= -1;
		}*/
		
		//return (Float) null;
		return 0;
	}
	
	public void interbankBalancing(GLEntry glEntry) throws LedgerException {

		// inter branch posting when the branch and the organisation ARE different
		/*
		 * Initialization
		 */
		float amount = glEntry.getAmount();
		String glAccountNo = glEntry.getAccount_no();
		SettingsAssignment settingsAssignment = this.settingBo.fetchsettings("interbank-GLP", glEntry.getOrgCoy().getId());
		String sysAccountNo = settingsAssignment.getSettings_value();
		User user = new User();
		user.setUserId(0);
		
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
		if (glEntry.getPost_code().equals("DR")) {
			gLEntry1.setPost_code("CR");
			gLEntry1.setAmount(this.getAmount(amount, glAccountNo.charAt(0), "002"));
		} else {
			gLEntry1.setPost_code("DR");
			gLEntry1.setAmount(this.getAmount(amount, glAccountNo.charAt(0), "001"));
		}
		gLEntry1.setOrganisation(this.organisationBo.getOrganisationById(glEntry.getBranch()));
		gLEntry1.setBranch(glEntry.getBranch());
		gLEntry1.setCreated_by(user);		
		gLEntry1.setCreate_date(new Date(System.currentTimeMillis()));
		
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
		if (gLEntry2.getPost_code().equals("DR")) {
			gLEntry2.setAmount(this.getAmount(amount, glAccountNo.charAt(0), "001"));
		} else {
			gLEntry2.setAmount(this.getAmount(amount, glAccountNo.charAt(0), "002"));
		}
		gLEntry2.setOrganisation(this.organisationBo.getOrganisationById(glEntry.getBranch()));
		gLEntry2.setBranch(glEntry.getOrganisation().getId());
		gLEntry2.setCreated_by(user);
		gLEntry2.setCreate_date(new Date(System.currentTimeMillis()));

		this.GLEntry(gLEntry1);
		this.GLEntry(gLEntry2);
	}
	
	public boolean getLedgerStat(String account_no, int branch_id, int company_id) throws LedgerException{
		
		return this.genLedgerDao.getLedgerStat(account_no, branch_id, company_id);
	}
}
