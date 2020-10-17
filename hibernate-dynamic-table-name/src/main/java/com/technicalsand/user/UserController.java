package com.technicalsand.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String name){
		List<User> users = userService.getUsers(name);
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(users);
	}
}
