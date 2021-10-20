package com.saumil.springboot.thymeleafdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saumil.springboot.thymeleafdemo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

	public User findByUserName(String username);


	
//	public User findByUserName(String userName);
}
