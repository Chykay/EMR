package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.VwItemDao;
import org.calminfotech.system.models.ItemVw;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VwItemDaoImpl implements VwItemDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ItemVw> fetchAllByPoint(Integer pointId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<ItemVw> list = this.sessionFactory.getCurrentSession()
				.createQuery("from VwItem where point = ?").setParameter(0, pointId)
				.list();
		
		return list;
	}

	@Override
	public ItemVw getVwItemById(Integer id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from VwItem where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0) {
			return (ItemVw) list.get(0);
		}
		return null;
	}

}
