package com.spring.restful.webservices.springrestfulwebservice.dao.impl;

import java.util.List;

import com.spring.restful.webservices.springrestfulwebservice.model.User;

public interface UserDao {
	
	public List<User> findAll();
	public User save(User user);
	public User findOne(int id);
	public void deleteById(int id);



}
