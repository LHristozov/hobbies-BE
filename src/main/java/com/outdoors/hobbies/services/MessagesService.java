package com.outdoors.hobbies.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outdoors.hobbies.models.MessagesModel;
import com.outdoors.hobbies.repositories.MessagesRepository;
import com.outdoors.hobbies.resources.MessagesResource;
import com.outdoors.hobbies.resources.UserResource;

@Service
public class MessagesService {
	
	private final MessagesRepository messagesRepository;

	@Autowired
	public MessagesService(MessagesRepository messagesRepository) {
		this.messagesRepository = messagesRepository;
	}
	
	public void saveMessage(MessagesResource msg) {
		if(msg == null) {
			return;
		}
		msg.setDate(new Date());
		messagesRepository.save(msg.toModel());
	}
	
	public List<MessagesModel> getMessageBySender(String receiverId) {
		List<MessagesModel> masseges = messagesRepository.findBySender(Long.parseLong(receiverId));
		return masseges;
	}
	
	
	public void updateMessage(Long id) {
		MessagesModel message = new MessagesModel();
		if (id == null) {
			return;
		}else {
			message = messagesRepository.findOne(id);
		}
		message.setSeen(true);
		messagesRepository.save(message);
	}
	
	
	
	
	

}
