package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.daoInterface.DiseasesTypeDao;
import org.calminfotech.system.models.DiseasesType;
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
public class DiseasesTypeDaoImpl implements DiseasesTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private UserIdentity userIdentity;


	
	
	@Override
	public DiseasesType getDiseasesTypeById(int id) {
		// TODO Auto-generated method stub
		List list =  this.sessionFactory.getCurrentSession()
				.createQuery("FROM DiseasesType WHERE diseasesTypeId = ? ")
				.setParameter(0, id).list();
				//.setParameter(1, userIdentity.getOrganisation().getId()).list();
		if(list.size()>0){
			return (DiseasesType)list.get(0);
		}
		return null;
	}

	@Override
	public void save(DiseasesType DiseasesType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(DiseasesType);
	}

	@Override
	public void delete(DiseasesType DiseasesType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(DiseasesType);
	}

	@Override
	public void update(DiseasesType DiseasesType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(DiseasesType);
	}

	@Override
	public List<DiseasesType> fetchAll() {
		// TODO Auto-generated method stub
		List<DiseasesType> list = sessionFactory.getCurrentSession()
				.createQuery("FROM DiseasesType").list();
		return list;
	}

	@Override
	public List<DiseasesType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(DiseasesType.class)
			//	.createAlias("organisation", "organisation")
				//.add(Restrictions.eq("organisation.id", organisation.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		List list = criteria.list();
		return list;
	}

	@Override
	public List<DiseasesType> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
