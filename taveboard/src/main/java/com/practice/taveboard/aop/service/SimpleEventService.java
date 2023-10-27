package com.practice.taveboard.aop.service;

import com.practice.taveboard.aop.EventService;
import com.practice.taveboard.aop.annotations.TrackTime;
import org.springframework.stereotype.Component;

@Component
public class SimpleEventService implements EventService {

    @TrackTime
    @Override
    public void createEvent(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Created an event.");
    }

    @Override
    public void publishEvent(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Published an event.");
    }

    @TrackTime
    @Override
    public void deleteEvent(){
        System.out.println("Delete an event.");
    }
}
