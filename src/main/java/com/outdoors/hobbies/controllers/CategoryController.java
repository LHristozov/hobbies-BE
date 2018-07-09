package com.outdoors.hobbies.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.outdoors.hobbies.resources.CategoryResource;
import com.outdoors.hobbies.services.CategoryService;

@RestController
@RequestMapping(produces = "application/json")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
	public List<CategoryResource> getAllEvents() {
		List<CategoryResource> categories = new ArrayList<>();
		categoryService.getAllCategories().forEach(d -> {
			categories.add(CategoryResource.toResource(d));
		});
		return categories;
	}

}
