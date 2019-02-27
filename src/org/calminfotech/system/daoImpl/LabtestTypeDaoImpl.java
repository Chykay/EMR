package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.daoInterface.LabtestTypeDao;
import org.calminfotech.system.models.LabtestType;
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
public class LabtestTypeDaoImpl implements LabtestTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private UserIdentity userIdentity;


	
	
	@Override
	public LabtestType getLabtestTypeById(int id) {
		// TODO Auto-generated method stub
		List list =  this.sessionFactory.getCurrentSession()
				.createQuery("FROM LabtestType WHERE labtestTypeId = ? ")
				.setParameter(0, id).list();
				//.setParameter(1, userIdentity.getOrganisation().getId()).list();
		if(list.size()>0){
			return (LabtestType)list.get(0);
		}
		return null;
	}

	@Override
	public void save(LabtestType LabtestType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(LabtestType);
	}

	@Override
	public void delete(LabtestType LabtestType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(LabtestType);
	}

	@Override
	public void update(LabtestType LabtestType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(LabtestType);
	}

	@Override
	public List<LabtestType> fetchAll() {
		// TODO Auto-generated method stub
		List<LabtestType> list = sessionFactory.getCurrentSession()
				.createQuery("FROM LabtestType").list();
		return list;
	}

	@Override
	public List<LabtestType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(LabtestType.class)
			//	.createAlias("organisation", "organisation")
				//.add(Restrictions.eq("organisation.id", organisation.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		List list = criteria.list();
		return list;
	}

	@Override
	public List<LabtestType> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
