package com.outdoors.hobbies.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.outdoors.hobbies.resources.DestinationResource;
import com.outdoors.hobbies.resources.EventsResource;
import com.outdoors.hobbies.services.EventsService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(value = "/events", produces = "application/json")
public class EventsController {

	@Autowired
	private EventsService eventsService;
	
	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	public void saveEvent(@RequestBody EventsResource event) {
		eventsService.saveEvents(event);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<EventsResource> getAllEvents() {
		List<EventsResource> events = new ArrayList<>();
		eventsService.getAllEvents().forEach(d -> {
			events.add(EventsResource.toResource(d));
		});
		return events;
	}

	@RequestMapping(value = "event-info/{name}", method = RequestMethod.GET)
	public EventsResource getEventByName(@PathVariable String name) {
		return EventsResource.toResource(eventsService.getEventByName(name));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EventsResource getEventById(@PathVariable String id) {
		return EventsResource.toResource(eventsService.getEventById(Long.getLong(id)));
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteEventById(@RequestBody EventsResource event) {
		eventsService.deleteEventBy(event.getId());
	}
	
	@RequestMapping(value = "/registerForEvent", method = RequestMethod.POST)
	public void registerForEvent(@RequestBody EventsResource event) {
		eventsService.registerForEvent(event);
	}
	
	@RequestMapping(value = "/getEventParticipants/{name}", method = RequestMethod.GET)
	public ArrayList<String> getAllEventComments(@PathVariable String name) {
		return eventsService.getEventParticipants(name);
	}
	
	@RequestMapping(value = "/getEventsByUser/{name}", method = RequestMethod.GET)
	public List<EventsResource> getEventsByUser(@PathVariable String name) {
		
		List<EventsResource> userEvents = new ArrayList<>();
		eventsService.getEventsByUser(name).forEach(d -> {
			userEvents.add(EventsResource.toResource(d));
		});
				
		return userEvents;
	}
	
}
