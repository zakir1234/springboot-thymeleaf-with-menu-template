/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakir.user.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zakir.user.model.entity.UsersRole;

public interface UsersRoleRepository extends JpaRepository<UsersRole, Long>{
	
    public List<UsersRole> findByUsername(String username);

//	UsersRole findByRoleName(String roleName);
   
 
}
