package org.calminfotech.hmo.daoImpl;

import java.util.List;

import org.calminfotech.hmo.daoInterface.HmoPackageItemContentDao;

import org.calminfotech.hmo.models.HmoPackageItem;
import org.calminfotech.hmo.models.HmoPackageItemContent;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HmoPackageItemContentDaoImpl implements HmoPackageItemContentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HmoPackageItemContent> fetchAll() {
		List<HmoPackageItemContent> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HmoPackageItemContent").list();
		return list;
	}

	@Override
	public HmoPackageItemContent getHmoItemContentById(int id) {
		// TODO Auto-generated method stub
		List<HmoPackageItemContent> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HmoPackageItemContent where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	
	

	@Override
	public void save(HmoPackageItemContent ehmoItem){
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(ehmoItem);
		//return ehmoItem;
		
	}
	
	@Override
	public void delete(HmoPackageItemContent ehmoItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(ehmoItem);

	}

	@Override
	public void update(HmoPackageItemContent ehmoItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(ehmoItem);

	}

}
