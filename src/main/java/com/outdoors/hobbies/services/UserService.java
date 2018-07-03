package com.outdoors.hobbies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outdoors.hobbies.models.User;
import com.outdoors.hobbies.repositories.UserRepository;
import com.outdoors.hobbies.resources.UserInfoResource;
import com.outdoors.hobbies.resources.UserResource;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void saveUser(UserResource userResource) {
		if (userResource == null) {
			return;
		}
		if (userResource.getUserInfo() != null && userResource.getUserInfo().getUser() == null) {
			userResource.getUserInfo().setUser((userRepository.findOne(userResource.getId())));
		}
		userRepository.save(userResource.toModel());
	}
	
	public void UpdateStatus(UserResource userResource) {
		if (userResource == null) {
			return;
		}
		if (userResource.getUserInfo() != null && userResource.getUserInfo().getUser() == null) {
			userResource.getUserInfo().setUser((userRepository.findOne(userResource.getId())));
		}
		userRepository.save(userResource.toModel());
	}

	public User getUserById(Long id) {
		return userRepository.findOne(id);
	}
	
	public User getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
	
	public String getUserNameById(Long id) {
		return userRepository.findOne(id).getUsername();
	}

}
