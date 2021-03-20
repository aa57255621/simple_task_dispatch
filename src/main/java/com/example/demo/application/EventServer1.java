package com.example.demo.application;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
/**
 * 通过实现ApplicationListener ，EmailEvent，实现监听
 */
public class EventServer1 implements ApplicationListener<EmailEvent> {
    @Override
    public void onApplicationEvent(EmailEvent event) {
        System.out.println("eventServer1 监听："+event.getMessage());
    }
}
