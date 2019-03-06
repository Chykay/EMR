package org.calminfotech.ledger.boImpl;

import java.util.Date;

import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.daoInterface.GenLedgerDao;
import org.calminfotech.ledger.forms.GLPostingForm;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.utiility.LedgerException;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GenLedgerBoImpl implements GenLedgerBo{

	@Autowired
	private GenLedgerDao genLedgerDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private OrganisationBo organisationBo;

	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	@Override
	public GenLedgBalance getBalance(String account_no, int branch_id, int company_id)  throws LedgerException{
		return this.genLedgerDao.getBalance(account_no, branch_id, company_id);
	}

	@Override
	public void GLEntry(GLEntry glEntry) throws LedgerException 
	{
		
		LedgerAccount ledgerAccount = this.ledgerAccBo.getLedgerByAccount_no(glEntry.getAccount_no());
		float balance = ledgerAccount.getBalance(), amount = glEntry.getAmount();

		if (glEntry.getPost_code() == "001") {
			glEntry.setPost_code("DB");
			
			if (balance < amount) {
				throw new LedgerException("Insufficient Funds");
			} else {
				ledgerAccount.setBalance(balance - amount);
			}
		} else {
			glEntry.setPost_code("CR");
			
			ledgerAccount.setBalance(balance + amount);
			
		}
		
		ledgerAccount.setModified_by(this.userIdentity.getUser());
		ledgerAccount.setModify_date(new Date(System.currentTimeMillis()));

		System.out.println("GLENTRY IMPL");
		this.updateGLBalance(ledgerAccount);


		this.genLedgerDao.GLEntry(glEntry);
	}

	@Override
	public void updateGLBalance(LedgerAccount ledgerAccount) throws LedgerException{
		// TODO Auto-generated method stub
		GenLedgBalance genLedgBalance = this.getBalance(ledgerAccount.getAccount_no(), ledgerAccount.getOrganisation().getId(), ledgerAccount.getOrgCoy().getId());
		genLedgBalance.setGl_account_no(ledgerAccount.getAccount_no());
		genLedgBalance.setModify_date(new Date(System.currentTimeMillis()));
		genLedgBalance.setOrganisation(ledgerAccount.getOrganisation());
		genLedgBalance.setOrgCoy(ledgerAccount.getOrgCoy());
		genLedgBalance.setModified_by(userIdentity.getUser());
		this.genLedgerDao.updateGLBalance(genLedgBalance);
		
	}

	@Override
	public void GLPosting(GLPostingForm glPostingForm) throws LedgerException {
		System.out.println("Output GLPOSTING JOR");
		GLEntry gLEntry1 = new GLEntry();
		GLEntry gLEntry2 = new GLEntry();
		
		gLEntry1.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry1.setAccount_no(glPostingForm.getP_account_no());
		gLEntry1.setOrganisation(this.organisationBo.getOrganisationById(glPostingForm.getP_branch_id()));
		gLEntry1.setRef_no1(glPostingForm.getRef_no1());
		gLEntry1.setPost_code(glPostingForm.getP_post_code());
		gLEntry1.setAmount(glPostingForm.getP_amount());
		gLEntry1.setDescription(glPostingForm.getP_description());
		gLEntry1.setCreated_by(userIdentity.getUser());
		
		gLEntry2.setCreate_date(new Date(System.currentTimeMillis()));
		gLEntry2.setAccount_no(glPostingForm.getR_account_no());
		gLEntry2.setOrganisation(this.organisationBo.getOrganisationById(glPostingForm.getR_branch_id()));;
		gLEntry2.setRef_no1(glPostingForm.getRef_no1());
		gLEntry2.setPost_code(glPostingForm.getR_post_code());
		gLEntry2.setAmount(glPostingForm.getP_amount());
		gLEntry2.setDescription(glPostingForm.getR_description());
		gLEntry2.setCreated_by(userIdentity.getUser());

		System.out.println("GLPOSTING IMPL");
		this.GLEntry(gLEntry1);
		this.GLEntry(gLEntry2);
		
	}
	
	
	

}
