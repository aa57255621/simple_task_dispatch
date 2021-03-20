package com.example.demo.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * 自定义监听类
 */
public class EmailEvent extends ApplicationContextEvent {
    private String message;

    public EmailEvent(ApplicationContext source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmailEvent(ApplicationContext source) {
        super(source);
    }
}
