package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.BalSheetDao;
import org.calminfotech.ledger.models.BalSheetCat;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BalSheetDaoImpl implements BalSheetDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<BalSheetCat> fetchAll(){
		
		List<BalSheetCat> balSheetCats = sessionFactory.getCurrentSession()
				.createQuery(" from BalSheetCat")
				.list();
		return balSheetCats;
	}
	
	@SuppressWarnings("unchecked")
	public List<BalSheetCat> fetchParents(int id){
		
		List<BalSheetCat> balSheetCats = sessionFactory.getCurrentSession()
				.createQuery(" from BalSheetCat WHERE id != ?")
				.setParameter(0, id)
				.list();
		return balSheetCats;
	}
	
	@SuppressWarnings("unchecked")
	public BalSheetCat getLedgerById(int id){
		List<BalSheetCat> list = this.sessionFactory.getCurrentSession()
		.createQuery("FROM BalSheetCat WHERE id = ?")
		.setParameter(0, id).list();
		
		if (list.size() > 0)
			return (BalSheetCat) list.get(0);
		
		return null;
	}
	
	public void save(BalSheetCat balSheetCat){
		this.sessionFactory.getCurrentSession().save(balSheetCat);
	}
	
	public void delete(BalSheetCat balSheetCat){
		System.out.println("Deleting");
		this.sessionFactory.getCurrentSession().delete(balSheetCat);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void update(BalSheetCat balSheetCat){
		System.out.println("Updating");
		this.sessionFactory.getCurrentSession().update(balSheetCat);
	}
}
