package com.outdoors.hobbies.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		EventsModel event = eventsRepository.findOne(eventsResource.getId());
		List<User> userList = new ArrayList<>();
		for (User u : event.getParticipants()) {
			userList.add(u);
		}
		userList.add(userRepository.findByUsername(eventsResource.getOwner().getUsername()));

		event.setParticipants(userList);

		eventsRepository.save(event);
	}

	public void deleteEvent(EventsResource eventsResource) {
		if (eventsRepository == null) {
			return;
		}
		EventsModel event = eventsRepository.findOne(eventsResource.getId());

		eventsRepository.delete(event);
	}

	public ArrayList<String> getEventParticipants(String name) {
		EventsModel event = eventsRepository.findByName(name);
		List<User> participants = event.getParticipants();
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

		for (EventsModel em : allEvents) {
			if (em.getParticipants().contains(user)) {
				userEvents.add(em);
			}
		}

		return userEvents;

	}

	public EventsResource getNextEventByUser(String name) {
		List<EventsModel> events = getEventsByUser(name);
		EventsModel currentEvent = null;

		for (EventsModel event : events) {
			if (currentEvent == null) {
				currentEvent = event;
				continue;
			}
			if (event.getEventDate().compareTo(currentEvent.getEventDate()) < 0) {
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

	public EventsModel getRecommendedEvent(String username) {
		List<EventsModel> allEvents = (List<EventsModel>) eventsRepository.findAll();
		EventsModel recommendedEvent = null;
		User user = userRepository.findByUsername(username);
		String [] userIntr = {};
		if(user.getUserInfo().getInterests() != null) {
			userIntr = user.getUserInfo().getInterests().replaceAll(" ", "").split(",");
		}
		int max = 0;
		EventsModel mostPopularEvent = new EventsModel();
		
		Map<String, Integer> hm = new HashMap<String, Integer>();
		
		for(EventsModel event : allEvents) {
			if(event.getEventDate().before(new Date())) {
				continue;
			}
		
			if(event.getCategory() != null) {
				String eventCategory = event.getCategory().getDescription() + " " + event.getCategory().getName();
				for(String str : userIntr) {
					if(eventCategory.contains(str)) {
						if(hm.get(event.getName()) == null) {
							hm.put(event.getName(), 1);
							continue;
						}
						hm.put(event.getName(), hm.get(event.getName()) + 1);
					}
				}
			}
			
			if(event.getDescription() != null) {
				String eventDescription = event.getDescription();
				for(String str : userIntr) {
					if(eventDescription.contains(str)) {
						if(hm.get(event.getName()) == null) {
							hm.put(event.getName(), 1);
							continue;
						}
						hm.put(event.getName(), hm.get(event.getName()) + 1);
					}
				}
				
			}
			
			
			if(event.getParticipants() != null && event.getParticipants().size() >= max) {
				mostPopularEvent = event;
				max = event.getParticipants().size();
			}
		
		}
		
		String mostPopular = "";	
		
//		final EventsModel eventt = mostPopularEvent;
//		mostPopular = hm.keySet().stream().filter(key -> key.equals(eventt.getName())).findFirst().get();
	
		for(String key : hm.keySet()) {
			if(key.equals(mostPopularEvent.getName())) {
				mostPopular = key;
				break;
			}
		}
		if(mostPopular.equals("")) {
			mostPopular = mostPopularEvent.getName();
		}
		
		if(hm.get(mostPopularEvent.getName()) == null) {
			hm.put(mostPopularEvent.getName(), 1);
		}else {
			hm.put(mostPopular, hm.get(mostPopularEvent.getName()) + 1);
		}
//		int maxValue = Collections.max(hm.values());
		String recommendedEventName = Collections.max(hm.entrySet(), (l,r) -> l.getValue() - r.getValue()).getKey();
		
		for(EventsModel e : allEvents) {
			if(e.getName().equals(recommendedEventName)) {
				recommendedEvent = e;
			}
		}
		
		return recommendedEvent;
	}

	public void deleteEventBy(Long id) {
		eventsRepository.delete(id);
	}

}
