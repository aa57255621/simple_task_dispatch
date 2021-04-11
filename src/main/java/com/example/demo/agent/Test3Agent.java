package com.example.demo.agent;

import com.example.demo.commons.dto.CustomProperties;
import com.example.demo.config.hotdeploy.ExcludeScan;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @author lp225484
 */
@Component
@ExcludeScan
public class Test3Agent extends BaseAgent implements DisposableBean {
    @Override
    protected void doPreMajor(CustomProperties customProperties) {
        System.out.println( currentAgentName + ": 执行Test3Agent的doPreMajor()方法");
        super.doPreMajor(customProperties);
    }

    @Override
    protected void doMajor(CustomProperties customProperties) {
        super.doMajor(customProperties);
    }

    @Override
    protected void doPostMajor(CustomProperties customProperties) {
        System.out.println(currentAgentName + ": 执行Test3Agent的doPostMajor()方法");
        super.doPostMajor(customProperties);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("调用destroy()");

    }

}
