package com.example.demo.agent;

import com.example.demo.commons.dto.CustomProperties;
import org.springframework.stereotype.Component;

@Component
public class Test2Agent extends BaseAgent{
    @Override
    protected void doPreMajor(CustomProperties customProperties) {
        System.out.println(currentAgentName + ": 执行Test2Agent的doPreMajor()");
        super.doPreMajor(customProperties);
    }

    @Override
    protected void doMajor(CustomProperties customProperties) {
        super.doMajor(customProperties);
    }

    @Override
    protected void doPostMajor(CustomProperties customProperties) {
        super.doPostMajor(customProperties);
    }
}
