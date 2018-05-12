package com.outdoors.hobbies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.outdoors.hobbies.models.EventsModel;

@Repository
public interface EventsRepository extends CrudRepository<EventsModel, Long> {
	
	EventsModel findByName(String name);

}
