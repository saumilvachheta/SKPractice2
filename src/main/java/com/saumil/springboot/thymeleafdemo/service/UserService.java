package com.saumil.springboot.thymeleafdemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.saumil.springboot.thymeleafdemo.entity.User;
import com.saumil.springboot.thymeleafdemo.userdto.CrmUser;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);
	
	public User save(CrmUser crmUserDTO) ;
}
