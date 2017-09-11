package com.example.demobootsecurity.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(!"admin".equalsIgnoreCase(username)) {
			throw new UsernameNotFoundException("unknown user");
		}
		
		return new User("admin", "1234", AuthorityUtils.createAuthorityList("ADMIN"));
	}

}
