package com.example.SPEECHEMOTIONRECOGNITIONSYSTEM.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.SPEECHEMOTIONRECOGNITIONSYSTEM.Model.User;
import com.example.SPEECHEMOTIONRECOGNITIONSYSTEM.Repository.UserRepo;





public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user= userRepo.findByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("User not Found");
		}else {
			return new CustomUser(user);
		}
	}

}
