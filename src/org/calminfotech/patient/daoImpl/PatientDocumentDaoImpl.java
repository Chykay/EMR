package org.calminfotech.patient.daoImpl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientDocumentDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.system.models.Organisation;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientDocumentDaoImpl implements PatientDocumentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientDocument> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientDocument.class).list();
	}

	@Override
	public List<PatientDocument> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientDocument.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@Override
	public List<PatientDocument> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientDocument.class)
				.createAlias("patient", "patient")

				.add(Restrictions.eq("patient.id", patient.getPatientId()))
				.list();
	}

	@Override
	public void update(PatientDocument patientDocument) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(patientDocument);
	}

	@Override
	public void save(PatientDocument patientDocument) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientDocument);
	}

	@Override
	public PatientDocument getPatientDocumentById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientDocument WHERE id = ? ")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientDocument) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientDocument patientDocument) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(patientDocument);
	}

}
