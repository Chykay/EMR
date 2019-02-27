package org.calminfotech.patient.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.patient.daoInterface.CasualtyPatientDao;
import org.calminfotech.patient.models.CasPatient;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CasualtyPatientDaoImpl implements CasualtyPatientDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CasPatient> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(CasPatient.class)
				.addOrder(Order.asc("createdDate")).list();
		return list;
	}

	@Override
	public List<CasPatient> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(CasPatient.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.addOrder(Order.asc("createdDate")).list();

		return list;
	}

	@Override
	public CasPatient getCasPatientById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(CasPatient.class)
				.add(Restrictions.eq("id", id)).list();

		if (list.size() > 0)
			return (CasPatient) list.get(0);

		return null;
	}

	@Override
	public void save(CasPatient casPatient) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(casPatient);
	}

	@Override
	public void delete(CasPatient casPatient) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(casPatient);
	}

	@Override
	public void update(CasPatient casPatient) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(casPatient);
	}

}
