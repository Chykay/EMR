package org.calminfotech.ledger.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.calminfotech.ledger.daoInterface.LedgerAccDao;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class LedgerAccDaoImpl implements LedgerAccDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SettingBo settingBo;
	
	@Autowired
	private UserIdentity userIdentity;

	@SuppressWarnings("unchecked")
	public List<LedgerAccount> fetchAll(int company_id){
		List<String> interfaces = this.settingBo.fetchAllGLSettings(company_id);
		
		if (interfaces == null) {
			interfaces = new ArrayList<String>();
			interfaces.add("0-0000-000");
		}
		
		List<LedgerAccount> ledgerAccounts = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerAccount WHERE  company_id = ? AND account_no NOT IN ( :interfaces )")
				.setParameter(0, company_id)
				.setParameterList("interfaces", interfaces)
				.list();
		
		if (ledgerAccounts.size() > 0)
			return ledgerAccounts;
		
		return null;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<LedgerAccount> fetchTop100(int company_id){
		List<String> interfaces = this.settingBo.fetchAllGLSettings(company_id);
		
		if (interfaces == null) {
			interfaces = new ArrayList<String>();
			interfaces.add("0-0000-000");
		}
		
		List<LedgerAccount> ledgerAccounts = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerAccount WHERE company_id = ? AND account_no NOT IN ( :interfaces ) ORDER BY create_date DESC")
				.setParameter(0, company_id)
				.setParameterList("interfaces", interfaces)
				.setMaxResults(100)
				.list();
		
		if (ledgerAccounts.size() > 0)
			return ledgerAccounts;
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public LedgerAccount getLedgerById(int id){
		List<LedgerAccount> list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM LedgerAccount WHERE id = ?")
				.setParameter(0, id).list();
				
		if (list.size() > 0)
			return (LedgerAccount) list.get(0);
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public LedgerAccount getLedgerByAccount_no(String account_no){
		List<LedgerAccount> list = this.sessionFactory.getCurrentSession()
		.createQuery("FROM LedgerAccount WHERE account_no = ?")
		.setParameter(0, account_no).list();
		
		if (list.size() > 0)
			return (LedgerAccount) list.get(0);
		
		return null;
	}
	
	
	
	public void save(LedgerAccount ledgerAccount){
		this.sessionFactory.getCurrentSession().save(ledgerAccount);
	}
	
	public void delete(LedgerAccount ledgerAccount){
		System.out.println("Deleting");
		this.sessionFactory.getCurrentSession().delete(ledgerAccount);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void update(LedgerAccount ledgerAccount){
		System.out.println("Updating");
		this.sessionFactory.getCurrentSession().update(ledgerAccount);
	}

	@SuppressWarnings("unchecked")
	public List<LedgerAccount> getAssetLedgers() {
		Organisation org = this.userIdentity.getOrganisation();
		List<String> interfaces = this.settingBo.fetchAllGLSettings(org.getOrgCoy().getId());
		
		if (interfaces == null) {
			interfaces = new ArrayList<String>();
			interfaces.add("0-0000-000");
		}
		
		List<LedgerAccount> ledgerAccounts = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerAccount WHERE organisation_id = ? AND company_id = ? AND account_no LIKE '1%' AND account_no NOT IN(:interfaces)")
				.setParameter(0, org.getId())
				.setParameter(1, org.getOrgCoy().getId())
				.setParameterList("interfaces", interfaces)
				.list();
		
		if (ledgerAccounts.size() > 0)
			return ledgerAccounts;
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isTotUsed(String code) {
		List<LedgerAccount> ledgerAccounts = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerAccount WHERE  company_id = ? AND totaling_code = ?")
				.setParameter(0, this.userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, code)
				.list();
		
		if (ledgerAccounts.size() > 0)
			return true;
		
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean isUsed(String accountNo) {
		List<LedgerAccount> ledgerAccounts = sessionFactory.getCurrentSession()
				.createQuery(" from GLEntry WHERE  company_id = ? AND account_no = ?")
				.setParameter(0, this.userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, accountNo)
				.list();
		
		if (ledgerAccounts.size() > 0)
			return true;
		
		return false;
	}

}
