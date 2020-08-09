package com.in28minutes.springboot.jpa.hibernate.h2.example.student;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.awt.print.Book;

@Slf4j
public class StudentRepositoryImpl implements CustomStudentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void customMethod() {
        log.info("test {}",em);
        Student parent = em.find(Student.class, 1L);
        em.lock(parent, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
    }
}