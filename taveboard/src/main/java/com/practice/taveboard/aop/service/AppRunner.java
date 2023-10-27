package com.practice.taveboard.aop.service;

import com.practice.taveboard.aop.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class AppRunner implements ApplicationRunner {

    @Autowired
    EventService eventService;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        eventService.createEvent();
        eventService.publishEvent();
        eventService.deleteEvent();
    }
}
