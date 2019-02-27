package org.calminfotech.user.daoImpl;

import java.util.List;

import org.calminfotech.user.daoInterface.UserAssgnmentDao;
import org.calminfotech.user.models.UserAssignment;
import org.calminfotech.user.models.UserAssignment_log;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserAssgnmentDaoImpl implements UserAssgnmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserAssignment getUserAssgnmentById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserAssgnment where userId = ?")
				.setParameter(0, id).list();
		if (list.size() > 0) {
			return (UserAssignment) list.get(0);
		}
		return null;
	}

	@Override
	public void save(UserAssignment userAssignment) {
		this.sessionFactory.getCurrentSession().save(userAssignment);
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
	}

	@Override
	public List<UserAssignment> deleteAllUserCheckedValues(Integer userId) {
		// call stored procedure using native createSQLQuery() method
		try {
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetDeleteUserCheckedVal")
					.setParameter("userid", userId);
			List result = query.list();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		System.out.println("Error for parameters is: " + ex.getMessage());
		}

		return null;
	}

	@Override
	public void save(UserAssignment userAssignment,
			UserAssignment_log userAssignment_log) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(userAssignment);
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
		this.sessionFactory.getCurrentSession().save(userAssignment_log);
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
	}

	

}
