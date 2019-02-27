package org.calminfotech.patient.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;

import org.calminfotech.patient.daoInterface.PatientFamilyHistoryDao;
import org.calminfotech.patient.daoInterface.PatientHistoryDao;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHistory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientHistoryDaoImpl implements PatientHistoryDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientHistory> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientHistory.class)
				.add(Restrictions.eq("patient", "patient"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		
	}

	@Override
	public void save(PatientHistory patientHistory) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientHistory);
	}

	@Override
	public PatientHistory getPatientHistoryById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientHistory WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientHistory) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientHistory patientHistory) {
		this.sessionFactory.getCurrentSession().delete(patientHistory);
	}

	@Override
	public void update(PatientHistory patientHistory) {
		this.sessionFactory.getCurrentSession().update(patientHistory);		
	}
	
	
	
	

}
