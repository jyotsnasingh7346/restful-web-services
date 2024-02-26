package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

// we want spring to manage this, so we will make it a component
@Component
public class UserDAOService {

	// DAOs have methods to interact with the data

	// METHODS -> 1. findAllUsers(), 2. saveUser(user), 3. findOneUser(id)
	// DATA -> For data, we will communicate with JPA/ Hibernate > Database,
	// But for now, Lets just create a static list for UserDAOService

	private static List<User> users = new ArrayList<>();

	private static int userCount = 0;

	static {
		users.add(new User(++userCount, "Ram", LocalDate.now().minusYears(27)));
		users.add(new User(++userCount, "Sita", LocalDate.now().minusYears(25)));
		users.add(new User(++userCount, "Hanuman", LocalDate.now().minusYears(21)));
	}

	// 1. findAllUsers()
	public List<User> findAll() {
		return users;
	}

	// 2. findOneUser(id)
	public User findById(int id) {

//		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);

	}

	// 3. saveUser(user)
	public User save(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}

	// 4. deleteById(id)
	public void deleteById(int id) {

		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);

	}

}
