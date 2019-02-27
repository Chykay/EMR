package org.calminfotech.user.boImpl;

import java.util.List;

import org.calminfotech.user.boInterface.SecurityQuestionBo;
import org.calminfotech.user.daoInterface.SecurityQuestionDao;
import org.calminfotech.user.models.SecurityQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecurityQuestionBoImpl implements SecurityQuestionBo {

	@Autowired
	private SecurityQuestionDao securityQuestionDao;

	@Override
	public void save(SecurityQuestion securityQuestion) {
		// TODO Auto-generated method stub
		securityQuestionDao.save(securityQuestion);
	}

	@Override
	public void update(SecurityQuestion securityQuestion) {
		// TODO Auto-generated method stub
		securityQuestionDao.update(securityQuestion);
	}

	@Override
	public void delete(SecurityQuestion securityQuestion) {
		// TODO Auto-generated method stub
		securityQuestionDao.delete(securityQuestion);
	}

	@Override
	public SecurityQuestion getQuestionById(int id) {
		// TODO Auto-generated method stub
		return securityQuestionDao.getQuestionById(id);
	}

	@Override
	public List<SecurityQuestion> fetchAll() {
		// TODO Auto-generated method stub
		return securityQuestionDao.fetchAll();
	}

}
