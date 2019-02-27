package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.ConsultationStatusDao;
import org.calminfotech.system.models.ConsultationStatus;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.patient.models.Patient;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ConsultationStatusDaoImpl implements ConsultationStatusDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ConsultationStatus> fetchAll() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(ConsultationStatus.class);
		List<ConsultationStatus> list = criteria.list();
		return list;
	}

	@Override
	public List<ConsultationStatus> fetchAllByOrganisation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(ConsultationStatus.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));
		List <ConsultationStatus> list = criteria.list();
		return list;
	}

	@Override
	public ConsultationStatus getStatusById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM ConsultationStatus WHERE id = ?").setParameter(0, id)
				.list();

		if (list.size() > 0)
			return (ConsultationStatus) list.get(0);

		return null;
	}

	@Override
	public void save(ConsultationStatus consultationStatus) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(consultationStatus);
	}

	@Override
	public void delete(ConsultationStatus consultationStatus) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(consultationStatus);
	}

	@Override
	public void update(ConsultationStatus consultationStatus) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(consultationStatus);
	}

	@Override
	public ConsultationStatus getStartPointStatus(Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(ConsultationStatus.class)
				.add(Restrictions.eq("startPoint", true))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));
		
		List <ConsultationStatus> list = criteria.list();

		if (list.size() > 0)
			return (ConsultationStatus) list.get(0);

		return null;
	}

	@Override
	public ConsultationStatus getEndPointStatus(Organisation organisation) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(ConsultationStatus.class)
				.add(Restrictions.eq("endPoint", true))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		           
		List <ConsultationStatus> list = criteria.list();
		if (list.size() > 0)
			return (ConsultationStatus) list.get(0);

		return null;
	}

}
