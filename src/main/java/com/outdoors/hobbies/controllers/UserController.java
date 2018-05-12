package com.outdoors.hobbies.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.outdoors.hobbies.resources.UserResource;
import com.outdoors.hobbies.services.UserService;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void saveUser(@RequestBody UserResource user) {
		userService.saveUser(user);
	}

	@RequestMapping(value = "/getUserByUsername/{username}", method = RequestMethod.GET)
	public UserResource getUserByUsername(@PathVariable String username) {
		return UserResource.toResource(userService.getUserByUsername(username));
	}

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public List<UserResource> getAllUsers() {
		List<UserResource> users = new ArrayList<>();
		userService.getAllUsers().forEach(d -> {
			users.add(UserResource.toResource(d));
		});
		return users;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public void updateUser(@PathVariable Long id, @RequestBody UserResource user) {
		userService.saveUser(user);
	}

}
