package com.outdoors.hobbies.repositories;

import org.springframework.data.repository.CrudRepository;

import com.outdoors.hobbies.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
