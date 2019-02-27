package org.calminfotech.patient.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.patient.daoInterface.PatientFamilyHistoryDao;
import org.calminfotech.patient.daoInterface.PatientTelephoneDao;
import org.calminfotech.patient.models.PatientFamilyHistory;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientTelephone;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientTelephoneDaoImpl implements PatientTelephoneDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientTelephone> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientTelephone.class)
				.add(Restrictions.eq("patient", "patient"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		
	}

	@Override
	public void save(PatientTelephone patientTelephone) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientTelephone);
	}

	@Override
	public PatientTelephone getPatientTelephoneById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientTelephone WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientTelephone) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientTelephone patientTelephone) {
		this.sessionFactory.getCurrentSession().delete(patientTelephone);
	}

	@Override
	public void update(PatientTelephone patientTelephone) {
		this.sessionFactory.getCurrentSession().update(patientTelephone);		
	}
	
	
	
	

}
