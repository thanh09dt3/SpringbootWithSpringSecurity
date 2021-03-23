package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.config.JwtTokenUtil;
import com.example.demo.security.config.modal.LoginRequestForm;
import com.example.demo.security.config.modal.LoginResponseForm;
import com.example.demo.security.service.JwtUserDetailsService;
@RestController
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(LoginRequestForm authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		String token = jwtTokenUtil.generateToken(userDetails);
		
		LoginResponseForm responseForm = new LoginResponseForm(token, userDetails.getAuthorities().toString());
		return new ResponseEntity<LoginResponseForm>(responseForm, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
