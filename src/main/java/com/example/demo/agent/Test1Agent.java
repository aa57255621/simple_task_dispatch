package com.example.demo.agent;

import com.example.demo.commons.dto.CustomProperties;
import com.example.demo.config.hotdeploy.ExcludeScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lp225484
 */
@Component
@ExcludeScan
public class Test1Agent extends BaseAgent {
    Logger logger = LoggerFactory.getLogger(Test1Agent.class);
    @Override
    protected void doPreMajor(CustomProperties customProperties) {
        logger.info(currentAgentName + ": Test1Agent   doPreMajor 11111111111111111test cdffdfdfompile");

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
