package com.outdoors.hobbies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.outdoors.hobbies.models.CommentModel;
import com.outdoors.hobbies.models.DestinationModel;

public interface CommentRepository extends CrudRepository<CommentModel, Long> {
	
	CommentModel getAllByDestination(DestinationModel destination);
	
	@Query(value = "SELECT * FROM COMMENTS WHERE DESTINATION_ID = ?1", nativeQuery = true)
	List<CommentModel> findAllByDestination(long destination_id);

}
