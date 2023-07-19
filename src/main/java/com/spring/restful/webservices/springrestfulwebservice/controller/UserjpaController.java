package com.spring.restful.webservices.springrestfulwebservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.restful.webservices.springrestfulwebservice.dao.impl.UserDaoService;
import com.spring.restful.webservices.springrestfulwebservice.exception.UserNotFoundException;
import com.spring.restful.webservices.springrestfulwebservice.model.User;
import com.spring.restful.webservices.springrestfulwebservice.repository.UserRepository;

@RestController
public class UserjpaController {

	@Autowired
	private UserDaoService userDaoService;
	
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * public UserjpaController(UserRepository repository) {
	 * 
	 * this.userRepository=repository; }
	 */

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();

	}

	//Add Hateous here
	//We need EntityMaodel and WebMvcBuilderLink for hateous
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(user ==null)
			throw new UserNotFoundException("id " +id);
		EntityModel<User> model = EntityModel.of(user.get());
		WebMvcLinkBuilder link =linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(link.withRel("all-user"));
		return model;

	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		}

}
