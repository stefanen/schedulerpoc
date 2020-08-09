package com.in28minutes.springboot.jpa.hibernate.h2.example.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>, CustomStudentRepository {

}