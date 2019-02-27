package org.calminfotech.user.daoImpl;

import java.util.List;

import org.calminfotech.user.daoInterface.UserActivationDao;
import org.calminfotech.user.models.UserActivation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserActivationDaoImpl implements
		UserActivationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserActivation getActivationCode(String code) {
		// TODO Auto-generated method stub

		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserActivation where activationCode = ?")
				.setParameter(0, code).list();

		if (list.size() > 0)
			return (UserActivation) list.get(0);
		return null;
	}

	@Override
	public void update(UserActivation userActivation) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(userActivation);
	}

}
