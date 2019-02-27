package org.calminfotech.user.daoImpl;

import java.util.List;

import org.calminfotech.user.daoInterface.UserLoginSessionDao;
import org.calminfotech.user.models.UserLoginSession;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserLoginSessionDaoImpl implements UserLoginSessionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(UserLoginSession userLoginSession) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(userLoginSession);
	}

	@Override
	public List<UserLoginSession> fetchAll() {
		// TODO Auto-generated method stub
		List<UserLoginSession> list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserLoginSession").list();
		return list;
	}

	@Override
	public List<UserLoginSession> fetchAllByUsername(String username) {
		// TODO Auto-generated method stub
		List<UserLoginSession> list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserLoginSession where username = ?")
				.setParameter(0, username).list();
		return list;
	}

	@Override
	public UserLoginSession getUserLoginSessionById(int id) {
		List<UserLoginSession> list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserLoginSession where id = ?")
				.setParameter(0, id).list();
		if(list.size()>0)
			return (UserLoginSession)list.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserLoginSession> search(UserLoginSession startDate, UserLoginSession endDate) {
		Criteria criteria = this.sessionFactory.getCurrentSession().
				createCriteria(UserLoginSession.class);
		if(startDate.getTimeStamp() != null && endDate.getTimeStamp() != null ){
			criteria.add(Restrictions.between("date", startDate, endDate));
		}
		return criteria.list();
	}

}
