package ist.challenge.lie_samsudin.challenge.controller;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ist.challenge.lie_samsudin.challenge.entity.User;
import ist.challenge.lie_samsudin.challenge.service.RegistrasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;


@ComponentScan(basePackageClasses=RegistrasiController.class)
@RestController
@RequestMapping("/api")
public class RegistrasiController {
	
	private RegistrasiService service;
	private static final Logger log = LoggerFactory.getLogger(RegistrasiController.class);

	public RegistrasiController() {

	}

	@Autowired
	public RegistrasiController(RegistrasiService service) {
		this.service = service;
	}

	// URI - http://localhost:8081/api/users
	// RequestMapping name at the top of this file is "/api"
	// GetMapping below is "/users"
	@GetMapping("/users")
	public ResponseEntity<List<User>> retrieveAllUsers() {
		// read from database
		List<User> users = service.findAll();
		log.info("Users list size= {}", users.size());
		if (users.size() == 0) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users); // return 200, with json body
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id) {
		User user = service.findById(id);
		if (user != null) {
			return ResponseEntity.ok(user); // return 200, with json body
		} else {
			log.info("User with id={} not found", id);
			return ResponseEntity.notFound().build();
		}

	}

	// URI - http://localhost:8081/api/users
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		// save to database
		User newUser = service.save(user);
		if (newUser != null) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newUser.getId()).toUri();
			return ResponseEntity.created(location).build();
		} else {
			log.info("User returned is NULL - Duplicate User found in the database");
			return ResponseEntity.unprocessableEntity().build();
		}

	}

	// URI - http://localhost:8081/api/users/{id}
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {
		user.setId(id);
		if (service.update(user)) {
			return ResponseEntity.noContent().build();
		} else {
			log.info("User with id= {} not found", id);
			return ResponseEntity.notFound().build();
		}

	}

	// URI - http://localhost:8081/api/users/{id}
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id) {
		if (service.deleteById(id)) {
			return ResponseEntity.noContent().build();
		} else {
			log.info("User with id= {} not found", id);
			return ResponseEntity.notFound().build();
		}

	}
}

