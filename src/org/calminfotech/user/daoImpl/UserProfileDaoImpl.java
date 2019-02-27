package org.calminfotech.user.daoImpl;

import java.util.List;

import org.calminfotech.user.daoInterface.UserProfileDao;
import org.calminfotech.user.models.UserProfile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileDaoImpl implements UserProfileDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void update(UserProfile userProfile) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(userProfile);
	}

	@Override
	public UserProfile getUserProfileByUserId(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserProfile where userId = ?")
				.setParameter(0, id).list();
		
		if (list.size() > 0)
			return (UserProfile) list.get(0);
		return null;
	}

}
