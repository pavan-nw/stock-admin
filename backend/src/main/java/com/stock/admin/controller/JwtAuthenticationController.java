package com.stock.admin.controller;

import static com.stock.admin.utils.StockAdminConstants.PRODUCT_DOES_NOT_EXISTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.stock.admin.service.JWTUserDetailsService;


import com.stock.admin.config.JWTTokenUtil;
import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.request.JWTRequest;
import com.stock.admin.model.response.JWTResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Autowired
	private JWTUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JWTResponse(token));
	}

	private void authenticate(String username, String password) throws StockAdminApplicationException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new StockAdminApplicationException("USER_DISABLED", HttpStatus.UNAUTHORIZED);			
		} catch (BadCredentialsException e) {
			throw new StockAdminApplicationException("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
		}
	}
}