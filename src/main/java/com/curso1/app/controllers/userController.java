package com.curso1.app.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso1.app.entity.User;
import com.curso1.app.services.userService;

@RestController
@RequestMapping("/api/users")

public class userController {

	@Autowired
	userService userService;
	
	//Create new user
	
	@PostMapping
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	public ResponseEntity<?> create(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	//Read an user
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long userId){
		Optional<User> oUser = userService.findById(userId);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(oUser);
		}
	}

	// Update an user
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PutMapping("/api/users/{id}")
	public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value = "id") Long userId){
		Optional<User> user = userService.findById(userId);

		if(!user.isPresent()){
			return ResponseEntity.notFound().build();
		}

		user.get().setName(userDetails.getName());
		user.get().setSurname(userDetails.getSurname());
		user.get().setEmail(userDetails.getEmail());
		user.get().setEnabled(userDetails.getEnabled());

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));		
	}

	//Delete an User
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value="id") Long userId){
		if(!userService.findById(userId).isPresent()){
			return ResponseEntity.notFound().build();
		}
		userService.deleteById(userId);
		return ResponseEntity.ok().build();
	}

	//Read all users
	@GetMapping
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	public List<User> readAll (){
		 List<User> users = StreamSupport
		 .stream(userService.findAll().spliterator(), false)
		 .collect(Collectors.toList());

		 return users;
	}
}
