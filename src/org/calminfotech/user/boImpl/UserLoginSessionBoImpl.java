package org.calminfotech.user.boImpl;

import java.util.List;

import org.calminfotech.user.boInterface.UserLoginSessionBo;
import org.calminfotech.user.daoInterface.UserLoginSessionDao;
import org.calminfotech.user.models.UserLoginSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserLoginSessionBoImpl implements UserLoginSessionBo {

	@Autowired
	private UserLoginSessionDao userLoginSessionDao;

	@Override
	public void save(UserLoginSession userLoginSession) {
		// TODO Auto-generated method stub
		userLoginSessionDao.save(userLoginSession);
	}

	@Override
	public List<UserLoginSession> fetchAll() {
		// TODO Auto-generated method stub
		return userLoginSessionDao.fetchAll();
	}

	@Override
	public List<UserLoginSession> fetchAllByUsername(String username) {
		// TODO Auto-generated method stub
		return userLoginSessionDao.fetchAllByUsername(username);
	}

	@Override
	public UserLoginSession getUserLoginSessionById(int id) {
		// TODO Auto-generated method stub
		return userLoginSessionDao.getUserLoginSessionById(id);
	}

	@Override
	public List<UserLoginSession> search(UserLoginSession startDate,
			UserLoginSession endDate) {
		// TODO Auto-generated method stub
		return userLoginSessionDao.search(startDate, endDate);
	}

}