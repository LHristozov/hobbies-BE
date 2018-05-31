package com.outdoors.hobbies.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outdoors.hobbies.models.CommentModel;
import com.outdoors.hobbies.models.EventsModel;
import com.outdoors.hobbies.models.User;
import com.outdoors.hobbies.models.UserInfoModel;
import com.outdoors.hobbies.repositories.DestinationRepository;
import com.outdoors.hobbies.repositories.EventsRepository;
import com.outdoors.hobbies.repositories.UserRepository;
import com.outdoors.hobbies.resources.DestinationResource;
import com.outdoors.hobbies.resources.EventsResource;
import com.outdoors.hobbies.resources.UserResource;

@Service
public class EventsService {

	private final EventsRepository eventsRepository;
	private final UserRepository userRepository;
	private final DestinationRepository destinationRepository;

	@Autowired
	public EventsService(EventsRepository eventsRepository, UserRepository userRepository,
			DestinationRepository destinationRepository) {
		this.eventsRepository = eventsRepository;
		this.userRepository = userRepository;
		this.destinationRepository = destinationRepository;
	}

	public Iterable<EventsModel> getAllEvents() {
		return eventsRepository.findAll();
	}

	public void saveEvents(EventsResource eventsResource) {
		if (eventsRepository == null) {
			return;
		}
		eventsResource.setOwner(
				UserResource.toResource(userRepository.findByUsername(eventsResource.getOwner().getUsername())));
		
		User user = userRepository.findByUsername(eventsResource.getOwner().getUsername());
		eventsResource.setOwner(UserResource.toResource(user));
		UserInfoModel uim = eventsResource.getOwner().getUserInfo();
		uim.setUser(user);
		eventsResource.getOwner().setUserInfoResource(uim);
		
		eventsResource.setDestination(DestinationResource
				.toResource(destinationRepository.findByName(eventsResource.getDestination().getName())));
		eventsRepository.save(eventsResource.toModel());
	}
	
	public void registerForEvent(EventsResource eventsResource) {
		if (eventsRepository == null) {
			return;
		}
		ArrayList<UserResource> userList = new ArrayList<UserResource>();
		userList.add(UserResource.toResource((userRepository.findByUsername(eventsResource.getOwner().getUsername()))));
		for (UserResource participant : eventsResource.getParticipants()) {
			userList.add(participant);
			
		}
		eventsResource.setParticipants(userList);
		eventsResource.setOwner(
				UserResource.toResource(eventsRepository.findOne(eventsResource.getId()).getOwner()));
		eventsResource.setDestination(DestinationResource
				.toResource(destinationRepository.findByName(eventsResource.getDestination().getName())));
		
		eventsResource.setMeetingPoint(null);
	
		
		eventsRepository.save(eventsResource.toModel());
	}
	
	public ArrayList<String> getEventParticipants(String name) {
		EventsModel event = eventsRepository.findByName(name);
		List<User> participants =  event.getParticipants();
		ArrayList<String> participantsNames = new ArrayList<String>();
		for (User participant : participants) {
			participantsNames.add(participant.getUsername());
		}
		return participantsNames;

	}
	
	public List<EventsModel> getEventsByUser(String name) {
		List<EventsModel> allEvents = (List<EventsModel>) eventsRepository.findAll();
		User user = userRepository.findByUsername(name);
		List<EventsModel> userEvents = new ArrayList<EventsModel>();
		
		for(EventsModel em : allEvents) {
			if(em.getParticipants().contains(user)) {
				userEvents.add(em);
			}
		}
		
		return userEvents;

	}
	
	
	public EventsResource getNextEventByUser(String name) {
		List<EventsModel> events =  getEventsByUser(name);
		EventsModel currentEvent = null;
		
		for(EventsModel event : events) {
			if(currentEvent == null) {
				currentEvent = event;
				continue;
			}
			if(event.getEventDate().compareTo(currentEvent.getEventDate()) < 0) {
				currentEvent = event;
			}
		
		}
		
		return EventsResource.toResource(currentEvent);
		
	}

	public EventsModel getEventById(Long id) {
		return eventsRepository.findOne(id);
	}
	
	public EventsModel getEventByName(String name) {
		return eventsRepository.findByName(name);
	}

	public void deleteEventBy(Long id) {
		eventsRepository.delete(id);
	}

}
