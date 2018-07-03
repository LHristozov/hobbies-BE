package com.outdoors.hobbies.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.outdoors.hobbies.resources.DestinationResource;
import com.outdoors.hobbies.resources.MessagesResource;
import com.outdoors.hobbies.resources.UserResource;
import com.outdoors.hobbies.services.MessagesService;

@RestController
@RequestMapping(produces = "application/json")
public class MessagesController {
	
	@Autowired
	private MessagesService messagesService;
	
	@RequestMapping(value = "saveMessage", method = RequestMethod.POST)
	public void saveMessage(@RequestBody MessagesResource message) {
		messagesService.saveMessage(message);
	}
	
	
	@RequestMapping(value = "getMessages/{senderId}", method = RequestMethod.GET)
	public List<MessagesResource> getMessagesResourceByreceiver(@PathVariable String senderId) {
		
		List<MessagesResource> messages = new ArrayList<>();
		messagesService.getMessageBySender(senderId).forEach(d -> {
			messages.add(MessagesResource.toResource(d));
		});
		
		return messages;
	}
	
	@RequestMapping(value = "/updateMessage", method = RequestMethod.POST)
	public void updateStatus(@RequestBody Long id) {
		messagesService.updateMessage(id);
	}

	

}
