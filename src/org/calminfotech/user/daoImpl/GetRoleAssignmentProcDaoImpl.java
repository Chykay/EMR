package org.calminfotech.user.daoImpl;

import java.util.List;

import org.calminfotech.user.daoInterface.GetRoleAssignmentProcDao;
import org.calminfotech.user.models.GetRoleAssignmentProc;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GetRoleAssignmentProcDaoImpl implements GetRoleAssignmentProcDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<GetRoleAssignmentProc> fetchRolePermission(Integer roleId,Integer orgId) {
		// call stored procedure using native createSQLQuery() method
		try{
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetRoleAssignment")
					.setParameter("roleid", roleId)
			
					.setParameter("orgid", orgId);
			
			List result = query.list();
			
			return result;
		}
		catch(Exception ex){
			ex.printStackTrace();
			//sessionFactory.getCurrentSession().beginTransaction().rollback();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}
		
		return null;
	}
}
