package com.example.UserManagement.Controller;

import javax.validation.Valid;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserManagement.Exception.UserNameAlreadyExistsException;
import com.example.UserManagement.Model.AuthRequest;
import com.example.UserManagement.Model.CreateUserRequest;
import com.example.UserManagement.Model.EmailDetails;
import com.example.UserManagement.Model.UserResponse;
import com.example.UserManagement.Service.EmailService;
import com.example.UserManagement.Service.MyUserDetailsService;
import com.example.UserManagement.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final  Logger LOGGER=LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	
	 @Autowired private EmailService emailService;

	 
	    // Sending a simple Email
	    @PostMapping("/sendMail")
	    public boolean
	    sendMail(@RequestBody EmailDetails details)
	    {
	        boolean status
	            = emailService.sendSimpleMail(details);
	 
	        return status;
	    }
	 
	    // Sending email with attachment
	    @PostMapping("/sendMailWithAttachment")
	    public String sendMailWithAttachment(
	        @RequestBody EmailDetails details)
	    {
	        String status
	            = emailService.sendMailWithAttachment(details);
	 
	        return status;
	    }
	@GetMapping("/api/hello")
	public String sayHello() {
		LOGGER.info("Enetereed Say Hello method");
		return "Hello";
	}
	
	@PostMapping("/register")
	public boolean addUser(@Valid @RequestBody CreateUserRequest createUserRequest) throws UserNameAlreadyExistsException {
		LOGGER.info("Enetered addUser Hello method");
		return userService.addUser(createUserRequest);
		
	}
	
	@PutMapping("/update")
	public boolean updateUserr(@Valid @RequestBody CreateUserRequest createUserRequest) {
		LOGGER.info("Enetered addUser Hello method");
		return userService.updateUser(createUserRequest);
	}
	
	@PostMapping("/authenticate")
	public boolean authenticateUser( @RequestBody AuthRequest authRequest) {
		LOGGER.info("Enetered addUser Hello method");
		return userService.authenticate(authRequest);
	}
	
	@PostMapping("/authorize")
	public boolean authorizeUser( @RequestBody AuthRequest authRequest) {
		LOGGER.info("Enetered authorizeUser Hello method");
		return userService.authorizeUser(authRequest);
		
	}
	
	@GetMapping("/get/{username}")
	 public UserResponse loadUserByUsername(@PathVariable("username") String username) {
		return userService.loadUserByUsername(username);
	}
}
