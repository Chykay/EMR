package org.calminfotech.user.boInterface;

import org.calminfotech.user.models.UserActivation;

public interface UserActivationBo {

	UserActivation getUserActivation(String code);

	public void update(UserActivation userActivation);
}
