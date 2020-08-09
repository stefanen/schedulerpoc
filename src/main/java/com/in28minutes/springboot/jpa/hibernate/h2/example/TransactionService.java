package com.in28minutes.springboot.jpa.hibernate.h2.example;

import com.in28minutes.springboot.jpa.hibernate.h2.example.eventpoc.Event;
import com.in28minutes.springboot.jpa.hibernate.h2.example.eventpoc.EventLog;
import com.in28minutes.springboot.jpa.hibernate.h2.example.eventpoc.EventRepository;
import com.in28minutes.springboot.jpa.hibernate.h2.example.student.Student;
import com.in28minutes.springboot.jpa.hibernate.h2.example.student.StudentChild;
import com.in28minutes.springboot.jpa.hibernate.h2.example.student.DoNotUseStudentRepository;
import com.in28minutes.springboot.jpa.hibernate.h2.example.student.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class TransactionService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    StudentRepository repository;

    @Autowired
    EventRepository eventRepository;



    @Transactional(rollbackFor = Throwable.class, isolation = Isolation.READ_UNCOMMITTED)
    public void doStuff() {

        //Student student=repository.findById(10001L).get();
        //logger.info("Student id 10001 -> {}", student);
		/*
		Student studentToSave=new Student("John", "A1234657");
		StudentChild child1=new StudentChild(studentToSave, "baby1");
		StudentChild child2=new StudentChild(studentToSave, "baby2");

		studentToSave.setChildList(Arrays.asList(child1,child2));

		logger.info("Inserting -> {}", repository.save(studentToSave));
		*/

        Student sameStudentToSave = new Student(1L, "John", "A1234657", 1L);
        StudentChild samechild1 = new StudentChild(1L, sameStudentToSave, "baby1");
        StudentChild samechild2 = new StudentChild(2L, sameStudentToSave, "baby2_renamed");


        sameStudentToSave.setChildList(Arrays.asList(samechild1, samechild2));


        logger.info("Inserting -> {}", repository.save(sameStudentToSave));

        //sameStudentToSave.setVersion(2L);


       // logger.info("Inserting v2 -> {}", repository.save(sameStudentToSave));



        Student fetchedStudent = repository.findById(1L).get();


        fetchedStudent.setPassportNumber("newpassportA1");
        repository.save(fetchedStudent);


        repository.customMethod();

        logger.info("done");
        //throw new RuntimeException();
        //logger.info("Update 10003 -> {}", repository.save(new Student(10001L, "Name-Updated", "New-Passport")));

        //repository.deleteById(10002L);

        //logger.info("All users -> {}", repository.findAll());
    }

    //@Transactional(rollbackFor = Throwable.class, isolation = Isolation.READ_UNCOMMITTED)
    public void doEventStuff() {

        //Student student=repository.findById(10001L).get();
        //logger.info("Student id 10001 -> {}", student);
		/*
		Student studentToSave=new Student("John", "A1234657");
		StudentChild child1=new StudentChild(studentToSave, "baby1");
		StudentChild child2=new StudentChild(studentToSave, "baby2");

		studentToSave.setChildList(Arrays.asList(child1,child2));

		logger.info("Inserting -> {}", repository.save(studentToSave));
		*/

        Event firstEventToSave = new Event(1L, "myFirstEventData1");
        EventLog firstEventFirstLog = new EventLog(1L,firstEventToSave,"noticed11");
        EventLog secondEventFirstLog = new EventLog(2L,firstEventToSave,"noticed12");
        firstEventToSave.setChildList(Arrays.asList(firstEventFirstLog, secondEventFirstLog));
        logger.info("Inserting -> {}", eventRepository.save(firstEventToSave));



        Event secondEventToSave = new Event(2L, "mySecondEventData2");
        EventLog firstEventSecondLog = new EventLog(3L,secondEventToSave,"noticed21");
        EventLog secondEventSecondLog = new EventLog(4L,secondEventToSave,"noticed22");
        secondEventToSave.setChildList(Arrays.asList(firstEventSecondLog, secondEventSecondLog));
        logger.info("Inserting -> {}", eventRepository.save(secondEventToSave));



        Event thirdEventToSave = new Event(3L, "myEventData3");
        Event fourthEventToSave = new Event(4L, "myEventData4");
        logger.info("Inserting -> {}", eventRepository.save(thirdEventToSave));
        logger.info("Inserting -> {}", eventRepository.save(fourthEventToSave));


        //sameStudentToSave.setVersion(2L);


        // logger.info("Inserting v2 -> {}", repository.save(sameStudentToSave));



        //Event fetchedEvent = eventRepository.findById(1L).get();

        Event fetchedEvent = eventRepository.findByStefan("noticed22").get();
        logger.info("Fetched event -> {}", fetchedEvent);



        Event fetchedEvent2 = eventRepository.findByStefan("noticed11").get();
        logger.info("Fetched event -> {}", fetchedEvent2);


        logger.info("Trying to fetch all unprocessed event");
        eventRepository.findAllByStefan().stream().forEach(System.out::println);
        //ogger.info("Fetched event -> {}", fetchedEvent2);


/*
        fetchedStudent.setPassportNumber("newpassportA1");
        repository.save(fetchedStudent);
        repository.customMethod();
*/
        logger.info("done");
        //throw new RuntimeException();
        //logger.info("Update 10003 -> {}", repository.save(new Student(10001L, "Name-Updated", "New-Passport")));

        //repository.deleteById(10002L);

        //logger.info("All users -> {}", repository.findAll());
    }

}
