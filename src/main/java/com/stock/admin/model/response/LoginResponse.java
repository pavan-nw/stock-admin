package com.stock.admin.model.response;

import java.io.Serializable;

public class LoginResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String token;

	public LoginResponse(String jwttoken) {
		this.token = jwttoken;
	}

	public String getToken() {
		return this.token;
	}
}