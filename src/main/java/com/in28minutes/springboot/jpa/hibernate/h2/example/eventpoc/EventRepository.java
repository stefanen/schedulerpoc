package com.in28minutes.springboot.jpa.hibernate.h2.example.eventpoc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e LEFT JOIN EventLog el ON e.id=el.parent WHERE el.eventLogData=?1")
    Optional<Event> findByStefan(String searchValue);

    @Query("SELECT e FROM Event e LEFT JOIN EventLog el ON e.id=el.parent WHERE el.eventLogData IS NULL")
    List<Event> findAllByStefan();
}