package org.calminfotech.user.daoImpl;

import java.util.List;

import org.calminfotech.system.daoInterface.RoleAssgnmentDao;
import org.calminfotech.user.models.RoleAssignment;
import org.calminfotech.user.models.RoleAssignment_log;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleAssgnmentDaoImpl implements RoleAssgnmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public RoleAssignment getRoleAssgnmentById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from RoleAssgnment where roleId = ?")
				.setParameter(0, id).list();
		if (list.size() > 0) {
			return (RoleAssignment) list.get(0);
		}
		return null;
	}

	@Override
	public void save(RoleAssignment roleAssignment,RoleAssignment_log roleAssignmentlog) {
		this.sessionFactory.getCurrentSession().save(roleAssignment);
		
		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();
		
		
		this.sessionFactory.getCurrentSession().save(roleAssignmentlog);
		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();
	}

	@Override
	public List<RoleAssignment> deleteAllCheckedValues(Integer roleId) {
		// call stored procedure using native createSQLQuery() method
		try {
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetDeleteCheckedVal")
					.setParameter("roleid", roleId);
			List result = query.list();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}

		return null;
	}

	@Override
	public void save(RoleAssignment roleAssignment) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(roleAssignment);
	
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
	}

}
