package com.curso1.app.services;

import java.util.Optional;

import org.springframework.data.domain.*;


import com.curso1.app.entity.User;

public interface userService {

	
	public	Iterable<User> findAll();
	
	public Page<User>findAll(Pageable pageable);
	
	public Optional<User>findById(Long id);
	
	public User save(User user);
	
	public void deleteById(Long id);
}
