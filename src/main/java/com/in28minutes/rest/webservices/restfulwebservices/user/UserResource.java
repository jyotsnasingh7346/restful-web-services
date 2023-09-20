package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

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

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDAOService service;

	// Auto wiring UserDAOService thru constructor injection
	public UserResource(UserDAOService service) {
		this.service = service;
	}

	/*
	 * NOTE : Whenever we are creating a REST API, we should think from consumer's POV
	 * */
	
	// First REST API -> 1. Get all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// Second REST API -> 2. Find 1 specific user
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findById(id);

		if (user == null)
			throw new UserNotFoundException("id: " + id);

		EntityModel<User> entityModel = EntityModel.of(user);	// this will still return the data as it is.
		
		/*
		 * To add links, use WebMvcLinkBuilder. 
		 * Here, we are using the WebMvcLinkBuilder's linkTo() method 
		 * to create a link pointing to the controller's method retrieveAllUsers().
		 * Then, we are storing this link in a local variable.
		 * 
		 * You can add as many links as you want to any entityModel object, depending on your resource.
		 */		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		// Once we have the link, we can add it to the EntityModel object before sending it.
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}

	// Third REST API -> 3. Save a user
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

		// /users/4 -> /users/{id} -> user.getId()

		URI location = ServletUriComponentsBuilder // inbuilt class used to build uri
				.fromCurrentRequest() // gives the uri of the current req which is ("/users")
				.path("/{id}") // we want to append a path ("/{id}") to the uri ("/users")
				.buildAndExpand(savedUser.getId()) // replace {id} with the id of the new created user
				.toUri(); // convert this entire thing into a URI

		// We will use the ResponseEntity class to return a proper http response for
		// created.
		return ResponseEntity.created(location).build(); // check in Talend, we should get 201 response status back
	}

	/*
	 * NOTE : since we cant make a POST method request thru the browser, we need a
	 * REST API client to fire a POST request. use Talend API Tester extension in
	 * chrome for making POST requests. or, use POSTMAN app
	 */

	// Fourth REST API -> 4. Delete a specific user
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}
}
