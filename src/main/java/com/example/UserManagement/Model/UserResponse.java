package com.example.UserManagement.Model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserResponse{

String username;
String password;
List<String> roleList;
public UserResponse() {
	super();
	// TODO Auto-generated constructor stub
}
public UserResponse(String username, String password, List<String> roleList) {
	super();
	this.username = username;
	this.password = password;
	this.roleList = roleList;
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
public List<String> getRoleList() {
	return roleList;
}
public void setRoleList(List<String> roleList) {
	this.roleList = roleList;
}


}
