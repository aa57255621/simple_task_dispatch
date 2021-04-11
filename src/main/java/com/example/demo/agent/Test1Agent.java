package com.example.demo.agent;

import com.example.demo.commons.dto.CustomProperties;
import com.example.demo.config.hotdeploy.ExcludeScan;
import org.springframework.stereotype.Component;

/**
 * @author lp225484
 */
@Component
@ExcludeScan
public class Test1Agent extends BaseAgent{
    @Override
    protected void doPreMajor(CustomProperties customProperties) {
        System.out.println(currentAgentName + ": Test1Agent   doPreMajor 11111111111111111test cdffdfdfompile");

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
