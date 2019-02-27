package org.calminfotech.user.daoInterface;

import org.calminfotech.user.models.UserProfile;

public interface UserProfileDao {
	
	public void update(UserProfile userProfile);

	public UserProfile getUserProfileByUserId(int id);
}
