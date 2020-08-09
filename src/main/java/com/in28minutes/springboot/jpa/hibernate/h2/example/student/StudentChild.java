package com.in28minutes.springboot.jpa.hibernate.h2.example.student;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class StudentChild {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Student parent;


	private String childName;


	public StudentChild() {
		super();
	}

	public StudentChild(Long id, Student parent, String childName) {
		this.id = id;
		this.parent = parent;
		this.childName = childName;
	}

	public StudentChild(Student parent, String childName) {
		this.parent = parent;
		this.childName = childName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	@Override
	public String toString() {
		return "StudentChild{" +
				"id=" + id +
				", childName='" + childName + '\'' +
				'}';
	}
}
