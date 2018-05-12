package com.outdoors.hobbies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outdoors.hobbies.models.DestinationModel;
import com.outdoors.hobbies.repositories.DestinationRepository;
import com.outdoors.hobbies.resources.DestinationResource;

@Service
public class DestinationService {

	private final DestinationRepository destinationRepository;

	@Autowired
	public DestinationService(DestinationRepository destinationRepository) {
		this.destinationRepository = destinationRepository;
	}

	public Iterable<DestinationModel> getAllDestinations() {
		return destinationRepository.findAll();
	}

	public void saveDestination(DestinationResource destinationResource) {
		if (destinationResource == null) {
			return;
		}
		destinationRepository.save(destinationResource.toModel());
	}

	public DestinationModel getDestinationById(Long id) {
		return destinationRepository.findOne(id);
	}
	
	public DestinationModel getDestinationByName(String name) {
		return destinationRepository.findByName(name);
	}
	
	public void deleteDestinationById(Long id) {
		destinationRepository.delete(id);
	}
}
