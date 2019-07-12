package org.calminfotech.billing.daoImpl;

import java.util.List;

import org.calminfotech.billing.daoInterface.BillSchemeItemDao;
import org.calminfotech.billing.models.BillSchemeItem;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BillSchemeItemDaoImpl implements BillSchemeItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BillSchemeItem> fetchAll() {
		List<BillSchemeItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillSchemeItem").list();
		return list;
	}

	@Override
	public BillSchemeItem getBillSchemeItemById(int id) {
		// TODO Auto-generated method stub
		List<BillSchemeItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillSchemeItem where billSchemeItemId = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	
	

	@Override
	public void save(BillSchemeItem billSchemeItem){
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billSchemeItem);
		//return ehmoItem;
		
	}
	
	@Override
	public void delete(BillSchemeItem billSchemeItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billSchemeItem);

	}

	@Override
	public void update(BillSchemeItem billSchemeItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billSchemeItem);

	}

}
