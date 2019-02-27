package org.calminfotech.user.daoImpl;

import java.util.List;

import org.calminfotech.user.daoInterface.SecurityQuestionDao;
import org.calminfotech.user.models.SecurityQuestion;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SecurityQuestionDaoImpl extends CustomHibernateDaoSupport
		implements SecurityQuestionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(SecurityQuestion securityQuestion) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(securityQuestion);
	}

	@Override
	public void update(SecurityQuestion securityQuestion) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(securityQuestion);
	}

	@Override
	public void delete(SecurityQuestion securityQuestion) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(securityQuestion);
	}

	@Override
	public SecurityQuestion getQuestionById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from SecurityQuestion where id = ?")
				.setParameter(0, id).list();
		
		if (list.size() > 0)
			return (SecurityQuestion) list.get(0);
		return null;
	}

	@Override
	public List<SecurityQuestion> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from SecurityQuestion").list();
		return (List<SecurityQuestion>) list;
	}

}
