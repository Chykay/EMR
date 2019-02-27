package org.calminfotech.user.boImpl;

import java.util.List;

import org.calminfotech.user.boInterface.UserSecurityQuestionsBo;
import org.calminfotech.user.daoInterface.UserSecurityQuestionsDao;
import org.calminfotech.user.models.UserSecurityQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserSecurityQuestionsBoImpl implements UserSecurityQuestionsBo {

	@Autowired
	private UserSecurityQuestionsDao userSecurityQuestionDao;

	@Override
	public void save(UserSecurityQuestions userSecurityQuestion) {
		// TODO Auto-generated method stub
		userSecurityQuestionDao.save(userSecurityQuestion);
	}

	@Override
	public void update(UserSecurityQuestions userSecurityQuestion) {
		// TODO Auto-generated method stub
		userSecurityQuestionDao.update(userSecurityQuestion);
	}

	@Override
	public void delete(UserSecurityQuestions userSecurityQuestion) {
		// TODO Auto-generated method stub
		userSecurityQuestionDao.delete(userSecurityQuestion);
	}

	@Override
	public List<UserSecurityQuestions> fetchAll() {
		// TODO Auto-generated method stub
		return userSecurityQuestionDao.fetchAll();
	}

	@Override
	public UserSecurityQuestions getUserSecurityQuestionsById(int id) {
		// TODO Auto-generated method stub
		return userSecurityQuestionDao.getUserSecurityQuestionsById(id);
	}

	@Override
	public UserSecurityQuestions getUserSecurityQuestionsByUserId(int userId) {
		// TODO Auto-generated method stub
		return userSecurityQuestionDao.getUserSecurityQuestionsByUserId(userId);
	}

}
