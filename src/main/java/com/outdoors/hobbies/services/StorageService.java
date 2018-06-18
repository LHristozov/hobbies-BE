package com.outdoors.hobbies.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
 
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private static Path rootLocation = Paths.get("C:\\dev\\Projects\\hobbies-FE\\src\\assets\\upload-dir");
	
	public void store(MultipartFile file, String id) {
		rootLocation = Paths.get("C:\\dev\\Projects\\hobbies-FE\\src\\assets\\upload-dir\\" + id);
		File tmpDir = new File("C:\\dev\\Projects\\hobbies-FE\\src\\assets\\upload-dir\\" + id);
		
		boolean exists = tmpDir.exists();
		if(!exists) {
			init();
		}
		
		try {
			Files.copy(file.getInputStream(), StorageService.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
	public void storeDestinationImg(MultipartFile file, String name) {
		rootLocation = Paths.get("C:\\dev\\Projects\\hobbies-FE\\src\\assets\\upload-dir\\" + name);
		File tmpDir = new File("C:\\dev\\Projects\\hobbies-FE\\src\\assets\\upload-dir\\" + name);
		
		boolean exists = tmpDir.exists();
		if(!exists) {
			init();
		}
		
		int index = getNumberOfFiles(rootLocation.toAbsolutePath().toString());
		index++;
		
		int indexOfPrefix = file.getOriginalFilename().indexOf('.');
		String endingPrefix = file.getOriginalFilename().substring(indexOfPrefix);
		
		try {
			Files.copy(file.getInputStream(), StorageService.rootLocation.resolve(Integer.toString(index) + endingPrefix));
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}
 
	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
 
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
 
	private int getNumberOfFiles(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		return listOfFiles.length;
		
	}
	public static void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
	
	public ArrayList<String> getDestinationImgs(String name) {
		ArrayList<String> imgNames = new ArrayList<String>();
		rootLocation = Paths.get("C:\\dev\\Projects\\hobbies-FE\\src\\assets\\upload-dir\\" + name);
		File folder = new File(rootLocation.toAbsolutePath().toString());
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			imgNames.add(file.getName());
			
		}
		
		
		return imgNames;
	}
	
	
}