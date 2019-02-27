package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.daoInterface.OrganisationTypeDao;
import org.calminfotech.system.models.OrganisationType;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@SuppressWarnings("unchecked")
@Repository
@Transactional
public class OrganisationTypeDaoImpl implements OrganisationTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private UserIdentity userIdentity;


	
	
	@Override
	public OrganisationType getOrganisationTypeById(int id) {
		// TODO Auto-generated method stub
		List list =  this.sessionFactory.getCurrentSession()
				.createQuery("FROM OrganisationType WHERE organisationTypeId = ? ")
				.setParameter(0, id).list();
				//.setParameter(1, userIdentity.getOrganisation().getId()).list();
		if(list.size()>0){
			return (OrganisationType)list.get(0);
		}
		return null;
	}

	@Override
	public void save(OrganisationType OrganisationType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(OrganisationType);
	}

	@Override
	public void delete(OrganisationType OrganisationType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(OrganisationType);
	}

	@Override
	public void update(OrganisationType OrganisationType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(OrganisationType);
	}

	@Override
	public List<OrganisationType> fetchAll() {
		// TODO Auto-generated method stub
		List<OrganisationType> list = sessionFactory.getCurrentSession()
				.createQuery("FROM OrganisationType")
				.list();
		return list;
	}

	@Override
	public List<OrganisationType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(OrganisationType.class)
			//	.createAlias("organisation", "organisation")
				//.add(Restrictions.eq("organisation.id", organisation.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		List list = criteria.list();
		return list;
	}

	@Override
	public List<OrganisationType> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
