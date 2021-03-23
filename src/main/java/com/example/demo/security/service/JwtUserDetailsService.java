package com.example.demo.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TAccountEntity;
import com.example.demo.repository.AccountRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired AccountRepository accountRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<TAccountEntity> accountOptional = accountRepo.findByUsername(username);
		User user = null;
		if (accountOptional!= null || !accountOptional.isEmpty()) {
			TAccountEntity accountEntity = accountOptional.get();
			List<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>();
			authorityList.add(new SimpleGrantedAuthority(accountEntity.getRole()));
			boolean isEnable = false;
			if ("00".equals(accountEntity.getStatusCode())) {
				isEnable = true;
			}
			user = new User(accountEntity.getUsername(), accountEntity.getPassword(), isEnable, true, true, true, authorityList);
			
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return  user;
	}
}