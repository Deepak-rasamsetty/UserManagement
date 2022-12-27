package com.example.UserManagement.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.UserManagement.Model.CreateUserRequest;

@Entity
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer uid;
	String name;
	String username;
	String gmailId;
	String password;
	public UserDetails() {
		super();
	}
	public UserDetails(String name, String username, String gmailId, String password) {
		super();
		this.name = name;
		this.username = username;
		this.gmailId = gmailId;
		this.password = new BCryptPasswordEncoder().encode(password);
		//this.password=password;
	}
	public UserDetails(CreateUserRequest request) {
		this.name = request.getName();
		this.username = request.getUsername();
		this.gmailId = request.getGmailId();
		this.password = new BCryptPasswordEncoder().encode(request.getPassword());
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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
		this.password = new BCryptPasswordEncoder().encode(password);
	}
	@Override
	public String toString() {
		return "UserDetails [uid=" + uid + ", name=" + name + ", username=" + username + ", gmailId=" + gmailId
				+ ", password=" + password + "]";
	}
	
	
}
