package org.calminfotech.patient.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.patient.daoInterface.PatientFamilyHistoryDao;
import org.calminfotech.patient.daoInterface.PatientAddressDao;
import org.calminfotech.patient.models.PatientFamilyHistory;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAddress;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientAddressDaoImpl implements PatientAddressDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientAddress> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientAddress.class)
				.add(Restrictions.eq("patient", "patient"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		
	}

	@Override
	public void save(PatientAddress patientAddress) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientAddress);
	}

	@Override
	public PatientAddress getPatientAddressById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientAddress WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientAddress) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientAddress patientAddress) {
		this.sessionFactory.getCurrentSession().delete(patientAddress);
	}

	@Override
	public void update(PatientAddress patientAddress) {
		this.sessionFactory.getCurrentSession().update(patientAddress);		
	}
	
	
	
	

}
