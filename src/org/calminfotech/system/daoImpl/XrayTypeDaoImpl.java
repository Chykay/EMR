package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.daoInterface.XrayTypeDao;
import org.calminfotech.system.models.XrayType;
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
public class XrayTypeDaoImpl implements XrayTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private UserIdentity userIdentity;


	
	
	@Override
	public XrayType getXrayTypeById(int id) {
		// TODO Auto-generated method stub
		List list =  this.sessionFactory.getCurrentSession()
				.createQuery("FROM XrayType WHERE xrayTypeId = ? ")
				.setParameter(0, id).list();
				//.setParameter(1, userIdentity.getOrganisation().getId()).list();
		if(list.size()>0){
			return (XrayType)list.get(0);
		}
		return null;
	}

	@Override
	public void save(XrayType XrayType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(XrayType);
	}

	@Override
	public void delete(XrayType XrayType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(XrayType);
	}

	@Override
	public void update(XrayType XrayType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(XrayType);
	}

	@Override
	public List<XrayType> fetchAll() {
		// TODO Auto-generated method stub
		List<XrayType> list = sessionFactory.getCurrentSession()
				.createQuery("FROM XrayType").list();
		return list;
	}

	@Override
	public List<XrayType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(XrayType.class)
			//	.createAlias("organisation", "organisation")
				//.add(Restrictions.eq("organisation.id", organisation.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		List list = criteria.list();
		return list;
	}

	@Override
	public List<XrayType> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
