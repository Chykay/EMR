package org.calminfotech.system.daoImpl;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.daoInterface.ExaminationTypeDao;
import org.calminfotech.system.models.ExaminationType;
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
public class ExaminationTypeDaoImpl implements ExaminationTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private UserIdentity userIdentity;


	
	
	@Override
	public ExaminationType getExaminationTypeById(int id) {
		// TODO Auto-generated method stub
		List list =  this.sessionFactory.getCurrentSession()
				.createQuery("FROM ExaminationType WHERE examinationTypeId = ? ")
				.setParameter(0, id).list();
				//.setParameter(1, userIdentity.getOrganisation().getId()).list();
		if(list.size()>0){
			return (ExaminationType)list.get(0);
		}
		return null;
	}

	@Override
	public void save(ExaminationType ExaminationType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(ExaminationType);
	}

	@Override
	public void delete(ExaminationType ExaminationType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(ExaminationType);
	}

	@Override
	public void update(ExaminationType ExaminationType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(ExaminationType);
	}

	@Override
	public List<ExaminationType> fetchAll() {
		// TODO Auto-generated method stub
		List<ExaminationType> list = sessionFactory.getCurrentSession()
				.createQuery("FROM ExaminationType").list();
		return list;
	}

	@Override
	public List<ExaminationType> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ExaminationType.class)
			//	.createAlias("organisation", "organisation")
				//.add(Restrictions.eq("organisation.id", organisation.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
		List list = criteria.list();
		return list;
	}

	@Override
	public List<ExaminationType> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
