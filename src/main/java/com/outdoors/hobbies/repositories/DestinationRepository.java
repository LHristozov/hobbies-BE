package com.outdoors.hobbies.repositories;

import org.springframework.data.repository.CrudRepository;

import com.outdoors.hobbies.models.DestinationModel;

public interface DestinationRepository extends CrudRepository<DestinationModel, Long> {
	
	DestinationModel findByName(String name);
	

}
