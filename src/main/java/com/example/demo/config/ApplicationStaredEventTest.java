package com.example.demo.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * spring容器调用ConfigurableApplicationContext的start()方法时，触发该事件
 */
public class ApplicationStaredEventTest implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        TextOutputGraphUtil.outputGraph("《任务调度系统》");
    }
}
