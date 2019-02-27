package org.calminfotech.user.utils.interceptors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Authenticator extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		// TODO check login state, forward to login page
		HttpSession session = request.getSession();
		Object sessionAttribute = session.getAttribute("login");
		if (null == sessionAttribute) {
			System.out.println("Kunle check me :"+sessionAttribute);
			session = request.getSession();
			String url = request.getRequestURL().toString();
			System.out.println("Get Url kunle :"+url);
			
			session.setAttribute("callbackUrl", null);
			
			// Set session of current url to get back after question so user can
			// go back to what they were doing
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/user/session/login");
			requestDispatcher.forward(request, response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

}
