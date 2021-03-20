package com.example.demo.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
/**
 * 通过使用注解实现监听
 */
public class EventService {
    @EventListener
    public void accept(EmailEvent emailEvent){
        System.out.println("接收事件"+emailEvent.getMessage());
    }
}
