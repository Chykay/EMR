package org.calminfotech.hrunit.daoImpl;

import java.util.List;

import org.calminfotech.hrunit.daoInterface.ClockinDao;
//import org.calminfotech.hrunit.daoInterface.ClockinLog
import org.calminfotech.hrunit.models.ClockAssignment;
import org.calminfotech.hrunit.models.ClockinLog;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ClockinDaoImpl implements ClockinDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ClockAssignment getClockinAssgnmentById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from ClockAssignment where userId = ?")
				.setParameter(0, id).list();
		if (list.size() > 0) {
			return (ClockAssignment) list.get(0);
		}
		return null;
	}

	@Override
	public void save(ClockAssignment clockAssignment) {
		System.out.println("inside save");
		this.sessionFactory.getCurrentSession().save(clockAssignment);
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
	}

	@Override 
	public List<ClockAssignment> deleteAllCheckedValues(Integer userId) {
		try {
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetDeleteClockingCheckedVal")
					.setParameter("userid", userId);
			List result = query.list();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}

		return null;
	}


	/*@Override
	public ClockAssignment getClockinById(int id) {
		List<ClockAssignment> list = this.sessionFactory.getCurrentSession()
				.createQuery("from ClockAssignment where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<ClockAssignment> fetchAllByOrganisation(int organisationId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from ClockAssignment where organisationId = ?")
				.setParameter(0,organisationId).list();
		
			return list;
	}

	

	@Override
	public ClockAssignment getClockinByUnitId(int unitId) {
		List<ClockAssignment> list = this.sessionFactory.getCurrentSession()
				.createQuery("from ClockAssignment where unitId = ?").setParameter(0, unitId)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}*/
	@Override
	public List<ClockAssignment> fetchAllByUnitId(int unitId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from ClockAssignment where unitId = ? ")
				.setParameter(0,unitId).list();
		
			return list;
	}

	@Override
	public List<ClockAssignment> fetchAllByUserId(int userid) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from ClockAssignment where user_id = ? ")
				.setParameter(0,userid).list();
		
			return list;
	}

	
	@Override
	public void save(ClockinLog clocklog) {
		System.out.println("inside save");
		this.sessionFactory.getCurrentSession().save(clocklog);
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
		
	}


}
