package com.example.demo.security.config.modal;

import java.io.Serializable;

public class LoginResponseForm implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String token;
	private final String role;
	public LoginResponseForm(String token, String role) {
		this.token = token;
		this.role = role;
	}

	public String token() {
		return token;
	}

	public String getRole() {
		return role;
	}

	public String getToken() {
		return this.token;
	}
}