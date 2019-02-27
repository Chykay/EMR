package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.GlobalItemPointDao;
import org.calminfotech.system.models.GlobalItemPoint;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GlobalItemPointDaoImpl implements GlobalItemPointDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<GlobalItemPoint> fetchAll() {
		// TODO Auto-generated method stub
		List<GlobalItemPoint> list = this.sessionFactory.getCurrentSession()
				.createQuery("from GlobalItemPoint").list();
		return list;
	}

	@Override
	public GlobalItemPoint getGlobalItemPointById(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(GlobalItemPoint.class)
				.add(Restrictions.eq("id", id));
		List list = criteria.list();
		if(list.size()>0){
			return (GlobalItemPoint)list.get(0);
		}
		return null;
	}

	@Override
	public void save(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(point);
	}

	@Override
	public void delete(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(point);
	}

	@Override
	public void update(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(point);
	}

	@Override
	public GlobalItemPoint getByPointAndItem(Integer itemId,
			VisitWorkflowPoint point) {
		// TODO Auto-generated method stub
		List list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalItemPoint where itemId = ? and point = ? ")
				.setParameter(0, itemId)
				.setParameter(1, point).list();
		if(list.size()>0)
			return (GlobalItemPoint) list.get(0);
		return null;
	}

}
