package com.stock.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.stock.admin.service.JWTUserDetailsService;


import com.stock.admin.config.JWTTokenUtil;
import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.ApplicationUser;
import com.stock.admin.model.entity.Shop;
import com.stock.admin.model.request.JWTRequest;
import com.stock.admin.model.response.JWTResponse;
import com.stock.admin.model.response.Response;
import com.stock.admin.model.response.ResponseStatus;
import com.stock.admin.repository.ShopsRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Autowired
	private JWTUserDetailsService userDetailsService;
	
	@Autowired
	private ShopsRepository shopRepository;

	@PostMapping
	@RequestMapping(value = "/login")
	@ResponseBody
	public Response createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword(),authenticationRequest.getShopCode());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		JWTResponse  jwtResponse= new JWTResponse(token);
		return new Response(ApplicationUser.type, jwtResponse, ResponseStatus.Success);
	}

	private void authenticate(String username, String password,String shopCode) throws StockAdminApplicationException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new StockAdminApplicationException("USER_DISABLED", HttpStatus.UNAUTHORIZED);			
		} catch (BadCredentialsException e) {
			throw new StockAdminApplicationException("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
		}
		
		Shop shop = shopRepository.findByShopCode(shopCode);
		if(shop==null) {
			throw new StockAdminApplicationException("INVALID SHOPCODE", HttpStatus.BAD_REQUEST);
		}
	}
}