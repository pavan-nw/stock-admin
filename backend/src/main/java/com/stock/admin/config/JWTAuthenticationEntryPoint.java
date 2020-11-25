package com.stock.admin.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		
		String headerConstant = request.getHeader("Authorization");
		
		if (headerConstant == null || !StringUtils.hasText(headerConstant)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token missing");			
		}		
		else{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
		}
		
	}
}