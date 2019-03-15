package org.calminfotech.ledger.daoImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.ledger.daoInterface.GenLedgerDao;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.GenLedgBalance;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenLedgerDaoImpl implements GenLedgerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private OrganisationBo organisationBo;

	@Autowired
	private SettingBo settingBo;
	

	@SuppressWarnings("unchecked")
	public GenLedgBalance getBalance(String account_no, int branch_id, int company_id) {
		List<GenLedgBalance> balance = sessionFactory.getCurrentSession()
				.createQuery("FROM GenLedgBalance WHERE gl_account_no = ? AND organisation_id = ? AND company_id = ? ")
				.setParameter(0, account_no)
				.setParameter(1, branch_id)
				.setParameter(2, company_id)
				.list();
				
		if (balance.size() > 0)
			return (GenLedgBalance) balance.get(0);
		

		GenLedgBalance genLedgBalance = new GenLedgBalance();
		
		genLedgBalance.setCreate_date(new Date(System.currentTimeMillis()));
		genLedgBalance.setCreated_by(this.userIdentity.getUser());
		genLedgBalance.setCurr_balance(0);
		genLedgBalance.setOrganisation(this.organisationBo.getOrganisationById(branch_id));
		genLedgBalance.setOrgCoy(this.organisationBo.getOrganisationById(branch_id).getOrgCoy());
		genLedgBalance.setGl_account_no(account_no);
		genLedgBalance.setCurrency("NGN");
		
		
		return this.saveGLBalance(genLedgBalance);
	}

	@Override
	public void updateGLBalance(GenLedgBalance genLedgBalance) {

		System.out.println("dao balance: " + genLedgBalance.getCurr_balance());
		System.out.println("dao account_no: " + genLedgBalance.getGl_account_no());
		System.out.println("dao organ: " + genLedgBalance.getOrganisation().getId());
		this.sessionFactory.getCurrentSession().update(genLedgBalance);
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
	}
	
	@Override
	public GenLedgBalance saveGLBalance(GenLedgBalance genLedgBalance) {
		this.sessionFactory.getCurrentSession().save(genLedgBalance);
		return genLedgBalance;
	}

	

	@Override
	public void GLEntry(GLEntry glEntry) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(glEntry);
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
	}
	
	@SuppressWarnings("unchecked")
	public boolean getLedgerStat(String account_no, int branch_id, int company_id) {
		List<GenLedgBalance> balance = sessionFactory.getCurrentSession()
				.createQuery("FROM GenLedgBalance WHERE gl_account_no = ? AND organisation_id = ? AND company_id = ? ")
				.setParameter(0, account_no)
				.setParameter(1, branch_id)
				.setParameter(2, company_id)
				.list();
				
		if (balance.size() > 0)
			return true;
	
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<GLEntry> getGLEntries(){
		List<GLEntry> entries = sessionFactory.getCurrentSession()
				.createQuery("FROM GLEntry WHERE company_id = ? AND organisation_id = ? AND account_no != ? AND ref_no2 != 'REVERSED' AND ref_no2 != 'REVERSAL'")
				.setParameter(0, userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, userIdentity.getOrganisation().getId())
				.setParameter(2, this.settingBo.fetchsettings("interbank-GLP", 2).getSettings_value())
				.list();
		
		return entries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GLEntry> getGLEntriesByBatch_no(String batch_no) {
		// TODO Auto-generated method stub
		
		List<GLEntry> glEntries = sessionFactory.getCurrentSession()
				.createQuery("FROM GLEntry WHERE batch_no = ? ")
				.setParameter(0, batch_no)
				.list();
		
		
		return glEntries;
	}
}
