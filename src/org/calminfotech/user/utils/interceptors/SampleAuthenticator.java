package org.calminfotech.user.utils.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.calminfotech.system.boInterface.ResourceBo;
import org.calminfotech.user.boInterface.GroupBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleAuthenticator extends HandlerInterceptorAdapter {

	private int sampleId = 16;

	@Autowired
	private UserBo userBo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private ResourceBo resourceBo;

	@Autowired
	private GroupBo groupBo;

	@Override
	@Transactional
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// Set sample data for Development
		if (!userIdentity.hasIdentity()) {
			User user = userBo.getUserById(sampleId);
			userIdentity.setUserId(sampleId);
			userIdentity.setUsername(user.getUsername());
			userIdentity.setUserProfile(user.getUserProfile());
			userIdentity.setIdentity(true);
			userIdentity.setRole(user.getRole());
			//userIdentity.setOrganisation(user.getOrganisation());

		}
		return true;
	}
}
