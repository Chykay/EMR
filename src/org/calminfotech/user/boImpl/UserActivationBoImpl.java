package org.calminfotech.user.boImpl;

import org.calminfotech.user.boInterface.UserActivationBo;
import org.calminfotech.user.daoInterface.UserActivationDao;
import org.calminfotech.user.models.UserActivation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserActivationBoImpl implements UserActivationBo {

	@Autowired
	UserActivationDao userActivationDao;

	@Override
	public UserActivation getUserActivation(String code) {
		// TODO Auto-generated method stub
		return userActivationDao.getActivationCode(code);
	}

	@Override
	public void update(UserActivation userActivation) {
		// TODO Auto-generated method stub
		userActivationDao.update(userActivation);
	}

}
