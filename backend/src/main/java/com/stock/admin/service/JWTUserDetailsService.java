package com.stock.admin.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stock.admin.model.entity.ApplicationUser;
import com.stock.admin.repository.UserRepository;

@Service
public class JWTUserDetailsService implements UserDetailsService {
	
	@Inject
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username){
				
		Optional<ApplicationUser> applicationUser = userRepository.findByUsername(username);
		
		if (applicationUser.isPresent()) {			
			return new User(applicationUser.get().getUsername(), applicationUser.get().getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
