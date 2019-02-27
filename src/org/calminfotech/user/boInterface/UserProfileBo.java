package org.calminfotech.user.boInterface;

import org.calminfotech.user.models.UserProfile;

public interface UserProfileBo {

	public void update(UserProfile userProfile);
	
	public UserProfile getUserProfileByUserId(int userId);
}
