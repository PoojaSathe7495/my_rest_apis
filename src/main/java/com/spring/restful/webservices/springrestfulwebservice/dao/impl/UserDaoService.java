package com.spring.restful.webservices.springrestfulwebservice.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.spring.restful.webservices.springrestfulwebservice.model.User;

@Service
public class UserDaoService implements UserDao {

	private static List<User> users = new ArrayList<>();
	private static int count = 0;

	static {
		users.add(new User(++count, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++count, "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(++count, "Jim", LocalDate.now().minusYears(20)));
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public User save(User user) {
		user.setId(++count);
		users.add(user);
		return user;
	}

	@Override
	public User findOne(int id) {

		Predicate<? super User> predicate = user -> user.getId() == (id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	@Override
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId() == (id);
		users.removeIf(predicate);
	}
}
