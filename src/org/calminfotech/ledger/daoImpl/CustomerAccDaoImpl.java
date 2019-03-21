package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.CustomerAccDao;
import org.calminfotech.ledger.models.CustomerAccount;
import org.calminfotech.ledger.models.CustomerEntry;
import org.calminfotech.system.boInterface.SettingBo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerAccDaoImpl implements CustomerAccDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SettingBo settingBo;
	

	@SuppressWarnings("unchecked")
	public List<CustomerAccount> fetchAll(int branch_id, int company_id){
		
		List<CustomerAccount> customerAccounts = sessionFactory.getCurrentSession()
				.createQuery(" from CustomerAccount WHERE organisation_id = ? AND company_id = ? AND account_no != ?")
				.setParameter(0, branch_id)
				.setParameter(1, company_id)
				.setParameter(2, this.settingBo.fetchsettings("interbank-GLP", 2).getSettings_value())
				.list();
		return customerAccounts;
	}

	@SuppressWarnings("unchecked")
	public CustomerAccount getLedgerById(int id){
		List<CustomerAccount> list = this.sessionFactory.getCurrentSession()
		.createQuery("FROM CustomerAccount WHERE id = ?")
		.setParameter(0, id).list();
		
		if (list.size() > 0)
			return (CustomerAccount) list.get(0);
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public CustomerAccount getLedgerByAccount_no(String account_no){
		List<CustomerAccount> list = this.sessionFactory.getCurrentSession()
		.createQuery("FROM CustomerAccount WHERE account_no = ?")
		.setParameter(0, account_no).list();
		
		if (list.size() > 0)
			return (CustomerAccount) list.get(0);
		
		return null;
	}
	
	
	
	public CustomerAccount save(CustomerAccount customerAccount){
		this.sessionFactory.getCurrentSession().save(customerAccount);
		return customerAccount;
	}
	
	public void delete(CustomerAccount customerAccount){
		System.out.println("Deleting");
		this.sessionFactory.getCurrentSession().delete(customerAccount);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void update(CustomerAccount customerAccount){
		System.out.println("Updating");
		this.sessionFactory.getCurrentSession().update(customerAccount);
	}

	@Override
	public void CustEntry(CustomerEntry customerEntry) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(customerEntry);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomerAccount getBalance(String account_no, Integer branch_id, Integer company_id) {
		List<CustomerAccount> balance = sessionFactory.getCurrentSession()
				.createQuery("FROM CustomerAccount WHERE account_no = ? AND organisation_id = ? AND company_id = ? ")
				.setParameter(0, account_no)
				.setParameter(1, branch_id)
				.setParameter(2, company_id)
				.list();
				
		if (balance.size() > 0)
			return (CustomerAccount) balance.get(0);
		return null;
	}

}
