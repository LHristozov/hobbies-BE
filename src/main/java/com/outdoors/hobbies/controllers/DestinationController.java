package com.outdoors.hobbies.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.outdoors.hobbies.resources.DestinationResource;
import com.outdoors.hobbies.services.DestinationService;

@RestController
@RequestMapping(value = "/destination", produces = "application/json")
public class DestinationController {

	@Autowired
	private DestinationService destinationService;

	@RequestMapping(method = RequestMethod.POST)
	public void saveDestination(@RequestBody DestinationResource destination) {
		destinationService.saveDestination(destination);
	}

	@RequestMapping(value = "/getDestinations", method = RequestMethod.GET)
	public List<DestinationResource> getAllDestinations() {
		List<DestinationResource> destinations = new ArrayList<>();
		destinationService.getAllDestinations().forEach(d -> {
			destinations.add(DestinationResource.toResource(d));
		});
		return destinations;
	}

	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public DestinationResource getDestinationResourceByName(@PathVariable String name) {
		return DestinationResource.toResource(destinationService.getDestinationByName(name));
	}
	
}
