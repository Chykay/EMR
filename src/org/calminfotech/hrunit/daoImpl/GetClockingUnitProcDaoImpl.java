package org.calminfotech.hrunit.daoImpl;

import java.util.List;

import org.calminfotech.hrunit.daoInterface.GetClockingUnitProcDao;
import org.calminfotech.hrunit.models.GetClockingUnitProc;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class GetClockingUnitProcDaoImpl implements GetClockingUnitProcDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	
	@Override
	public List<GetClockingUnitProc> fetchClockinUnit(Integer userId) {
		try {
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetClockingUnitProc")
					.setParameter("userid", userId)
					.setParameter("orgid", userIdentity.getOrganisation().getId());
			
			List result = query.list();
			System.out.println("clocking size is" + result.size());
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("error msg" + ex.getMessage());

			//sessionFactory.getCurrentSession().beginTransaction().rollback();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}

		return null;
	}


}
