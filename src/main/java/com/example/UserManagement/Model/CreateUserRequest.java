package com.example.UserManagement.Model;

import java.util.List;

import javax.validation.constraints.NotNull;

public class CreateUserRequest {
	@NotNull
	String name;
	@NotNull
	String username;
	@NotNull
	String gmailId;
	@NotNull
	String password;
	
	List<String> roles;
	
	public CreateUserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreateUserRequest(String name, String username, String gmailId, String password) {
		super();
		this.name = name;
		this.username = username;
		this.gmailId = gmailId;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGmailId() {
		return gmailId;
	}
	public void setGmailId(String gmailId) {
		this.gmailId = gmailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "CreateUserRequest [name=" + name + ", username=" + username + ", gmailId=" + gmailId + ", password="
				+ password + ", roles=" + roles + "]";
	}
	
	
}
