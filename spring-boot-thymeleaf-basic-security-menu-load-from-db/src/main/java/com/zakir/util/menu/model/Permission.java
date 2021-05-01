package com.zakir.util.menu.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="permission")
@Getter
@Setter
public class Permission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long permissionId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="route_id")
	private Route route;
	
	private Integer status;
	
	private String username;
	
	private Boolean hasReadRole;
	
	private Boolean hasInsertRole;
	
	private Boolean hasUpdateRole;
	
	private Boolean hasDeleteRole;

}
