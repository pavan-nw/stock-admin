package com.stock.admin.service;

import java.util.Optional;
import javax.inject.Inject;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.stock.admin.model.entity.ApplicationUser;
import com.stock.admin.repository.UserRepository;
import com.stock.admin.utils.CustomUserDetail;

@Service
public class LoginUserDetailsService implements UserDetailsService {
	
	@Inject
	UserRepository userRepository;

	@Override
	public CustomUserDetail loadUserByUsername(String username){
				
		Optional<ApplicationUser> applicationUser = userRepository.findByUsername(username);
		
		if (applicationUser.isPresent()) {			
			 CustomUserDetail customUserDetail=new CustomUserDetail();
		        customUserDetail.setUser(applicationUser.get());
		        return customUserDetail;			

		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
