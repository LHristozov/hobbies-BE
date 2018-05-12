package com.outdoors.hobbies.resources;

import java.util.Date;

import com.outdoors.hobbies.models.MessagesModel;

public class MessagesResource {
	
	private Long id;
	private Long sender;
	private Long receiver;
	private Date date;
	private String content;
	private boolean seen;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSender() {
		return sender;
	}
	public void setSender(Long sender) {
		this.sender = sender;
	}
	public Long getReceiver() {
		return receiver;
	}
	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	public static MessagesResource toResource(MessagesModel messagesModel) {
		MessagesResource messagesResource = new MessagesResource();
		messagesResource.setId(messagesModel.getId());
		messagesResource.setSender(messagesModel.getSender());
		messagesResource.setReceiver(messagesModel.getReceiver());
		messagesResource.setDate(messagesModel.getDate());
		messagesResource.setContent(messagesModel.getContent());
		messagesResource.setSeen(messagesModel.isSeen());
		
		return messagesResource;
	}
	
	public MessagesModel toModel() {
		MessagesModel messagesModel = new MessagesModel();
		messagesModel.setId(id);
		messagesModel.setSender(sender);
		messagesModel.setReceiver(receiver);
		messagesModel.setDate(date);
		messagesModel.setContent(content);
		messagesModel.setSeen(seen);
		
		return messagesModel;
	}
	

}
