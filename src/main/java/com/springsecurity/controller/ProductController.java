package com.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.AuthorizeRequestsDsl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.dto.AuthRequest;
import com.springsecurity.model.UserInfo;
import com.springsecurity.repo.UserInfoRepository;
import com.springsecurity.service.JwtService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome successfully....!";
	}
	
	@GetMapping("/balance")
	public String balance() {
		return "balance is 54000 successfully....!";
	}
	
	@GetMapping("/debit")
	public List<UserInfo> debit() {
		return userInfoRepository.findAll();
	}
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepository.save(userInfo);
		return "successfully....!";
	}
	
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	
		return jwtService.generateToken(authRequest.getUsername());
	}
	
}
