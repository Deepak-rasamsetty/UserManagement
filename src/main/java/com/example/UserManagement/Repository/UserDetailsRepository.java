package com.example.UserManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserManagement.Entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer>{

	UserDetails findByUsername(String username);

}
