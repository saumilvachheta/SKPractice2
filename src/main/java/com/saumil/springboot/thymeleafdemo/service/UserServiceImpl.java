package com.saumil.springboot.thymeleafdemo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.saumil.springboot.thymeleafdemo.entity.Role;
import com.saumil.springboot.thymeleafdemo.entity.User;
import com.saumil.springboot.thymeleafdemo.repository.UserRepository;
import com.saumil.springboot.thymeleafdemo.userdto.CrmUser;
	   
@Service 
public class UserServiceImpl implements UserService {

	// @Autowired // this is a field injection, you can also use constructor or field based injection. But field based injection is not recommended. So we should use COnstructor injection. 
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// this is a constructor injection, only single constructor is used, therefore no need to explicitly use @Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public User save(CrmUser crmUserDTO) {
		// TODO Auto-generated method stub
		
		

		User user = new User(crmUserDTO.getUserName(), passwordEncoder.encode(crmUserDTO.getPassword()), 
						crmUserDTO.getFirstName(), crmUserDTO.getLastName(), 
						crmUserDTO.getEmail(),	
						Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}
	
	/*
	 * @Override public User save(UserRegistrationDTO registrationDTO) { // TODO
	 * Auto-generated method stub
	 * 
	 * 
	 * }
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		/* User user = userRepository.findByEmail(username); */
		
		User user = userRepository.findByUserName(username);

		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));	
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(userName);
	}

	
	
}
