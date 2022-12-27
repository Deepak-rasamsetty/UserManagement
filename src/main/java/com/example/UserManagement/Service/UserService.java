package com.example.UserManagement.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.UserManagement.Entity.UserDetails;
import com.example.UserManagement.Entity.UserRoleMap;
import com.example.UserManagement.Exception.UserNameAlreadyExistsException;
import com.example.UserManagement.Model.AuthRequest;
import com.example.UserManagement.Model.CreateUserRequest;
import com.example.UserManagement.Model.EmailDetails;
import com.example.UserManagement.Model.UserResponse;
import com.example.UserManagement.Repository.UserDetailsRepository;
import com.example.UserManagement.Repository.UserRoleMapRepository;

@Service
public class UserService {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	UserRoleMapRepository userRoleMapRepository;
	
	@Autowired
	EmailService emailService;

	
	private static final  Logger LOGGER=LoggerFactory.getLogger(UserService.class);

	public boolean addUser(CreateUserRequest createUserRequest) throws UserNameAlreadyExistsException {
		LOGGER.info("Enetered addUser function");
		UserDetails UserAlreadyExistsByUserName =  userDetailsRepository.findByUsername(createUserRequest.getUsername());
		if(UserAlreadyExistsByUserName!=null) {
			LOGGER.error("User already exsits");
			throw new UserNameAlreadyExistsException("User name already in use");
		}
		UserDetails newUser = new UserDetails(createUserRequest);
		List<UserRoleMap> userRoles=getUserRoleMapListFromRequest(createUserRequest);
		
		userDetailsRepository.save(newUser);
		userRoleMapRepository.saveAll(userRoles);
		
		EmailDetails emailDetails=new EmailDetails();
		emailDetails.setRecipient(createUserRequest.getGmailId());
		emailDetails.setSubject("Account Registered");
		emailDetails.setMsgBody("Thank you for registering");
		emailService.sendSimpleMail(emailDetails);
		LOGGER.info("Leaving addUser function");
		return true;
	} 

	private List<UserRoleMap> getUserRoleMapListFromRequest(CreateUserRequest createUserRequest) {
		List<UserRoleMap> userRolesList=new ArrayList<UserRoleMap>();
		Consumer<String> addEachRoleToList=role->userRolesList.add(new UserRoleMap(createUserRequest.getUsername(), role));
		createUserRequest.getRoles().stream().forEach(addEachRoleToList);
		return userRolesList;
	}

	public boolean updateUser(CreateUserRequest createUserRequest) {
		LOGGER.info("Enetered updateUser function");
		UserDetails userToBeUpdated =  userDetailsRepository.findByUsername(createUserRequest.getUsername());
		userToBeUpdated.setGmailId(createUserRequest.getGmailId());
		userToBeUpdated.setUsername(createUserRequest.getUsername());
		userDetailsRepository.save(userToBeUpdated);
		LOGGER.info("Enetered updateUser function");
		return true;
	}

	public boolean authenticate(AuthRequest authRequest) {
		LOGGER.info("Enetered authenticate function");
		UserDetails userToBeAuthenticated =  userDetailsRepository.findByUsername(authRequest.getUsername());
		//return new BCryptPasswordEncoder().encode(authRequest.getPassword()).equals(userToBeAuthenticated.getPassword());
		return  new BCryptPasswordEncoder().matches(authRequest.getPassword(),userToBeAuthenticated.getPassword());
	}

	public boolean authorizeUser(AuthRequest authRequest) {
		LOGGER.info("Enetered authorizeUser function");
		List<UserRoleMap> userRoles=userRoleMapRepository.findByUserName(authRequest.getUsername());
		return  userRoles.stream().anyMatch(x->x.getRole().equals(authRequest.getRole()));

	}
	
	public UserResponse loadUserByUsername(String username) {
		  com.example.UserManagement.Entity.UserDetails user = userDetailsRepository.findByUsername(username);
	      if (user == null) {
	          throw new UsernameNotFoundException(username);
	      }
	      List<UserRoleMap> userRoleMapList= userRoleMapRepository.findByUserName(username);  
	      List<String> roleList=new ArrayList<>();
	      Consumer<UserRoleMap> addToRoleList=(userRoleMap)->roleList.add(userRoleMap.getRole());
	      userRoleMapList.stream().forEach(addToRoleList);
	      return new UserResponse(user.getUsername(),user.getPassword(), roleList);
	    }

}
