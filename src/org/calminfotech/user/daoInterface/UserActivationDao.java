package org.calminfotech.user.daoInterface;

import org.calminfotech.user.models.UserActivation;

public interface UserActivationDao {

	UserActivation getActivationCode(String code);
	
	public void update(UserActivation userActivation);
}
