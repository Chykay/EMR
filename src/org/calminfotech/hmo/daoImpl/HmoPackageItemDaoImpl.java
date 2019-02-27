package org.calminfotech.hmo.daoImpl;

import java.util.List;

import org.calminfotech.hmo.daoInterface.HmoPackageItemDao;

import org.calminfotech.hmo.models.HmoPackageItem;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HmoPackageItemDaoImpl implements HmoPackageItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HmoPackageItem> fetchAll() {
		List<HmoPackageItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HmoPackageItem").list();
		return list;
	}

	@Override
	public HmoPackageItem getHmoItemById(int id) {
		// TODO Auto-generated method stub
		List<HmoPackageItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HmoPackageItem where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	
	

	@Override
	public void save(HmoPackageItem ehmoItem){
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(ehmoItem);
		//return ehmoItem;
		
	}
	
	@Override
	public void delete(HmoPackageItem ehmoItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(ehmoItem);

	}

	@Override
	public void update(HmoPackageItem ehmoItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(ehmoItem);

	}

	@Override
	public HmoPackageItem getHmoItemByAll(int id) {
		// TODO Auto-generated method stub
				List<HmoPackageItem> list = this.sessionFactory.getCurrentSession()
						.createQuery("from HmoPackageItem where id = ? and Isall=1 and hmostatus.hmostatus_id=1").setParameter(0, id)
						.list();
				if (list.size() > 0)
					return list.get(0);
				return null;
			}

}
