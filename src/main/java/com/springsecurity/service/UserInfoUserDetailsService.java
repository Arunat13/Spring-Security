package com.springsecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.model.UserInfo;
import com.springsecurity.repo.UserInfoRepository;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserInfo> userInfoDetails = userInfoRepository.findByUsername(username);
		return userInfoDetails.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("UserName not found"));

	}

}
