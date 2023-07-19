package com.spring.restful.webservices.springrestfulwebservice.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.restful.webservices.springrestfulwebservice.dao.impl.Employee;
import com.spring.restful.webservices.springrestfulwebservice.exception.UserNotFoundException;
import com.spring.restful.webservices.springrestfulwebservice.model.Post;
import com.spring.restful.webservices.springrestfulwebservice.model.User;
import com.spring.restful.webservices.springrestfulwebservice.repository.PostRepository;
import com.spring.restful.webservices.springrestfulwebservice.repository.UserRepository;

@RestController
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	/*
	 * public UserjpaController(UserRepository repository) {
	 * 
	 * this.userRepository=repository; }
	 */

	@GetMapping("/jpa/post")
	public List<Post> retrieveAllPost() {

		return postRepository.findAll();

	}

	// Add Hateous here
	// We need EntityMaodel and WebMvcBuilderLink for hateous
	@GetMapping("/jpa/hashmap/{id}")
	public List<Post> retrievePostBasedOnUserId(@PathVariable int id) {

		Optional<User> user = userRepository.findById(id);
		if (user == null)
			throw new UserNotFoundException("id " + id);

		return user.get().getPosts();

	}

	@PostMapping("/jpa/post")
	public ResponseEntity<User> createPost(@Valid @RequestBody Post post) {
		Post savedUser = postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/post/{id}")
	public void deletePost(@PathVariable int id) {
		postRepository.deleteById(id);
	}

}
