package org.calminfotech.patient.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.patient.daoInterface.PatientPaymentOptionDao;
import org.calminfotech.patient.models.PatientFamilyHistory;
import org.calminfotech.patient.models.PatientPaymentOption;
import org.calminfotech.patient.models.Patient;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class PatientPaymentOptionDaoImpl implements PatientPaymentOptionDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PatientPaymentOption> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientPaymentOption.class).list();
	}

	@Override
	public List<PatientPaymentOption> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientPaymentOption.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@Override
	public List<PatientPaymentOption> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientPaymentOption.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.id", patient.getPatientId())).list();
	}

	@Override
	public void save(PatientPaymentOption patientPaymentOption) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientPaymentOption);
	}

	@Override
	public PatientPaymentOption getPatientPaySchemeById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientPaymentOption WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientPaymentOption) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientPaymentOption patientPaymentOption) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(patientPaymentOption);
	}

}
