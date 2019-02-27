package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.daoInterface.BedTypeDao;
import org.calminfotech.system.models.BedType;
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
public class BedTypeDaoImpl implements BedTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private UserIdentity userIdentity;


	
	
	@Override
	public BedType getBedTypeById(int id) {
		// TODO Auto-generated method stub
		List list =  this.sessionFactory.getCurrentSession()
				.createQuery("FROM BedType WHERE bedTypeId = ? ")
				.setParameter(0, id).list();
				//.setParameter(1, userIdentity.getOrganisation().getId()).list();
		if(list.size()>0){
			return (BedType)list.get(0);
		}
		return null;
	}

	@Override
	public void save(BedType BedType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(BedType);
	}

	@Override
	public void delete(BedType BedType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(BedType);
	}

	@Override
	public void update(BedType BedType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(BedType);
	}

	@Override
	public List<BedType> fetchAll() {
		// TODO Auto-generated method stub
		List<BedType> list = sessionFactory.getCurrentSession()
				.createQuery("FROM BedType").list();
		return list;
	}

	@Override
	public List<BedType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(BedType.class)
			//	.createAlias("organisation", "organisation")
				//.add(Restrictions.eq("organisation.id", organisation.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		List list = criteria.list();
		return list;
	}

	@Override
	public List<BedType> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
