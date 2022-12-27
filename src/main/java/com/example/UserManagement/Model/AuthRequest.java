package com.example.UserManagement.Model;

import javax.validation.constraints.NotNull;

public class AuthRequest {
	@NotNull
	String username;
	String password;
	String role;
	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthRequest(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role=role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "AuthRequest [username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
}
