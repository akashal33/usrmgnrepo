package com.akashk.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.akashk.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository UserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.akashk.entity.User user = UserRepository.findByEmail(username);
		
		
		return new User(user.getEmail(), user.getPassword(), 
				
			user.getRoles().stream().map( role  -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList())
				
			);
	}

	
}
