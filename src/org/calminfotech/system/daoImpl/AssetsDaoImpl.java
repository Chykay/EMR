package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.AssetsDao;
import org.calminfotech.system.models.Assets;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AssetsDaoImpl implements AssetsDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Assets> fetchAll() {
		List<Assets> list = sessionFactory.getCurrentSession().createQuery("from Assets").list();
		return list;
	}

	@Override
	public Assets getAssetsId(int id) {
		List<Assets> list = sessionFactory.getCurrentSession()
				                   .createQuery("from Assets where assetId = ? ")
				                   .setParameter(0, id).list();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(Assets assets) {
		sessionFactory.getCurrentSession().save(assets);
	}

	@Override
	public void update(Assets assets) {
		sessionFactory.getCurrentSession().update(assets);
	}

	@Override
	public void delete(Assets assets) {
		sessionFactory.getCurrentSession().delete(assets);
	}

}
