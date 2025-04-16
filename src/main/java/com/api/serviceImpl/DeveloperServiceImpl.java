package com.api.serviceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.api.entity.Developer;
import com.api.service.DeveloperService;

public class DeveloperServiceImpl implements DeveloperService, UserDetailsService{

	public Developer saveData(Developer developer) {
		// TODO Auto-generated method stub
		return null;
	}

	public Developer getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
