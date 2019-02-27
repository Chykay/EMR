package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.daoInterface.AllergyTypeDao;
import org.calminfotech.system.models.AllergyType;
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
public class AllergyTypeDaoImpl implements AllergyTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private UserIdentity userIdentity;


	
	
	@Override
	public AllergyType getAllergyTypeById(int id) {
		// TODO Auto-generated method stub
		List list =  this.sessionFactory.getCurrentSession()
				.createQuery("FROM AllergyType WHERE allergyTypeId = ? ")
				.setParameter(0, id).list();
				//.setParameter(1, userIdentity.getOrganisation().getId()).list();
		if(list.size()>0){
			return (AllergyType)list.get(0);
		}
		return null;
	}

	@Override
	public void save(AllergyType AllergyType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(AllergyType);
	}

	@Override
	public void delete(AllergyType AllergyType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(AllergyType);
	}

	@Override
	public void update(AllergyType AllergyType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(AllergyType);
	}

	@Override
	public List<AllergyType> fetchAll() {
		// TODO Auto-generated method stub
		List<AllergyType> list = sessionFactory.getCurrentSession()
				.createQuery("FROM AllergyType").list();
		return list;
	}

	@Override
	public List<AllergyType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(AllergyType.class)
			//	.createAlias("organisation", "organisation")
				//.add(Restrictions.eq("organisation.id", organisation.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		List list = criteria.list();
		return list;
	}

	@Override
	public List<AllergyType> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
