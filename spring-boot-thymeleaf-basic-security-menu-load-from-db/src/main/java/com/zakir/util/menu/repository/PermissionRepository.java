package com.zakir.util.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zakir.util.menu.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

	List<Permission> findByUsername(String loggedInUserName);

}
