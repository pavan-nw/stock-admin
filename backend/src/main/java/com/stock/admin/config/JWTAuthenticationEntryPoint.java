package com.stock.admin.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.admin.model.response.ErrorResponse;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		
		String headerConstant = request.getHeader("Authorization");
		
		ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpServletResponse.SC_UNAUTHORIZED);             
		
		if (headerConstant == null || !StringUtils.hasText(headerConstant)) {
			errorResponse.setErrorMessage("Token missing");			
		}		
		else{
			errorResponse.setErrorMessage("Invalid Token");		
		}
		byte[] responseToSend = restResponseBytes(errorResponse);
        response.setHeader("Content-Type", "application/json");
        response.setStatus(401);
        response.getOutputStream().write(responseToSend);        
		
	}
	
	 private byte[] restResponseBytes(ErrorResponse eErrorResponse) throws IOException {
	        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
	        return serialized.getBytes();
	    }
}