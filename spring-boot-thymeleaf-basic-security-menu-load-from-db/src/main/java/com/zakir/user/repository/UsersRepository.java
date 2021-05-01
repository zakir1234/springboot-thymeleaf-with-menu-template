/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakir.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.zakir.user.model.entity.Users;


public interface UsersRepository extends JpaRepository<Users, Long> {

	public Users findByUsernameAndPasswordAndEnabled(String username, String password, boolean enabled);

	public Users findByUsername(String username);

	public Users findByUsernameAndEnabled(String username, boolean enabled);
	
}
