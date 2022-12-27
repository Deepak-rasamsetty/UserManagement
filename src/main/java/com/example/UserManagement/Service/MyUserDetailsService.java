package com.example.UserManagement.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.UserManagement.Entity.UserRoleMap;
import com.example.UserManagement.Repository.UserDetailsRepository;
import com.example.UserManagement.Repository.UserRoleMapRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	  @Autowired
	  private UserDetailsRepository userRepository;

	  @Autowired 
	  UserRoleMapRepository userRoleMapRepository;
	  
	  @Override
	  public UserDetails loadUserByUsername(String username) {
		  com.example.UserManagement.Entity.UserDetails user = userRepository.findByUsername(username);
	      if (user == null) {
	          throw new UsernameNotFoundException(username);
	      }
	      List<UserRoleMap> userRoleMapList= userRoleMapRepository.findByUserName(username);  
	      List<GrantedAuthority> authList=new ArrayList<>();
	      Consumer<UserRoleMap> addToAuthorities=(userRoleMap)->authList.add(new SimpleGrantedAuthority(userRoleMap.getRole()));
	      userRoleMapList.stream().forEach(addToAuthorities);
	      return new User(user.getUsername(),user.getPassword(), true, true, true, true, authList);
	    }

}
