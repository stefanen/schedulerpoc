package com.in28minutes.springboot.jpa.hibernate.h2.example.eventpoc;

import com.in28minutes.springboot.jpa.hibernate.h2.example.student.StudentChild;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@Setter
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String eventData;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", cascade = CascadeType.ALL)
	private List<EventLog> childList = new ArrayList<>();


	public Event() {
		super();
	}

	public Event(Long id, String eventData, List<EventLog> childList) {
		this.id = id;
		this.eventData = eventData;
		this.childList = childList;
	}

	public Event(Long id, String eventData) {
		this.id = id;
		this.eventData = eventData;
	}

	@Override
	public String toString() {
		return "Event{" +
				"id=" + id +
				", eventData='" + eventData + '\'' +
				", childList=" + childList +
				'}';
	}
}
