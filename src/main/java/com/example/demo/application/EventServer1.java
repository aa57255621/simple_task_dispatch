package com.example.demo.application;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 通过实现ApplicationListener ，EmailEvent，实现监听
 * @author lp225484
 */
@Component
public class EventServer1 implements ApplicationListener<EmailEvent> {
    @Override
    public void onApplicationEvent(EmailEvent event) {
        System.out.println("eventServer1 监听：" + event.getMessage());
    }
}
