package com.zakir.common.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.zakir.user.model.response.PostLoginViewInfo;
import com.zakir.user.service.UserService;
import com.zakir.util.menu.Node;

@Controller
@RequestMapping
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public String showLogin() {
		return "login";
	}

	@GetMapping("/login")
	public String showLoginPage(@RequestParam(required = false) String error) {
		return "login";
	}

	@RequestMapping(value = "/postLogin", method = RequestMethod.POST)
	public String postLogin(Model model) {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String homePage(HttpSession session, Model model) {
		PostLoginViewInfo postLoginViewInfo = userService.postLoginViewInfo();
		session.setAttribute("username", postLoginViewInfo.getUsername());
		session.setAttribute("nickname", postLoginViewInfo.getNickname());
		session.setAttribute("instituteid", postLoginViewInfo.getInstituteid());
		session.setAttribute("institutename", postLoginViewInfo.getInstitutename());
		session.setAttribute("address", postLoginViewInfo.getInstituteaddress());
		session.setAttribute("logo", postLoginViewInfo.getInstitutelogo());
		
		List<Node> nodes = userService.getMenus();		
		
		model.addAttribute("menuItemsList", nodes);

		return "home";
	}

	@GetMapping("/logout")
	public String logout(SessionStatus session, Session s) {
		SecurityContextHolder.getContext().setAuthentication(null);
		session.setComplete();
		return "redirect:/login";
	}
	
	  private List<Node> getSampleNodeList() {
	        List<Node> nodes = new ArrayList<>();
	        
	       Node module1 = new Node();
	       module1.setId(1L);
	       module1.setName("Module 1");
	       module1.setSubNodes(getNodes(2, 1));
	       
	       
	       for(Node nd : module1.getSubNodes()) {
	    	   nd.setName("Submodule");
	    	   nd.setSubNodes(getNodes(3, 2));
	       }
	       
	       nodes.add(module1);
	       
	  
	        return nodes;
	    }
	    
	    
	    private List<Node> getNodes(int numOfChildren, int childLevel){
	    	
	    	List<Node> nodes = new ArrayList<>();
	    	
	    	for(int i = 0; i< numOfChildren ; i++) {
	    		Node node = new Node();
	    		node.setId(Long.valueOf(i));
	    		
	    		String name = "";
	    		
	    		if(childLevel == 1) {
	    			name="Submodule";
	    		}else if (childLevel == 2) {
	    			name = "Item";
	    		}
	    		
	    		node.setName(name);
	    		
	    		nodes.add(node);
	    		
	    	}
	    	
	    	return nodes;
	    	
	    }
}
