package com.outdoors.hobbies.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.outdoors.hobbies.resources.CommentResource;
import com.outdoors.hobbies.services.CommentService;

@RestController
@RequestMapping(produces = "application/json")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "saveDestinationComment", method = RequestMethod.POST)
	public void saveDestinationComment(@RequestBody CommentResource comment) {
		commentService.saveDestinationComment(comment);
	}
	
	@RequestMapping(value = "saveEventComment", method = RequestMethod.POST)
	public void saveEventComment(@RequestBody CommentResource comment) {
		commentService.saveEventComment(comment);
	}

//	@RequestMapping(value = "/getDestinationComments/{name}", method = RequestMethod.GET)
//	public List<CommentResource> getAllDestinationComments() {
//		List<CommentResource> comments = new ArrayList<>();
//		commentService.getAllDestinationComments().forEach(d -> {
//			comments.add(CommentResource.toResource(d));
//		});
//		return comments;
//	}
	
	
	@RequestMapping("/getDestinationComments/{name}")
	public ArrayList<String> getDestinationComments(@PathVariable String name) {
		return commentService.getDestinationComments(name);
	}
	
	@RequestMapping(value = "/getEventComments/{name}", method = RequestMethod.GET)
	public ArrayList<String> getAllEventComments(@PathVariable String name) {
		return commentService.getEventComments(name);
	}

		
}
