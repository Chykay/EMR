package org.calminfotech.patient.daoImpl;

import java.util.List;
import org.calminfotech.patient.daoInterface.PatientHmoDao;
import org.calminfotech.patient.models.PatientHmo;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientHmoDaoImpl implements PatientHmoDao {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	/*@Override
	public List<PatientHmo> fetchAll() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientHmoPackage.class);

		List list = criteria.list();

		return list;
	}*/
	@Override	
	public List<PatientHmo> fetchAll() {
		//System.out.println("This is HMO DAO for fetch All method");
		List<PatientHmo> list = this.sessionFactory.getCurrentSession()
			.createQuery("FROM PatientHmo").list();
		return list;
	}

	@Override
	public PatientHmo getPatientHmoById(int id) {
		// TODO Auto-generated method stub
		List<PatientHmo> list = this.sessionFactory.getCurrentSession()
				.createQuery("from PatientHmo where Id = ? ").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	@Override
	public void save(PatientHmo patientHmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientHmo);
	}

	@Override
	public void delete(PatientHmo patientHmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(patientHmo);
	}

	@Override
	public void update(PatientHmo patientHmo) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(patientHmo);
	}

}
