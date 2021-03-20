package com.example.demo.agent;

import com.example.demo.commons.dto.CustomProperties;
import org.springframework.stereotype.Component;

@Component
public class Test3Agent extends BaseAgent{
    @Override
    protected void doPreMajor(CustomProperties customProperties) {
        System.out.println(customProperties.getWorkerType() + ": 执行Test3Agent的doPreMajor()方法");
        super.doPreMajor(customProperties);
    }

    @Override
    protected void doMajor(CustomProperties customProperties) {
        super.doMajor(customProperties);
    }

    @Override
    protected void doPostMajor(CustomProperties customProperties) {
        System.out.println(customProperties.getWorkerType() + ": 执行Test3Agent的doPostMajor()方法");
        super.doPostMajor(customProperties);
    }
}
