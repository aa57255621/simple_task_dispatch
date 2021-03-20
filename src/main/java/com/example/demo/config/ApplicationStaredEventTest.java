package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * spring容器调用ConfigurableApplicationContext的start()方法时，触发该事件
 */
@Configuration
public class ApplicationStaredEventTest implements ApplicationListener<ApplicationStartedEvent> {


    @Qualifier("textOutputGraphUtil")
    @Autowired
    private TextOutputGraphUtil textOutputGraphUtil;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        textOutputGraphUtil.outputGraph("任务调度系统");
    }


    /**
     * 测试通过注解注入bean
     */
    @Bean("textOutputGraphUtil")
    public TextOutputGraphUtil textOutputGraphUtil(){
        return new TextOutputGraphUtil();
    }
}
