package com.zakir.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zakir.common.service.ApplicationUtilService;
import com.zakir.user.model.entity.Users;
import com.zakir.user.model.response.PostLoginViewInfo;
import com.zakir.user.repository.UsersRepository;
import com.zakir.util.UserInfoUtils;
import com.zakir.util.menu.Node;
import com.zakir.util.menu.model.Module;
import com.zakir.util.menu.model.Permission;
import com.zakir.util.menu.model.Route;
import com.zakir.util.menu.model.Submodule;
import com.zakir.util.menu.repository.PermissionRepository;
import com.zakir.util.menu.repository.RouteRepository;


@Service
public class UserService {
	
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private ApplicationUtilService applicationUtilService;


	@Autowired
	private UserInfoUtils userInfoUtils;
	
	public PostLoginViewInfo postLoginViewInfo() {
		
		PostLoginViewInfo postLoginViewInfo=new PostLoginViewInfo();
		String userName=userInfoUtils.getLoggedInUserName();
		Users user = usersRepository.findByUsername(userName);
		postLoginViewInfo.setUsername(user.getUsername());
		postLoginViewInfo.setNickname(user.getNickName());
		/*
		 * try { String logo="data:image/png;base64, "+new
		 * String(imageStorageService.fetchImageInBase64Encode(ImageFolder.INSTITUTE.
		 * name(), institute.getImageName())); postLoginViewInfo.setInstitutelogo(logo);
		 * }catch(Exception e) { postLoginViewInfo.setInstitutelogo(""); }
		 */
		
		return postLoginViewInfo;
	}

	public List<Node> getMenus() {
		
		List<Permission> permissions = permissionRepository.findByUsername(userInfoUtils.getLoggedInUserName());
		
		List<Route> routes = permissions.stream().map(permission -> permission.getRoute()).collect(Collectors.toList());
		
		List<Module> modules = permissions.stream().map(permission -> permission.getRoute().getSubmodule().getModule()).collect(Collectors.toList());
		
		List<Module> distictModules = modules.stream().filter(applicationUtilService.distinctByKey(Module :: getModuleId)).collect(Collectors.toList());
		
		List<Node> nodes = distictModules.stream().map(module -> getNodes(module, routes)).collect(Collectors.toList());
		
		return nodes;
	}

	private Node getNodes(Module module, List<Route> routes) {
		
		
		Node node = new Node();
		node.setId(module.getModuleId());
		node.setName(module.getModuleName());	
		
		node.setIcon(getIcon(module));
		
		Set<Route> singleModuleRoutes = routes.stream().filter(route -> route.getSubmodule().getModule().getModuleId().equals(module.getModuleId())).collect(Collectors.toSet());
		
		Set<Submodule> singleSubModules = singleModuleRoutes.stream().map(route -> route.getSubmodule()).collect(Collectors.toSet());
		
		List<Node> subModuleNodes = singleSubModules.stream().map(submodule -> getSubModuleNodes(submodule, singleSubModules, singleModuleRoutes)).collect(Collectors.toList());
		
		node.setSubNodes(subModuleNodes);  
					
		
		return node;
	}
	
	private String getIcon(Module module) {
		

		String icon = "";
		
		if(module.getModuleId() == 1) {
			icon = "fa fa-graduation-cap";
		}else if(module.getModuleId() == 2) {
			icon = "fa fa-cog";
		}
		
		return icon;
		
	}
	
	
	
	private Node getSubModuleNodes(Submodule submodule, Set<Submodule> submodules, Set<Route> routes) {
		
		Node node = new Node();
		node.setId(submodule.getSubModuleId());
		node.setName(submodule.getSubModuleName());			
		
		Set<Route> singleSubModuleRoutes = routes.stream().filter(route -> route.getSubmodule().getSubModuleId().equals(submodule.getSubModuleId())).collect(Collectors.toSet());
		
		List<Node> routeNodes = singleSubModuleRoutes.stream().map(route -> getRouteNodes(route, singleSubModuleRoutes)).collect(Collectors.toList());
		
		node.setSubNodes(routeNodes); 
		
		return node;
	}
	
	
	private Node getRouteNodes(Route route, Set<Route> routes) {
		
		Node node = new Node();
		node.setId(route.getRouteId());
		node.setName(route.getRouteName());
		node.setUrl(route.getUrl());		
		return node;
		
	}
	
	
	 
}
