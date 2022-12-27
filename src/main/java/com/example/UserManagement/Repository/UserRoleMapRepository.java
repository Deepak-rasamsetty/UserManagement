package com.example.UserManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.UserManagement.Entity.UserRoleMap;

public interface UserRoleMapRepository extends JpaRepository<UserRoleMap,Integer>{

	List<UserRoleMap> findByUserName(String username);

	List<String> getRoleByUserName(String username);

}

