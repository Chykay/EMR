package org.calminfotech.patient.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.patient.daoInterface.PatientFamilyHistoryDao;
import org.calminfotech.patient.daoInterface.PatientAllergyDao;
import org.calminfotech.patient.models.PatientFamilyHistory;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientAllergyDaoImpl implements PatientAllergyDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientAllergy> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientAllergy.class)
				.add(Restrictions.eq("patient", "patient"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		
	}

	@Override
	public void save(PatientAllergy patientAllergy) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientAllergy);
	}

	@Override
	public PatientAllergy getPatientAllergyById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientAllergy WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientAllergy) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientAllergy patientAllergy) {
		this.sessionFactory.getCurrentSession().delete(patientAllergy);
	}

	@Override
	public void update(PatientAllergy patientAllergy) {
		this.sessionFactory.getCurrentSession().update(patientAllergy);		
	}
	
	
	
	

}
