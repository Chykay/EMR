package org.calminfotech.visitqueue.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Diseases;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.visitqueue.daoInterface.VisitStatusDao;
import org.calminfotech.visitqueue.models.VisitStatus;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VisitStatusDaoImpl implements VisitStatusDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<VisitStatus> fetchAll() {
		// TODO Auto-generated method stub
	
		List<VisitStatus> list = sessionFactory.getCurrentSession()
				   .createQuery("from VisitStatus  where is_deleted=false")
				 
				   .list();
				 return list;  
	}

	@Override
	public List<VisitStatus> fetchAll(Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(VisitStatus.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));
		return criteria.list();
	}

	@Override
	public VisitStatus getStatusById(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(VisitStatus.class)
				.add(Restrictions.eq("id", id));

		List list = criteria.list();
		if (list.size() > 0)
			return (VisitStatus) list.get(0);
		return null;
	}

	@Override
	public void save(VisitStatus status) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(status);
	}

	@Override
	public void delete(VisitStatus status) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(status);
	}

	@Override
	public void update(VisitStatus status) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(status);
	}

	@Override
	public VisitStatus getStartPointStatus(Organisation organisation) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(VisitStatus.class)
				.add(Restrictions.eq("isStartPoint", true))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));

		List<VisitStatus> list = criteria.list();

		if (list.size() > 0)
			return (VisitStatus) list.get(0);

		return null;
	}

	@Override
	public VisitStatus getEndPointStatus(Organisation organisation) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(VisitStatus.class)
				.add(Restrictions.eq("isEndPoint", true))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));

		List<VisitStatus> list = criteria.list();

		if (list.size() > 0)
			return (VisitStatus) list.get(0);
		return null;
	}

	@Override
	public VisitStatus getNotEndPointStatus(Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(VisitStatus.class)
				.add(Restrictions.eq("isEndPoint", false))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));

		List<VisitStatus> list = criteria.list();

		if (list.size() > 0)
			return (VisitStatus) list.get(0);
		return null;
	}

}
