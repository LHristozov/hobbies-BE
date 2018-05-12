package com.outdoors.hobbies.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.outdoors.hobbies.models.User;
import com.outdoors.hobbies.resources.UserResource;
import com.outdoors.hobbies.services.StorageService;
import com.outdoors.hobbies.services.UserService;

@Controller
@RequestMapping(produces = "application/json")
public class UploadController {

	@Autowired
	StorageService storageService;
	@Autowired
	private UserService userService;

	List<String> files = new ArrayList<String>();

	@PostMapping("/uploadImage/{id}")
	public ResponseEntity<String> handleFileUpload(@PathVariable String id, @RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			UserResource userToUpdate;
			// userToUpdate.setId(Long.parseLong(id));
			// userToUpdate.setImage(file.getOriginalFilename());
			storageService.store(file, id);
			files.add(file.getOriginalFilename());
			User userr = userService.getUserById(Long.parseLong(id));
			userToUpdate = UserResource.toResource(userr);
			userToUpdate.setImage(file.getOriginalFilename());
			userService.saveUser(userToUpdate);

			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@PostMapping("/uploadDestinationImage/{name}")
	public ResponseEntity<String> uploadDestinationImg(@PathVariable String name,
			@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.storeDestinationImg(file, name);
			// files.add(file.getOriginalFilename());

			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@GetMapping("/getDestinationImgs/{name}")
	@ResponseBody
	public ArrayList<String> getDestinationImgs(@PathVariable String name) {
		return storageService.getDestinationImgs(name);
	}

	@GetMapping("/getallfiles")
	public ResponseEntity<List<String>> getListFiles(Model model) {
		List<String> fileNames = files
				.stream().map(fileName -> MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(fileNames);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}