package com.outdoors.hobbies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.outdoors.hobbies.models.CategoryModel;

@Repository
public interface CategoryRepository  extends CrudRepository<CategoryModel, Long> {

}
