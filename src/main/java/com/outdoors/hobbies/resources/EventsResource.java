package com.outdoors.hobbies.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.outdoors.hobbies.models.DestinationModel;
import com.outdoors.hobbies.models.EventsModel;
import com.outdoors.hobbies.models.MeetingPointModel;
import com.outdoors.hobbies.models.User;

public class EventsResource {

	private Long id;

	private String name;

	private String description;

	private Date eventDate;

	private MeetingPointResource meetingPoint;

	private UserResource owner;

	private List<CommentResource> comment;

	private List<CategoryResource> categories;

	private List<UserResource> participants;

	private DestinationResource destination;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public MeetingPointResource getMeetingPoint() {
		return meetingPoint;
	}

	public void setMeetingPoint(MeetingPointResource meetingPoint) {
		this.meetingPoint = meetingPoint;
	}

	public UserResource getOwner() {
		return owner;
	}

	public void setOwner(UserResource owner) {
		this.owner = owner;
	}

	public List<CommentResource> getComment() {
		return comment;
	}

	public void setComment(List<CommentResource> comment) {
		this.comment = comment;
	}

	public List<UserResource> getParticipants() {
		return participants;
	}

	public void setParticipants(List<UserResource> participants) {
		this.participants = participants;
	}

	public List<CategoryResource> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryResource> categories) {
		this.categories = categories;
	}

	public DestinationResource getDestination() {
		return destination;
	}

	public void setDestination(DestinationResource destination) {
		this.destination = destination;
	}

	public static EventsResource toResource(EventsModel eventsModel) {
		EventsResource eventsResource = new EventsResource();
		eventsResource.setDescription(eventsModel.getDescription());
		eventsResource.setName(eventsModel.getName());
		eventsResource.setId(eventsModel.getId());

		// if (eventsModel.getComments() != null) {
		// eventsResource.setComment(
		// eventsModel.getComments().stream().map(CommentResource::toResource).collect(Collectors.toList()));
		// }

		if (eventsModel.getCategories() != null) {
			eventsResource.setCategories(eventsModel.getCategories().stream().map(CategoryResource::toResource)
					.collect(Collectors.toList()));
		}

		if (eventsModel.getEventDate() == null || eventsModel.getEventDate().toString().equals("0000-00-00 00:00:00")) {
			// TODO: its possible this line here to mess up dates!
			eventsResource.setEventDate(new Date());
		} else {
			eventsResource.setEventDate(eventsModel.getEventDate());
		}

		eventsResource.setMeetingPoint(MeetingPointResource.toResource(eventsModel.getMeetingPoint()));

		eventsResource.setOwner(UserResource.toResource(eventsModel.getOwner()));
		eventsResource.getOwner().setUserInfoResource(null);

		eventsResource.setDestination(DestinationResource.toResource(eventsModel.getEventDestination()));

		if (eventsModel.getParticipants() != null) {
			List<UserResource> participants = new ArrayList<UserResource>();
			
			for(User u : eventsModel.getParticipants()) {
				UserResource us = UserResource.toResource(u);
				us.setUserInfoResource(null);
				participants.add(us);
				
			}
			eventsResource.setParticipants(participants);
			
//			eventsResource.setParticipants(
//					eventsModel.getParticipants().stream().map(UserResource::toResource).collect(Collectors.toList()));
		}

		return eventsResource;
	}

	public EventsModel toModel() {
		EventsModel eventsModel = new EventsModel();
		eventsModel.setDescription(description);
		eventsModel.setName(name);
		eventsModel.setEventDestination(destination.toModel());
		eventsModel.setOwner(owner.toModel());
		eventsModel.setEventDate(eventDate);
		eventsModel.setId(id);
		if (meetingPoint != null) {
			eventsModel.setMeetingPoint(meetingPoint.toModel());
		}
		if(participants != null && !participants.isEmpty()) {
			ArrayList<User> userList = new ArrayList<User>();
			for (UserResource participant : participants) {
				userList.add(participant.toModel());
			}
			
			eventsModel.setParticipants(userList);	
				
		}
		// TODO: add all fields

		return eventsModel;
	}

}
