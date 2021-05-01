package com.zakir.user.model.entity;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import com.zakir.util.UserInfoUtils;



public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		
		return UserInfoUtils.getLoggedInUserName();
	}
}
