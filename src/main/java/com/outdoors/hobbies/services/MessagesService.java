package com.outdoors.hobbies.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outdoors.hobbies.models.MessagesModel;
import com.outdoors.hobbies.repositories.MessagesRepository;
import com.outdoors.hobbies.resources.MessagesResource;

@Service
public class MessagesService {
	
	private final MessagesRepository messagesRepository;

	@Autowired
	public MessagesService(MessagesRepository messagesRepository) {
		this.messagesRepository = messagesRepository;
	}
	
	public void saveMessage(MessagesResource msg) {
		msg.setDate(new Date());
		messagesRepository.save(msg.toModel());
	}
	
	public List<MessagesModel> getMessageByReceiver(Long receiverId) {
		List<MessagesModel> masseges = messagesRepository.findByReceiver(receiverId);
		return masseges;
	}
	
	
	
	
	
	

}
