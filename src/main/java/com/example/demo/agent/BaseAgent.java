package com.example.demo.agent;

import com.example.demo.commons.agetworker.AgentWorker;
import com.example.demo.commons.dto.CustomProperties;
import com.example.demo.config.hotdeploy.ExcludeScan;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lp225484
 */
@Slf4j
@Component
@ExcludeScan
public class BaseAgent implements AgentWorker {

    Logger logger = LoggerFactory.getLogger(BaseAgent.class);
    String currentAgentName;

    @Override
    public void execute(CustomProperties custom) {
        this.currentAgentName = this.getClass().getSimpleName();
        long start = System.currentTimeMillis();
        logger.info(custom.getWorkerType() + ": BaseAgent execute start");
        doPreMajor(custom);
        doMajor(custom);
        doPostMajor(custom);
        logger.info(custom.getWorkerType() + ": agent run success timeCost:" + (System.currentTimeMillis() - start));
    }

    /**
     * 主任务执行前准备工作
     *
     * @param customProperties
     */
    protected void doPreMajor(CustomProperties customProperties) {

    }

    /**
     * agent主任务执行
     *
     * @param customProperties
     */
    protected void doMajor(CustomProperties customProperties) {

    }

    /**
     * agent执行完后的收尾工作
     *
     * @param customProperties
     */
    protected void doPostMajor(CustomProperties customProperties) {

    }
}
