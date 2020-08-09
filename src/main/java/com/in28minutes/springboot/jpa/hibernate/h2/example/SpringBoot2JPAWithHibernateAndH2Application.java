package com.in28minutes.springboot.jpa.hibernate.h2.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBoot2JPAWithHibernateAndH2Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TransactionService core;

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2JPAWithHibernateAndH2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            try {
                core.doEventStuff();
            } catch (Exception e) {
                logger.info("transaction done exception caught", e);
            }
            logger.info("transaction done");
        }).start();

    }




}
