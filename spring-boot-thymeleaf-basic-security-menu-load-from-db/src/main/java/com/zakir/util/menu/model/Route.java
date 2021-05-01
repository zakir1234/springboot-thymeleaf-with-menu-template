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

@Getter
@Setter
@Entity
@Table(name="route")
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long routeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="submodule_id")
	private Submodule submodule;
	
	private String routeName;	
	
	private String url;
	
	private Integer status;

}
