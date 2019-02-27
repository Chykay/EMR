package org.calminfotech.user.daoImpl;

import java.util.List;

import org.calminfotech.user.daoInterface.UserSecurityQuestionsDao;
import org.calminfotech.user.models.UserSecurityQuestions;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserSecurityQuestionsDaoImpl implements UserSecurityQuestionsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(UserSecurityQuestions userSecurityQuestion) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(userSecurityQuestion);
	}

	@Override
	public void update(UserSecurityQuestions userSecurityQuestion) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(userSecurityQuestion);
	}

	@Override
	public void delete(UserSecurityQuestions userSecurityQuestion) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(userSecurityQuestion);
	}

	@Override
	public List<UserSecurityQuestions> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserSecurityQuestions").list();
		return (List<UserSecurityQuestions>) list;
	}

	@Override
	public UserSecurityQuestions getUserSecurityQuestionsById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserSecurityQuestions where id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (UserSecurityQuestions) list.get(0);
		return null;
	}

	@Override
	public UserSecurityQuestions getUserSecurityQuestionsByUserId(int userId) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserSecurityQuestions where userId = ?")
				.setParameter(0, userId).list();
		
		if (list.size() > 0)
			return (UserSecurityQuestions) list.get(0);
		return null;
	}

}
