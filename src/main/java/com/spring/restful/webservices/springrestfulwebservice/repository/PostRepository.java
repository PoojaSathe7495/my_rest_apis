package com.spring.restful.webservices.springrestfulwebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restful.webservices.springrestfulwebservice.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> { 

}
