package com.outdoors.hobbies.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.outdoors.hobbies.models.MessagesModel;

@Repository
public interface MessagesRepository extends CrudRepository<MessagesModel, Long> {
	
	List<MessagesModel> findByReceiver(Long receiverId);
	
	List<MessagesModel> findBySender(Long senderId);

}
