package com.example.demo.agent;

import com.example.demo.commons.dto.CustomProperties;
import com.example.demo.config.hotdeploy.ExcludeScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @author lp225484
 */
@Component
@ExcludeScan
public class Test3Agent extends BaseAgent implements DisposableBean {
    Logger logger = LoggerFactory.getLogger(Test3Agent.class);
    @Override
    protected void doPreMajor(CustomProperties customProperties) {
        logger.info(currentAgentName + ": 执行Test3Agent的doPreMajor()方法");
        super.doPreMajor(customProperties);
    }

    @Override
    protected void doMajor(CustomProperties customProperties) {
        super.doMajor(customProperties);
    }

    @Override
    protected void doPostMajor(CustomProperties customProperties) {
        logger.info(currentAgentName + ": 执行Test3Agent的doPostMajor()方法");
        super.doPostMajor(customProperties);
    }

    @Override
    public void destroy() throws Exception {
        logger.info("调用destroy()");

    }

}
