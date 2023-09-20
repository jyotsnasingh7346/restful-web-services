package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.in28minutes.rest.webservices.restfulwebservices.post.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 2, message = "Name should have atleast 2 characters")
//	@JsonProperty("user_name")
	private String name;

	@Past(message = "Birthdate should be valid & in the past")
//	@JsonProperty("birth_date")
	private LocalDate birthDate;

	// Each User can have one or more Posts.
	@OneToMany(mappedBy = "user") // 'mappedBy' suggests that owner of the field 'posts' is 'user'
	@JsonIgnore // adding this so we don't get Posts as part of User in the API response
	private List<Post> posts;

	// since we are using JPA, we need a default constructor
	protected User() {
	}

	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}


}
