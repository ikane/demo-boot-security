package com.example.demobootsecurity.config;

import java.lang.invoke.MethodHandles;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class CustomPreAuthentocationFilter extends AbstractPreAuthenticatedProcessingFilter {
	
	//public static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		
		String username = request.getParameter("user");
		
		logger.info("********* getPreAuthenticatedCredentials - username: {}" + username);
		
		return null;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		
		String username = request.getParameter("user");
		
		logger.info("********* getPreAuthenticatedPrincipal - username: {}" + username);
		
		return null;
	}

}
