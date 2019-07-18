package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.TotCodeDao;
import org.calminfotech.ledger.models.TotalingCode;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TotCodeDaoImpl implements TotCodeDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;

	@SuppressWarnings("unchecked")
	public List<TotalingCode> fetchAll(){
		
		List<TotalingCode> totalingCodes = sessionFactory.getCurrentSession()
				.createQuery(" from TotalingCode WHERE company_id = ?")
				.setParameter(0, this.userIdentity.getOrganisation().getOrgCoy().getId())
				.list();
		return totalingCodes;
	}
	
	@SuppressWarnings("unchecked")
	public List<TotalingCode> fetchAllActive(){
		
		List<TotalingCode> totalingCodes = sessionFactory.getCurrentSession()
				.createQuery(" from TotalingCode WHERE company_id = ? AND is_active = 1")
				.setParameter(0, this.userIdentity.getOrganisation().getOrgCoy().getId())
				.list();
		return totalingCodes;
	}

	@SuppressWarnings("unchecked")
	public TotalingCode getLedgerById(int id){
		List<TotalingCode> list = this.sessionFactory.getCurrentSession()
		.createQuery("FROM TotalingCode WHERE id = ?")
		.setParameter(0, id).list();
		
		if (list.size() > 0)
			return (TotalingCode) list.get(0);
		
		return null;
	}
	
	public void save(TotalingCode totalingCode){
		this.sessionFactory.getCurrentSession().save(totalingCode);
	}
	
	public void delete(TotalingCode totalingCode){
		System.out.println("Deleting");
		this.sessionFactory.getCurrentSession().delete(totalingCode);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void update(TotalingCode totalingCode){
		System.out.println("Updating");
		this.sessionFactory.getCurrentSession().update(totalingCode);
	}
}
