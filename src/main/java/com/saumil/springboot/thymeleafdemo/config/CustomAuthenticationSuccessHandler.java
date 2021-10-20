package com.saumil.springboot.thymeleafdemo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.saumil.springboot.thymeleafdemo.entity.User;
import com.saumil.springboot.thymeleafdemo.service.UserService;

// import ch.qos.logback.classic.Logger; specific implementation, by default Spring uses this internally

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class); // logback
	//private static final Logger logger = LogManager.getLogger(CustomAuthenticationSuccessHandler.class);
	
    @Autowired
    private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String userName = authentication.getName();
		
		System.out.println("userName=" + userName);

		User theUser = userService.findByUserName(userName);
		
		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);
		
		// forward to home page
		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
