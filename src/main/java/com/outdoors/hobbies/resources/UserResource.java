package com.outdoors.hobbies.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.outdoors.hobbies.models.DestinationModel;
import com.outdoors.hobbies.models.User;

public class UserResource {

	private Long id;

	private String username;

	private String password;

	private String firstname;

	private String lastname;

	private String email;
	
	private String image;

	private List<EventsResource> createdEvents;

	private List<CommentResource> comments;

	private List<EventsResource> subscribedEvents;

	// private List<Authority> authorities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<EventsResource> getCreatedEvents() {
		return createdEvents;
	}

	public void setCreatedEvents(List<EventsResource> createdEvents) {
		this.createdEvents = createdEvents;
	}

	public List<CommentResource> getComments() {
		return comments;
	}

	public void setComments(List<CommentResource> comments) {
		this.comments = comments;
	}

	public List<EventsResource> getSubscribedEvents() {
		return subscribedEvents;
	}

	public void setSubscribedEvents(List<EventsResource> subscribedEvents) {
		this.subscribedEvents = subscribedEvents;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public static UserResource toResource(User userModel) {
		UserResource userResource = new UserResource();
		if(userModel == null) {
			return userResource;
		}
		userResource.setUsername(userModel.getUsername());
		userResource.setEmail(userModel.getEmail());
		userResource.setFirstname(userModel.getFirstname());
		userResource.setLastname(userModel.getPassword());
		userResource.setId(userModel.getId());
		userResource.setImage(userModel.getImage());
		userResource.setPassword(userModel.getPassword());

//		if (userModel.getComments() != null) {
//			userResource.setComments(
//					userModel.getComments().stream().map(CommentResource::toResource).collect(Collectors.toList()));
//		}

//		if (userModel.getCreatedEvents() != null) {
//			userResource.setCreatedEvents(
//					userModel.getCreatedEvents().stream().map(EventsResource::toResource).collect(Collectors.toList()));
//		}
//		if (userModel.getSubscribedEvents() != null) {
//			userResource.setSubscribedEvents(userModel.getSubscribedEvents().stream().map(EventsResource::toResource)
//					.collect(Collectors.toList()));
//		}
		

		return userResource;
	}
	
	
	
	public User toModel() {
		User user = new User();
		user.setEmail(email);;
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPassword(password);
		user.setUsername(username);
		user.setId(id);
		user.setImage(image);
		
		return user;
	}
	

}
