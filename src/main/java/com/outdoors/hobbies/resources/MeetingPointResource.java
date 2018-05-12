package com.outdoors.hobbies.resources;

import com.outdoors.hobbies.models.MeetingPointModel;


public class MeetingPointResource {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private String lat;
	
	private String lon;
	
	private EventsResource event;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public EventsResource getEvent() {
		return event;
	}

	public void setEvent(EventsResource event) {
		this.event = event;
	}
	
	public static MeetingPointResource toResource(MeetingPointModel meetingPointModel) {
		MeetingPointResource meetingPointResource = new MeetingPointResource();
		if(meetingPointModel == null) {
			return meetingPointResource;
		}
		
		meetingPointResource.setDescription(meetingPointModel.getDescription());
		meetingPointResource.setName(meetingPointModel.getName());
//		meetingPointResource.setEvent(EventsResource.toResource(meetingPointModel.getEvent()));
		meetingPointResource.setEvent(null);
		meetingPointResource.setId(meetingPointModel.getId());
		meetingPointResource.setLat(meetingPointModel.getLat());
		meetingPointResource.setLon(meetingPointModel.getLon());
		
		
		return meetingPointResource;
	}
	public MeetingPointModel toModel() {
		MeetingPointModel meetingPointModel = new MeetingPointModel();
		meetingPointModel.setDescription(description);
		meetingPointModel.setName(name);
		meetingPointModel.setLat(lat);
		meetingPointModel.setLon(lon);
		
		//TODO: add all fields
		
		return meetingPointModel;
	}
	
}
