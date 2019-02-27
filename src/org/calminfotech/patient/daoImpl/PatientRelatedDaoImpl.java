package org.calminfotech.patient.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.patient.daoInterface.PatientRelatedDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientRelated;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PatientRelatedDaoImpl implements PatientRelatedDao {
	
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	/*@Override
	public List<PatientRelated> fetchAll() {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientRelated.class);
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}*/
	
	@Override
	public List<PatientRelated> fetchAll(Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria("from PatientRelated where organisation = ? and status = 'active'")
				.addOrder(Order.asc("createDate"));
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

	@Override
	public List<PatientRelated> fetchAllByOrganisation(Organisation organisation) {
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientRelated.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.addOrder(Order.asc("createDate")).list();
		return list;
	}

	
	
	
	/*
	 * @Override
	public PatientRelated getRelPatientById(int id) {
		

		System.out.println("Related Patient Id: " +id);
		
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientRelated WHERE relPatient = ?").setParameter(0, id)
				.list();
		System.out.println("list size: " +list.size());
		if (list.size() > 0)
			return (PatientRelated) list.get(0);
		return null;
	}
	*/

	
	
	@Override
	public void save(PatientRelated relPatient) {
		this.sessionFactory.getCurrentSession().save(relPatient);
	}

	@Override
	public void delete(PatientRelated relPatient) {
		this.sessionFactory.getCurrentSession().delete(relPatient);
	}

	@Override
	public void update(PatientRelated relPatient) {
		this.sessionFactory.getCurrentSession().update(relPatient);
	}

	@Override
	public PatientRelated checkIfPatientIdExist(String patientId) {
		List<PatientRelated> list = sessionFactory
				.getCurrentSession()
				.createQuery("from PatientRelated where relPatientId = ? ")
				.setParameter(0, patientId).list();
if (list.size() > 0)
return list.get(0);
return null;
	}


	
	

	@Override
	public PatientRelated getRelPatientById(int id) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from PatientRelated where id = ? ")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (PatientRelated)list.get(0);
		
		return null;
	}

	@Override
	public PatientRelated getPatientRelatedByPatientId(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}


}
