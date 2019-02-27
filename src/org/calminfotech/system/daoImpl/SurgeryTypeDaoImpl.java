package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.daoInterface.SurgeryTypeDao;
import org.calminfotech.system.models.SurgeryType;
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
public class SurgeryTypeDaoImpl implements SurgeryTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private UserIdentity userIdentity;


	
	
	@Override
	public SurgeryType getSurgeryTypeById(int id) {
		// TODO Auto-generated method stub
		List list =  this.sessionFactory.getCurrentSession()
				.createQuery("FROM SurgeryType WHERE surgeryTypeId = ? ")
				.setParameter(0, id).list();
				//.setParameter(1, userIdentity.getOrganisation().getId()).list();
		if(list.size()>0){
			return (SurgeryType)list.get(0);
		}
		return null;
	}

	@Override
	public void save(SurgeryType SurgeryType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(SurgeryType);
	}

	@Override
	public void delete(SurgeryType SurgeryType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(SurgeryType);
	}

	@Override
	public void update(SurgeryType SurgeryType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(SurgeryType);
	}

	@Override
	public List<SurgeryType> fetchAll() {
		// TODO Auto-generated method stub
		List<SurgeryType> list = sessionFactory.getCurrentSession()
				.createQuery("FROM SurgeryType").list();
		return list;
	}

	@Override
	public List<SurgeryType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(SurgeryType.class)
			//	.createAlias("organisation", "organisation")
				//.add(Restrictions.eq("organisation.id", organisation.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		List list = criteria.list();
		return list;
	}

	@Override
	public List<SurgeryType> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
