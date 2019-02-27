/*package org.calminfotech.utils.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.SystemSetting;
import org.calminfotech.system.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SettingsInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private SystemSetting systemSetting;

	@Autowired
	private SettingBo settingBo;

	@Autowired
	UserIdentity userIdentity;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (null == systemSetting.getSetting()) {
			systemSetting.setSetting(settingBo.getSetting());
		}

		if (null == systemSetting.getOrganisation()) {
			systemSetting.setOrganisation(userIdentity.getOrganisation());
		}

		return true;
	}
}
*/