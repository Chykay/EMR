package org.calminfotech.hrunit.daoImpl;

import java.util.List;

//import org.calminfotech.system.models.AllergyCategory;
import org.calminfotech.hrunit.daoInterface.StaffRegDao;
import org.calminfotech.hrunit.models.Hrunit;
import org.calminfotech.hrunit.models.StaffRegistration;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRegDaoImpl implements StaffRegDao  {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;


	@Override
	public StaffRegistration getStaffById(int id) {
		List<StaffRegistration> list = sessionFactory.getCurrentSession()
                .createQuery("from StaffRegistration where id = ? ")
                .setParameter(0, id).list();
			if(list.size() > 0)
			return list.get(0);
			return null;
	}
	
	@Override
	public StaffRegistration getStaffByUsernameId(String username) {
		
		List<StaffRegistration> list = sessionFactory.getCurrentSession()
                .createQuery("from StaffRegistration where email= ? ")
                .setParameter(0, username).list();
		System.out.print("daoooooooo");
		System.out.print(list.size());
		System.out.print(username);
		System.out.print("daooooooooo");
			if(list.size() > 0)
			return list.get(0);
			return null;
	}
	
	
	

	@Override
	public List<StaffRegistration> fetchAllByOrganisation(int organisationId) {
		
		
		if (userIdentity.getOrganisation().getId()==1)
		{
			List list = sessionFactory.getCurrentSession()
					.createSQLQuery("select * from Staff_Registration where email not in (select email from users) ")
					.addEntity(StaffRegistration.class)
					//.setParameter(0,organisationId)
					.list();
			
				return list;
			
		}
		else
		{
		List list = sessionFactory.getCurrentSession()
				.createQuery("from StaffRegistration where organisation_id = ? ")
				//.addEntity(StaffRegistration.class)
				.setParameter(0,organisationId)
				.list();
		
			return list;
	}
	}
	

	
	@Override
	public List<StaffRegistration> fetchAllByOrganisationNotIn(int organisationId) {
		
		if (userIdentity.getOrganisation().getId()==1)
		{
			List list = sessionFactory.getCurrentSession()
					.createSQLQuery("select * from Staff_Registration where email not in (select email from users) ")
					.addEntity(StaffRegistration.class)
				//	.setParameter(0,organisationId)
					.list();
			
				return list;
			
		}
		else
		{
		List list = sessionFactory.getCurrentSession()
				.createQuery(" from StaffRegistration where organisation_id = ? ")
				//.addEntity(StaffRegistration.class)
				.setParameter(0,organisationId)
				.list();
		
			return list;
		}
	}
	
	
	@Override
	public List<StaffRegistration> fetchTop50byOrganisation(int id) {
		
		if (userIdentity.getOrganisation().getId()==1)
		{
			List list = sessionFactory.getCurrentSession()
					.createSQLQuery("select * from Staff_Registration where email not in (select email from users) order by id desc")
					.addEntity(StaffRegistration.class)
					 .setMaxResults(50)
					//.setParameter(0,organisationId)
					.list();
			
				return list;
		}
		else
		{
		
		List<StaffRegistration> list = sessionFactory.getCurrentSession()
			   .createQuery("from StaffRegistration  where organisation.Id = ?  ORDER BY id DESC")
			 .setParameter(0,id)
			  .setMaxResults(50)
				   .list();
	
		return  list;
		
	}

	
	
	
	}
	@Override
	public void save(StaffRegistration staffRegistration) {
		this.sessionFactory.getCurrentSession().save(staffRegistration);
	}

	@Override
	public void delete(StaffRegistration staffRegistration) {
		this.sessionFactory.getCurrentSession().delete(staffRegistration);
	}

	@Override
	public void update(StaffRegistration staffRegistration) {
	this.sessionFactory.getCurrentSession().update(staffRegistration);
	}

	@Override
	public List<StaffRegistration> fetchStaffByUnitId(int unitId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from StaffRegistration where unit = ? AND status = 'active'")
				.setParameter(0,unitId).list();
		
			return list;
	}


	
}
