package com.outdoors.hobbies.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.outdoors.hobbies.models.DestinationModel;

public class DestinationResource {

	private Long id;
	private String name;
	private String description;
	private List<CommentResource> comment;
	private String lat;
	private String lon;
	private List<EventsResource> event;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CommentResource> getComment() {
		return comment;
	}

	public void setComment(List<CommentResource> comment) {
		this.comment = comment;
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

	public List<EventsResource> getEvent() {
		return event;
	}

	public void setEvent(List<EventsResource> event) {
		this.event = event;
	}

	public DestinationModel toModel() {
		DestinationModel destinationModel = new DestinationModel();
		destinationModel.setId(id);
		destinationModel.setDescription(description);
		destinationModel.setName(name);
		destinationModel.setLat(lat);
		destinationModel.setLon(lon);

		return destinationModel;
	}

	public static DestinationResource toResource(DestinationModel destinationModel) {
		DestinationResource destinationResource = new DestinationResource();
		if (destinationModel != null) {
			destinationResource.setDescription(destinationModel.getDescription());
			destinationResource.setName(destinationModel.getName());
			destinationResource.setId(destinationModel.getId());
			destinationResource.setLat(destinationModel.getLat());
			destinationResource.setLon(destinationModel.getLon());

//			if (destinationModel.getComments() != null) {
//				destinationResource.setComment(destinationModel.getComments().stream().map(CommentResource::toResource)
//						.collect(Collectors.toList()));
//			}
		}

		return destinationResource;
	}

}
