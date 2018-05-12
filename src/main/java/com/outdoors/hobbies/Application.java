package com.outdoors.hobbies;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.outdoors.hobbies.services.StorageService;

@SpringBootApplication
public class Application {

	@Resource
	StorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//StorageService.init();

		
	}

}
