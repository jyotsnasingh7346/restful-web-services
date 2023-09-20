package com.in28minutes.rest.webservices.restfulwebservices.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 5)
	private String description;

	// A user can own one to many posts
	// Each Post maps / belongs to a specific User
	@ManyToOne(fetch = FetchType.LAZY) // so we don't get User as part of Post in the API response as
	// fetch = FetchType.EAGER is the default value for @ManyToOne
	@JsonIgnore // adding this so we don't get User as part of Post in the API response
	private User user; // user_id field will be generated in Post table

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

}
