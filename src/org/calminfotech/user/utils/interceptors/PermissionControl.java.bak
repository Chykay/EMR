package org.calminfotech.user.utils.interceptors;

import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.calminfotech.system.boInterface.ResourceBo;
import org.calminfotech.system.models.Resource;
import org.calminfotech.user.models.Group;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class PermissionControl extends HandlerInterceptorAdapter {

	@Autowired
	private ResourceBo resourceBo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private RequestMappingHandlerMapping mappingHandler;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub

		// Get the current url,
		// Get the resource from the database
		// Get the user group for userIdentity object
		// Check the resource.getGroup().contains(group);
		// return true or false.

		// Allow all post request
		if ("POST".equals(request.getMethod()))
			return true;

		String url = null;
		for (Entry<RequestMappingInfo, HandlerMethod> m : mappingHandler
				.getHandlerMethods().entrySet()) {

			// Loop through all the RequestMappingInfo to get a matching url
			// pattern
			RequestMappingInfo requestMappingInfo = m.getKey()
					.getMatchingCondition(request);
			if (null != requestMappingInfo) {
				// requestMappingInfo is available. store the url and escape
				Set<String> urlArray = requestMappingInfo
						.getPatternsCondition().getPatterns();
				// Store the request url
				url = (String) urlArray.toArray()[0];
				break;
			}

		}
		System.out.println(url);
		if (null != url) {
			Group group = this.userIdentity.getGroup();
			Resource resource = this.resourceBo.getResourceByUrlPattern(url);
			
			// Loop through all the groups inside the resource
			for (Group rGroup : resource.getGroups()) {
				if (rGroup.getId() == group.getId()) {
					// If the groupId of the current user matches any group
					// inside the resource allow access
					return true;
				}
			}

		}

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("/user/permission/noaccess");
		requestDispatcher.forward(request, response);
		return false;
	}

}
