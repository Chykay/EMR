package org.calminfotech.patient.daoImpl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientDaoImpl implements PatientDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<Patient> fetchAll(int start) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(Patient.class)
				.add(Restrictions.eq("organisation.id", userIdentity
						.getOrganisation().getId())).addOrder(Order.desc("id"))
				.setFirstResult(start).setMaxResults(3);
		List list = criteria
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return list;
	}

	/*
	 * @Override public List<Patient> fetchAllByOrganisation(Organisation
	 * organisation) { // TODO Auto-generated method stub Criteria criteria =
	 * this.sessionFactory.getCurrentSession() .createCriteria(Patient.class)
	 * //.createAlias("organisation", "organisation")
	 * .add(Restrictions.eq("organisation.id", organisation.getId()))
	 * .addOrder(Order.desc("patientId"));
	 * 
	 * .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
	 * criteria.setFirstResult(0); criteria.setMaxResults(Integer.MAX_VALUE);
	 * return
	 * criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setMaxResults
	 * (300).list(); }
	 */

	@Override
	public List<Patient> fetchAllByOrganisation(int organisationId) {
		// System.out.println("name");
		List<Patient> list = sessionFactory.getCurrentSession()
				.createQuery("from Patient where organisation_id = ? ")
				// .setMaxResults(50)

				.setParameter(0, organisationId).list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public List<Patient> fetchTop50ByOrganisation(int organisationId) {
		// System.out.println("name");
		List<Patient> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Patient where organisation.orgCoy.Id = ?  ORDER BY patientId DESC")
				.setMaxResults(50)

				.setParameter(0, organisationId).list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public List<Patient> fetchByOrganisationrec(int organisationId) {
		// System.out.println("name");
		List<Patient> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Patientrec where organisation.orgCoy.Id = ?  ORDER BY patientId DESC")
				.setParameter(0, organisationId).list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public List<Patient> fetchByOrganisationrecbypatient(int pid) {
		// System.out.println("name");
		List<Patient> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Patientrec where patient.patientId = ?  ORDER BY patientId DESC")
				.setParameter(0, pid).list();
		// System.out.println(list.get(0).getHmoId());
		return list;
	}

	@Override
	public Patient getPatientById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Patient WHERE patientId = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (Patient) list.get(0);

		return null;
	}

	@Override
	public void save(Patient patient) {
		this.sessionFactory.getCurrentSession().save(patient);
	}

	@Override
	public void delete(Patient patient) {
		this.sessionFactory.getCurrentSession().delete(patient);

	}

	@Override
	public void update(Patient patient) {
		this.sessionFactory.getCurrentSession().update(patient);
	}

	@Override
	public Patient findByBirthDay(String patientDob) {
		List<Patient> list = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Patient where patientId = (select max(id) from Patient where dob = ?  ) ")
				.setParameter(0, DateUtils.formatStringToDate(patientDob))
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public Patient checkIfPatientIdExist(String patientId) {

		List<Patient> list = sessionFactory.getCurrentSession()
				.createQuery("from Patient where patientCode = ? ")
				.setParameter(0, patientId).list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

}
