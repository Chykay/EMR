package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.PointDao;
import org.calminfotech.hrunit.daoInterface.HrunitCategoryDao;
import org.calminfotech.system.models.Point;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public class PointDaoImpl implements PointDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;


	@Override
	
		public List<Point> fetchAllPoint() {
			List list = sessionFactory.getCurrentSession()
					.createQuery("from Point").list();
			
			return (List<Point>)list;
	}

	@Override
	public List<Point> getPointByUnitId(int id) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from Point where unit = ? ")
				.setParameter(0, id).list();
		return list;
	}

	@Override
	public Point getPointById(Integer id) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Point.class).add(Restrictions.eq("id", id));

		List list = criteria.list();

		if (list.size() > 0)
			return (Point) list.get(0);

		return null;
	}

	@Override
	public void save(Point point) {
		this.sessionFactory.getCurrentSession().save(point);
	}

	@Override
	public void update(Point point) {
		this.sessionFactory.getCurrentSession().update(point);
	}

	@Override
	public void delete(Point point) {
		this.sessionFactory.getCurrentSession().delete(point);
	}

	
	@Override
	public List<Point> getPointByUnitId1(HrunitCategory unit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Point> getPointByUnitId(HrunitCategory unit) {
		// TODO Auto-generated method stub
		return null;
	}

}
