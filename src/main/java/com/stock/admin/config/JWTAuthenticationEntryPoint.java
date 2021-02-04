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
import static com.stock.admin.utils.StockAdminConstants.AUTHORIZATION;
import static com.stock.admin.utils.StockAdminConstants.TOKEN_MISSING;
import static com.stock.admin.utils.StockAdminConstants.INVALID_TOKEN;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	
	private static final long serialVersionUID = -7858869558953243875L;

	/**
	 * Commence.
	 *
	 * @param request the request
	 * @param response the response
	 * @param authException the auth exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		
		String headerConstant = request.getHeader(AUTHORIZATION);
		
		ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpServletResponse.SC_UNAUTHORIZED);             
		
		if (headerConstant == null || !StringUtils.hasText(headerConstant)) {
			errorResponse.setErrorMessage(TOKEN_MISSING);			
		}		
		else{
			errorResponse.setErrorMessage(INVALID_TOKEN);		
		}
		byte[] responseToSend = restResponseBytes(errorResponse);
        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().write(responseToSend);        
		
	}
	
	 /**
 	 * Rest response bytes.
 	 *
 	 * @param eErrorResponse the e error response
 	 * @return the byte[]
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
 	private byte[] restResponseBytes(ErrorResponse eErrorResponse) throws IOException {
	        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
	        return serialized.getBytes();
	    }
}