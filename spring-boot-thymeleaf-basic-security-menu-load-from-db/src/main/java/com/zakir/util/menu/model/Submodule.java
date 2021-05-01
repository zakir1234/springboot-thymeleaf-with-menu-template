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
@Table(name="sub_module")
@Getter
@Setter
public class Submodule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subModuleId;
	
	private String subModuleName;
	
	private String image_path;
	
	private Integer status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="module_id")
	private Module module;

}
