package com.stock.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.stock.admin.service.JWTUserDetailsService;
import com.stock.admin.utils.CustomUserDetail;
import com.stock.admin.config.JWTTokenUtil;
import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.ApplicationUser;
import com.stock.admin.model.entity.Shop;
import com.stock.admin.model.request.JWTRequest;
import com.stock.admin.model.response.JWTResponse;
import com.stock.admin.model.response.Response;
import com.stock.admin.model.response.ResponseStatus;
import com.stock.admin.repository.ShopsRepository;
import com.stock.admin.repository.UserRepository;
import static com.stock.admin.utils.StockAdminConstants.USER_DISABLED;
import static com.stock.admin.utils.StockAdminConstants.INVALID_SHOPCODE;
import static com.stock.admin.utils.StockAdminConstants.INVALID_CREDENTIALS;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Autowired
	private JWTUserDetailsService userDetailsService;
	
	@Autowired
	private ShopsRepository shopRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Creates the authentication token.
	 *
	 * @param authenticationRequest the authentication request
	 * @return the response
	 * @throws Exception the exception
	 */
	@PostMapping(value = "/login")
	@ResponseBody
	public Response createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword(),authenticationRequest.getShopCode());

		final CustomUserDetail userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		JWTResponse  jwtResponse= new JWTResponse(token);
		return new Response(ApplicationUser.type, jwtResponse, ResponseStatus.Success);
	}
	
		
	/**
	 * Sign up.
	 *
	 * @param authenticationRequest the authentication request
	 * @return the response
	 */
	@PostMapping(value = "/sign-up")
	@ResponseBody
	public Response signUp(@RequestBody JWTRequest authenticationRequest) {
		
		Optional<ApplicationUser> userExisting = userRepository.findByUsername(authenticationRequest.getUsername());
    	if(userExisting.isPresent()) { 
    		if( userExisting.get().getShopCodes().contains(authenticationRequest.getShopCode())) {
    			throw new StockAdminApplicationException(authenticationRequest.getUsername() + " User Already Exists",HttpStatus.EXPECTATION_FAILED);
    		}
    		else {
    			ApplicationUser applicationUser = userExisting.get();
    			applicationUser.getShopCodes().add(authenticationRequest.getShopCode());
    			Response.buildResponse(ApplicationUser.type, authenticationRequest.getUsername()+ " Added to " + authenticationRequest.getShopCode()+ " Successfully", true);
    		}
    	}
    	authenticationRequest.setPassword(bCryptPasswordEncoder.encode(authenticationRequest.getPassword()));
    	ApplicationUser applicationUser = new ApplicationUser();
    	applicationUser.setUsername(authenticationRequest.getUsername());
    	applicationUser.setPassword(authenticationRequest.getPassword());
    	List<String> shopCodes = new ArrayList<>();
    	shopCodes.add(authenticationRequest.getShopCode());
    	applicationUser.setShopCodes(shopCodes);
    	userRepository.save(applicationUser);
        return Response.buildResponse(ApplicationUser.type, authenticationRequest.getUsername()+ " Added Successfully", true);
	}

	/**
	 * Authenticate.
	 *
	 * @param username the username
	 * @param password the password
	 * @param shopCode the shop code
	 */
	private void authenticate(String username, String password,String shopCode) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new StockAdminApplicationException(USER_DISABLED, HttpStatus.UNAUTHORIZED);			
		} catch (BadCredentialsException e) {
			throw new StockAdminApplicationException(INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED);
		}
		
		Shop shop = shopRepository.findByShopCode(shopCode);
		if(shop==null) {
			throw new StockAdminApplicationException(INVALID_SHOPCODE, HttpStatus.BAD_REQUEST);
		}
	}
}