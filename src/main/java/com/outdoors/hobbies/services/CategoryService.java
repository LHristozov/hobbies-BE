package com.outdoors.hobbies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outdoors.hobbies.models.CategoryModel;
import com.outdoors.hobbies.repositories.CategoryRepository;


@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;

	}
	
	public Iterable<CategoryModel> getAllCategories() {
		return categoryRepository.findAll();
	}

}
