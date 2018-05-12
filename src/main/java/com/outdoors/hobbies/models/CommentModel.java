package com.outdoors.hobbies.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "COMMENT")
public class CommentModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Date date;
	
	@NotNull
	private String text;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	private User author;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
	private DestinationModel destination;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
	private EventsModel event;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public DestinationModel getDestination() {
		return destination;
	}

	public void setDestination(DestinationModel destination) {
		this.destination = destination;
	}

	public EventsModel getEvent() {
		return event;
	}

	public void setEvent(EventsModel event) {
		this.event = event;
	}
	
}
