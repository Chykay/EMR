package org.calminfotech.user.boImpl;

import org.calminfotech.user.boInterface.UserProfileBo;
import org.calminfotech.user.daoInterface.UserProfileDao;
import org.calminfotech.user.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserProfileBoImpl implements UserProfileBo {

	@Autowired
	private UserProfileDao userProfileDao;
	
	@Override
	public void update(UserProfile userProfile) {
		// TODO Auto-generated method stub
		userProfileDao.update(userProfile);
	}

	@Override
	public UserProfile getUserProfileByUserId(int userId) {
		// TODO Auto-generated method stub
		return userProfileDao.getUserProfileByUserId(userId);
	}

}
