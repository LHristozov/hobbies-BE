package com.outdoors.hobbies.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outdoors.hobbies.models.CommentModel;
import com.outdoors.hobbies.repositories.CommentRepository;
import com.outdoors.hobbies.repositories.DestinationRepository;
import com.outdoors.hobbies.repositories.EventsRepository;
import com.outdoors.hobbies.repositories.UserRepository;
import com.outdoors.hobbies.resources.CommentResource;
import com.outdoors.hobbies.resources.DestinationResource;
import com.outdoors.hobbies.resources.EventsResource;
import com.outdoors.hobbies.resources.UserResource;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final DestinationRepository destinationRepository;
	private final UserRepository userRepository;
	private final EventsRepository eventsRepository;

	@Autowired
	public CommentService(CommentRepository commentRepository, DestinationRepository destinationRepository,
			UserRepository userRepository, EventsRepository eventsRepository) {
		this.commentRepository = commentRepository;
		this.destinationRepository = destinationRepository;
		this.userRepository = userRepository;
		this.eventsRepository = eventsRepository;
	}

	public void saveDestinationComment(CommentResource commentResource) {
		if (commentResource == null) {
			return;
		}
		commentResource.setDate(new Date());
		commentResource.setDestination_id(DestinationResource
				.toResource(destinationRepository.findByName(commentResource.getDestination_id().getName())));

		commentResource.setUser_id(
				UserResource.toResource(userRepository.findByUsername(commentResource.getUser_id().getUsername())));
		commentRepository.save(commentResource.toModel());
	}

	public void saveEventComment(CommentResource commentResource) {
		if (commentResource == null) {
			return;
		}
		commentResource.setDate(new Date());
		commentResource.setEvent_id(
				EventsResource.toResource(eventsRepository.findByName(commentResource.getEvent_id().getName())));
		commentResource.setUser_id(
				UserResource.toResource(userRepository.findByUsername(commentResource.getUser_id().getUsername())));
		commentRepository.save(commentResource.toModel());
	}

	public ArrayList<String> getDestinationComments(String name) {
		Iterable<CommentModel> commentList = commentRepository.findAll();
		Iterator<CommentModel> iter = commentList.iterator();
		ArrayList<String> comments = new ArrayList<>();
		while (iter.hasNext()) {
			CommentModel cm = iter.next();
			if (cm.getDestination() != null && cm.getDestination().getName().equals(name)) {
				Date date = cm.getDate();
				String userName = cm.getAuthor().getUsername();
				String text = cm.getText();

				String comment = date + " " + "(" + userName + "): " + text;

				comments.add(comment);
			}
		}
		return comments;
	}

	public ArrayList<String> getEventComments(String name) {

		Iterable<CommentModel> commentList = commentRepository.findAll();
		Iterator<CommentModel> iter = commentList.iterator();
		ArrayList<String> comments = new ArrayList<>();
		while (iter.hasNext()) {
			CommentModel cm = iter.next();
			if (cm.getEvent() != null && cm.getEvent().getName().equals(name)) {
				Date date = cm.getDate();
				String userName = cm.getAuthor().getUsername();
				String text = cm.getText();

				String comment = date + " " + "(" + userName + "): " + text;

				comments.add(comment);
			}
		}
		return comments;

	}

}
