/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zakir.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.softapple.helper.exception.AnyExceptionExceptionHandler;
import com.zakir.user.model.entity.Users;


/**
 *
 * @author Md. Zakir Hossain
 */
@Component
public class UserInfoUtils {
    
    private static ApplicationContext context;

    @Autowired
    public UserInfoUtils(ApplicationContext ac) {
        context = ac;
    }

    public static ApplicationContext getContext() {
        return context;
    }
    
    
     public static String getHashPassword(String password) {
        PasswordEncoder userPasswordEncoder = context.getBean("userPasswordEncoder", PasswordEncoder.class);
        return userPasswordEncoder.encode(password);
    }
    
    public static boolean isPreviousPasswordCorrect(String previousPlainPassword,String previousEncriptedPassword) {
        PasswordEncoder userPasswordEncoder = context.getBean("userPasswordEncoder", PasswordEncoder.class);
       return userPasswordEncoder.matches(previousPlainPassword, previousEncriptedPassword);
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static boolean hasARole(String roleName) {
    List<String> roleNames =	SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(oauthority -> oauthority.getAuthority().toString()).collect(Collectors.toList());
    	   
    if(roleNames.contains(roleName)) return true;
    
    return false;
    	
    }
    
    
    public static String getLoggedInUserName() {
    	    	
    	Authentication authentication = getAuthentication();
    	
    	String username = authentication.getName().toString();
    	
    	return username;

    }
    

    
    public static Long getLoggedInUserId() {
        Users user = getLoggedInUser();
        
        if(user == null) {
        	throw new AnyExceptionExceptionHandler("No User found");
        }
        
        return user.getUserId();

    }
    
    public static Long getLoggedInHospitalId() {
    	  Users user = getLoggedInUser();
          
          if(user == null) {
          	throw new AnyExceptionExceptionHandler("No User found");
          }
        return user.getHospitalId();

    }
    
    public static Authentication getAuthentication() {
    	
    	 SecurityContext context =	SecurityContextHolder.getContext();
         Authentication authentication = context.getAuthentication();
         return authentication;
    }
    
    public static Users getLoggedInUser() {

        Authentication authentication = getAuthentication();
        
        Users user = null;
        
        if(authentication != null && authentication.getPrincipal() != null) {
        	
        	if(! "anonymousUser".equals(String.valueOf(authentication.getPrincipal()))) {
        		user = (Users) authentication.getPrincipal();
        	}        	        	
        }
        
        return user;
   }
    
}
