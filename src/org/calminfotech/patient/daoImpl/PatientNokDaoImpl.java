package org.calminfotech.patient.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.patient.daoInterface.PatientNokDao;
import org.calminfotech.patient.daoInterface.PatientNokDao;
import org.calminfotech.patient.models.PatientNok;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientNok;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientNokDaoImpl implements PatientNokDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientNok> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientNok.class)
				.add(Restrictions.eq("patient", "patient"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		
	}

	@Override
	public void save(PatientNok patientNok) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientNok);
	}

	@Override
	public PatientNok getPatientNokById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientNok WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientNok) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientNok patientNok) {
		this.sessionFactory.getCurrentSession().delete(patientNok);
	}

	@Override
	public void update(PatientNok patientNok) {
		this.sessionFactory.getCurrentSession().update(patientNok);		
	}
	
	
	
	

}
