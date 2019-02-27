package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.AssetCategoryDao;
import org.calminfotech.system.models.AssetCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AssetCategoryDaoImpl implements AssetCategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<AssetCategory> fetchAll() {
		List<AssetCategory> list = sessionFactory.getCurrentSession()
								   .createQuery("from AssetCategory").list();
		return list;
	}

	@Override
	public AssetCategory getAssetCategoryId(int id) {
		List<AssetCategory> list = sessionFactory.getCurrentSession()
				                   .createQuery("from AssetCategory where assetCatId = ? ")
				                   .setParameter(0, id).list();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(AssetCategory assetCategory) {
		sessionFactory.getCurrentSession().save(assetCategory);
	}

	@Override
	public void update(AssetCategory assetCategory) {
		sessionFactory.getCurrentSession().update(assetCategory);
	}

	@Override
	public void delete(AssetCategory assetCategory) {
		sessionFactory.getCurrentSession().delete(assetCategory);
	}

}
