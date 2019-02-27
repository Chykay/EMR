package org.calminfotech.visitqueue.daoImpl;

import java.util.List;

import org.calminfotech.visitqueue.daoInterface.VisitTimelineDao;
import org.calminfotech.visitqueue.models.Visit;
import org.calminfotech.visitqueue.models.VisitTimeline;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VisitTimelineDaoImpl implements VisitTimelineDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<VisitTimeline> fetchAllByOrganisation(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(VisitTimeline.class)
				.add(Restrictions.eq("organisation.id", id));
						
		List list = criteria.list();
		return list;
	}
	
	
	@Override
	public List<VisitTimeline> fetchByVisitId(int visitid) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(VisitTimeline.class)
				.add(Restrictions.eq("visit.id", visitid));
						
		List list = criteria.list();
		return list;
	}
	

	@Override
	public VisitTimeline getVisitationById(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(VisitTimeline.class).add(Restrictions.eq("id", id));

		List list = criteria.list();

		if (list.size() > 0)
			return (VisitTimeline) list.get(0);

		return null;
	}

	@Override
	public void save(VisitTimeline timeline) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(timeline);
		
	}

	@Override
	public void delete(VisitTimeline timeline) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(timeline);
	}

	@Override
	public void update(VisitTimeline timeline) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(timeline);
	}



			
			
	
}
