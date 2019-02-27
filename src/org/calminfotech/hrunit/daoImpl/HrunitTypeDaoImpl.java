package org.calminfotech.hrunit.daoImpl;

import java.util.List;

import org.calminfotech.hrunit.daoInterface.HrunitTypeDao;
import org.calminfotech.hrunit.models.HrunitType;
import org.calminfotech.system.models.Organisation;
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
public class HrunitTypeDaoImpl implements HrunitTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private UserIdentity userIdentity;


	
	
	@Override
	public HrunitType getHrunitTypeById(int id) {
		// TODO Auto-generated method stub
		List list =  this.sessionFactory.getCurrentSession()
				.createQuery("FROM HrunitType WHERE hrunitTypeId = ? ")
				.setParameter(0, id).list();
				//.setParameter(1, userIdentity.getOrganisation().getId()).list();
		if(list.size()>0){
			return (HrunitType)list.get(0);
		}
		return null;
	}

	@Override
	public void save(HrunitType HrunitType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(HrunitType);
	}

	@Override
	public void delete(HrunitType HrunitType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(HrunitType);
	}

	@Override
	public void update(HrunitType HrunitType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(HrunitType);
	}

	@Override
	public List<HrunitType> fetchAll() {
		// TODO Auto-generated method stub
		List<HrunitType> list = sessionFactory.getCurrentSession()
				.createQuery("FROM HrunitType").list();
		return list;
	}

	@Override
	public List<HrunitType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(HrunitType.class)
			//	.createAlias("organisation", "organisation")
				//.add(Restrictions.eq("organisation.id", organisation.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		List list = criteria.list();
		return list;
	}

	@Override
	public List<HrunitType> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
