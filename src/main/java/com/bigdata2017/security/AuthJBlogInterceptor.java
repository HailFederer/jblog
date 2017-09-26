package com.bigdata2017.security;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bigdata2017.jblog.vo.UserVo;

public class AuthJBlogInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
				
		// 1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Method에서 @Auth 받아오기
		AuthJBlog auth = handlerMethod.getMethodAnnotation(AuthJBlog.class);
		
		// 3-1. Method에 @Auth가 없다면, Class(Type)에 붙어있는 @Auth 받아오기
		if(auth == null)
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(AuthJBlog.class);
		
		// 4. @Auth가 안 붙어있는 경우
		if(auth == null) {
			return true;
		}
		
		// 5.@Auth가 붙어있을 때, 인증여부 체크
		HttpSession session = request.getSession();
		if(session == null) {	// 인증이 안 되어있는 상태
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {	// 인증이 안 되어있는 상태		
			response.sendRedirect(request.getContextPath());	
			return false;
		}
		
		String userId = authUser.getId();

		String uri = request.getRequestURI();
		StringTokenizer st = new StringTokenizer(uri, "/");
		String blogId = "";
		if(st.countTokens() > 2) {
			for(int i=0; i<2; i++) {
				blogId = st.nextToken();
			}
		}
		
		if(!blogId.equals(userId)) {
			response.sendRedirect(request.getContextPath()+"/"+userId);	
			return false;
		}
		
		return true;
	}
}













