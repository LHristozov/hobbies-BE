package com.outdoors.hobbies.resources;

import java.util.Date;

import com.outdoors.hobbies.models.CommentModel;
import com.outdoors.hobbies.models.EventsModel;

public class CommentResource {
	private Long id;
	private Date date;
	private String text;
	private DestinationResource destination_id;
	private UserResource user_id;
	private EventsResource event_id;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public DestinationResource getDestination_id() {
		return destination_id;
	}
	public void setDestination_id(DestinationResource destination_id) {
		this.destination_id = destination_id;
	}
	public UserResource getUser_id() {
		return user_id;
	}
	public void setUser_id(UserResource user_id) {
		this.user_id = user_id;
	}
	public EventsResource getEvent_id() {
		return event_id;
	}
	public void setEvent_id(EventsResource event_id) {
		this.event_id = event_id;
	}
	
	
	public static CommentResource toResource(CommentModel commentModel) {
		CommentResource commentResource = new CommentResource();
		commentResource.setText(commentModel.getText());
		commentResource.setDate(commentModel.getDate());
		commentResource.setId(commentModel.getId());
		if(commentModel.getEvent() != null) {
			commentResource.setEvent_id(EventsResource.toResource(commentModel.getEvent()));
		}
		commentResource.setUser_id(UserResource.toResource(commentModel.getAuthor()));
		if(commentModel.getDestination() != null) {
			commentResource.setDestination_id(DestinationResource.toResource(commentModel.getDestination()));
		}
		
		return commentResource;
	}
	
	public CommentModel toModel() {
		CommentModel commentModel = new CommentModel();
		
		commentModel.setAuthor(user_id.toModel());
		commentModel.setDate(date);
		
		if(destination_id != null) {
			commentModel.setDestination(destination_id.toModel());
		}else {
			commentModel.setDestination(null);
		}
		
		if(event_id != null) {
			commentModel.setEvent(event_id.toModel());
		}else {
			commentModel.setEvent(null);
		}
		
		commentModel.setText(text);

		return commentModel;
	}
	
}
