package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
/** 
 *  ログインフィルダー
 **/
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse  response, Object handle) throws Exception {
		
//		System.out.println("preHandle");
		
		HttpSession session = request.getSession();
		if (!"authorized".equals(session.getAttribute("token"))) {
			request.getRequestDispatcher("/login").forward(request, response);
			return false;
		}
		return true;
	}
}
