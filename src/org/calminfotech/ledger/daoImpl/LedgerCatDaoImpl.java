package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.LedgerCatDao;
import org.calminfotech.ledger.models.LedgerCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LedgerCatDaoImpl implements LedgerCatDao {

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<LedgerCategory> fetchAll(){
		
		List<LedgerCategory> ledgerCats = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerCategory WHERE company_id = ?")
				.setParameter(0, this.userIdentity.getOrganisation().getOrgCoy().getId())
				.list();
		return ledgerCats;
	}
	
	@SuppressWarnings("unchecked")
	public List<LedgerCategory> fetchParents(int id){
		
		List<LedgerCategory> ledgerCats = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerCategory WHERE id != ? AND company_id = ?")
				.setParameter(0, id)
				.setParameter(1, this.userIdentity.getOrganisation().getOrgCoy().getId())
				.list();
		return ledgerCats;
	}
	
	@SuppressWarnings("unchecked")
	public LedgerCategory getLedgerById(int id){
		List<LedgerCategory> list = this.sessionFactory.getCurrentSession()
		.createQuery("FROM LedgerCategory WHERE id = ? AND company_id = ?")
		.setParameter(0, id)
		.setParameter(1, this.userIdentity.getOrganisation().getOrgCoy().getId())
		.list();
		
		if (list.size() > 0)
			return (LedgerCategory) list.get(0);
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<LedgerCategory> getCatsWithoutLedgerChildren() {
		List<LedgerCategory> ledgerCats = sessionFactory.getCurrentSession()
				.createQuery("SELECT A from LedgerCategory A WHERE A.id NOT IN (SELECT B.ledgerCatID FROM LedgerAccount B WHERE B.orgCoy.id = :companyID) AND A.orgCoy.id  = :companyID")
				.setParameter("companyID", this.userIdentity.getOrganisation().getOrgCoy().getId())
				.list();
		
		return ledgerCats;
	}

	@SuppressWarnings("unchecked")
	public List<LedgerCategory> getCatsWithoutCatsChildren() {
		List<LedgerCategory> ledgerCats = sessionFactory.getCurrentSession()
				.createQuery("SELECT A from LedgerCategory A WHERE A.id NOT IN (SELECT B.parentID FROM LedgerCategory B WHERE B.orgCoy.id  = :companyID) AND A.orgCoy.id  = :companyID")
				.setParameter("companyID", this.userIdentity.getOrganisation().getOrgCoy().getId())
				.list();
		
		return ledgerCats;
	}
	
	public void save(LedgerCategory ledgerCategory){
		this.sessionFactory.getCurrentSession().save(ledgerCategory);
	}
	
	public void delete(LedgerCategory ledgerCategory){
		System.out.println("Deleting");
		this.sessionFactory.getCurrentSession().delete(ledgerCategory);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void update(LedgerCategory ledgerCategory){
		System.out.println("Updating");
		this.sessionFactory.getCurrentSession().update(ledgerCategory);
	}

	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<LedgerCategory> fetchAllByOrgg(int orgID) {
		List<LedgerCategory> ledgerCats = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerCategory WHERE organisation_id = ?")
				.setParameter(0, orgID)
				.list();
		
		return ledgerCats;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LedgerCategory> fetchByLedgType(ArrayList<Integer> ledgerTypes) {
		
		for (int i : ledgerTypes) {
			System.out.println(i);
		}
		List<LedgerCategory> ledgerCats = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerCategory WHERE organisation_id = ? AND ledger_type IN ( :types )")
				.setParameter(0, this.userIdentity.getOrganisation().getId())
				.setParameterList("types", ledgerTypes)
				.list();
		
		return ledgerCats;
	}*/
}
