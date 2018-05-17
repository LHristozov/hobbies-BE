package com.outdoors.hobbies.resources;

import java.util.Date;

import com.outdoors.hobbies.models.EventsModel;
import com.outdoors.hobbies.models.User;
import com.outdoors.hobbies.models.UserInfoModel;

public class UserInfoResource {

	private Long id;

	private UserResource user;

	private String interests;

	private String job;

	private String livingLocation;

	private Date birthDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserResource getUser() {
		return user;
	}

	public void setUser(UserResource user) {
		this.user = user;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getLivingLocation() {
		return livingLocation;
	}

	public void setLivingLocation(String livingLocation) {
		this.livingLocation = livingLocation;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public static UserInfoResource toResource(UserInfoModel userInfoModel) {
		UserInfoResource userInfoResource = new UserInfoResource();
		if (userInfoModel != null) {
			userInfoResource.setBirthDate(userInfoModel.getBirthDate());
			userInfoResource.setId(userInfoModel.getId());
			userInfoResource.setInterests(userInfoModel.getInterests());
			userInfoResource.setJob(userInfoModel.getJob());
			userInfoResource.setLivingLocation(userInfoModel.getLivingLocation());
			// userInfoResource.setUser(UserResource.toResource(userInfoModel.getUser()));
		}
		return userInfoResource;
	}

	public UserInfoModel toModel() {
		UserInfoModel userInfoModel = new UserInfoModel();

		userInfoModel.setBirthDate(birthDate);
		userInfoModel.setId(id);
		userInfoModel.setInterests(interests);
		userInfoModel.setJob(job);
		userInfoModel.setLivingLocation(livingLocation);
		// userInfoModel.setUser(user.toModel());

		return userInfoModel;
	}
}
