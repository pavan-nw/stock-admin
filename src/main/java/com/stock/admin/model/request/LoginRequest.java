package com.stock.admin.model.request;

import java.io.Serializable;

public class LoginRequest extends Request implements Serializable  {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String username;	
	private String password;	
	private String shopCode;
	
	public String getUsername() {
		return this.username;
	}	
	
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}