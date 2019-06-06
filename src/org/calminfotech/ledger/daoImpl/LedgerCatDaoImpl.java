package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.LedgerCatDao;
import org.calminfotech.ledger.models.LedgerCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LedgerCatDaoImpl implements LedgerCatDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<LedgerCategory> fetchAll(){
		
		List<LedgerCategory> balSheetCats = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerCategory")
				.list();
		return balSheetCats;
	}
	
	@SuppressWarnings("unchecked")
	public List<LedgerCategory> fetchParents(int id){
		
		List<LedgerCategory> balSheetCats = sessionFactory.getCurrentSession()
				.createQuery(" from LedgerCategory WHERE id != ?")
				.setParameter(0, id)
				.list();
		return balSheetCats;
	}
	
	@SuppressWarnings("unchecked")
	public LedgerCategory getLedgerById(int id){
		List<LedgerCategory> list = this.sessionFactory.getCurrentSession()
		.createQuery("FROM LedgerCategory WHERE id = ?")
		.setParameter(0, id).list();
		
		if (list.size() > 0)
			return (LedgerCategory) list.get(0);
		
		return null;
	}
	
	public void save(LedgerCategory balSheetCat){
		this.sessionFactory.getCurrentSession().save(balSheetCat);
	}
	
	public void delete(LedgerCategory balSheetCat){
		System.out.println("Deleting");
		this.sessionFactory.getCurrentSession().delete(balSheetCat);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void update(LedgerCategory balSheetCat){
		System.out.println("Updating");
		this.sessionFactory.getCurrentSession().update(balSheetCat);
	}
}
