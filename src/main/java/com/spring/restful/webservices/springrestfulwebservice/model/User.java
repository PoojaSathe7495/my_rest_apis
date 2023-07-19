package com.spring.restful.webservices.springrestfulwebservice.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity(name="user_details")
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	@Size(min=2, message = "name should have atleast two char")
	private String name;

	
	@Column(name="birth_date")
	@Past(message="Birthdate should be in past")
	private LocalDate birthdate;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	List<Post> posts;
	
	
	
	public User() {
		super();
	}


	public User(String string, String string2) {
		super();
	}


	public User(int id, String name, LocalDate birthdate) {
		
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}
	
	
	

	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
	
	
	
	
	


}
