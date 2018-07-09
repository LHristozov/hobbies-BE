package com.outdoors.hobbies.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EVENTS")
public class EventsModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	@Size(min = 1, max = 4000)
	@Column(name = "description")
	private String description;
	
	@NotNull
	private Date eventDate;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_point_id")
	private MeetingPointModel meetingPoint;
	
	@ManyToOne()
    @JoinColumn(name = "user_id")
	private User owner;
	
    @OneToMany(mappedBy = "event")
    private List<CommentModel> comments;
    
    @ManyToOne()
    @JoinColumn(name = "destination_id")
    private DestinationModel eventDestination;
    
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoryModel category;
    	
    @ManyToMany()
    @JoinTable(name = "USER_EVENT", 
    	joinColumns = @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID"), 
    	inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"))
	private List<User> participants;

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

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public MeetingPointModel getMeetingPoint() {
		return meetingPoint;
	}

	public void setMeetingPoint(MeetingPointModel meetingPoint) {
		this.meetingPoint = meetingPoint;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public List<CommentModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}

	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
	}

	public DestinationModel getEventDestination() {
		return eventDestination;
	}

	public void setEventDestination(DestinationModel eventDestination) {
		this.eventDestination = eventDestination;
	}
	

}
