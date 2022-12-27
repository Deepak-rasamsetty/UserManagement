package com.example.UserManagement;

import java.util.ArrayList;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.UserManagement.Entity.UserDetails;
import com.example.UserManagement.Entity.UserRoleMap;
import com.example.UserManagement.Repository.UserDetailsRepository;
import com.example.UserManagement.Repository.UserRoleMapRepository;

@SpringBootApplication
public class UserManagementApplication {

	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired 
	UserRoleMapRepository userRoleMapRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}
	
	@Bean
	void createUserData() {
		UserDetails user1=new UserDetails("user1","user1","user1","user1");
		UserDetails user2=new UserDetails("user2","user2","user2","user2");
		UserDetails user3=new UserDetails("user3","user3","user3","user3");
		userDetailsRepository.save(user1);
		userDetailsRepository.save(user2);
		userDetailsRepository.save(user3);
		
		UserRoleMap um1=new UserRoleMap("user1", "DEVELOPER");
		UserRoleMap um2=new UserRoleMap("user2", "TESTER");
		UserRoleMap um3=new UserRoleMap("user3", "ADMIN");
		UserRoleMap um4=new UserRoleMap("user3", "SUPER_USER");
		userRoleMapRepository.save(um1);
		userRoleMapRepository.save(um2);
		userRoleMapRepository.save(um3);
		userRoleMapRepository.save(um4);
	}
	
	@Bean
	 JavaMailSender setJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    mailSender.setUsername("");
	    mailSender.setPassword("");
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    return mailSender;
	}
	
	@Bean
	PasswordEncoder setPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
