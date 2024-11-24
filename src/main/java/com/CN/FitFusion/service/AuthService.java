package com.CN.FitFusion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.JwtRequest;
import com.CN.FitFusion.dto.JwtResponse;
import com.CN.FitFusion.jwt.JwtAuthenticationHelper;

@Service
public class AuthService {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtAuthenticationHelper authenticationHelper;

	public JwtResponse login(JwtRequest jwtRequest) {
		Authentication authentication = doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		String token = authenticationHelper.generateToken((UserDetails)authentication.getPrincipal());
		JwtResponse jwtResponse = JwtResponse.builder().jwtToken(token).build();
		return jwtResponse;

	}
	
	public Authentication doAuthenticate(String username,String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {		
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			return authentication;
		}
		catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username or password");
		}
		
	}
	
	

}
