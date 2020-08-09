package com.in28minutes.springboot.jpa.hibernate.h2.example.eventpoc;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EventLog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Event parent;


	private String eventLogData;


	public EventLog() {
		super();
	}

	public EventLog(Long id, Event parent, String eventLogData) {
		this.id = id;
		this.parent = parent;
		this.eventLogData = eventLogData;
	}

	public EventLog(Event parent, String eventLogData) {
		this.parent = parent;
		this.eventLogData = eventLogData;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventLogData() {
		return eventLogData;
	}

	public void setEventLogData(String eventLogData) {
		this.eventLogData = eventLogData;
	}


	@Override
	public String toString() {
		return "EventLog{" +
				"id=" + id +
				", eventLogData='" + eventLogData + '\'' +
				'}';
	}
}
