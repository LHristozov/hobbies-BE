package com.outdoors.hobbies.resources;


import com.outdoors.hobbies.models.CategoryModel;

public class CategoryResource {
	
	private Long id;
	private String name;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static CategoryResource toResource(CategoryModel categoryModel) {
		CategoryResource categoryResource = new CategoryResource();
		categoryResource.setName(categoryModel.getName());
		categoryResource.setDescription(categoryModel.getDescription());
		categoryResource.setId(categoryModel.getId());
		
		return categoryResource;
	}
	
	
}
