package com.example.UserManagement.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.UserManagement.Model.CreateUserRequest;

@Entity
public class UserRoleMap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer uid;
	String userName;
	String role;
	public UserRoleMap() {
		super();
	}
	public UserRoleMap(String userName, String role) {
		super();
		this.userName = userName;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	@Override
	public String toString() {
		return "UserRoleMap [userName=" + userName + ", role=" + role + "]";
	}
	
	
}
