package com.example.demo.domain;

import com.example.demo.commons.agetworker.AgentWorkerChain;
import com.example.demo.commons.agetworker.AgentWorkerChainImpl;
import com.example.demo.commons.dto.CustomProperties;
import com.example.demo.utils.TaskInfo;
import com.example.demo.utils.dto.TaskInfoDto;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lp225484
 */
@Component
@Scope("singleton")
public class BaseHandler implements Runnable {
    Logger logger = LoggerFactory.getLogger(BaseHandler.class);

    private CustomProperties properties;

    public void setProperties(CustomProperties properties) {
        this.properties = properties;
    }

    public CustomProperties getProperties() {
        return properties;
    }

    public BaseHandler(CustomProperties properties) {
        this.properties = properties;
    }

    public BaseHandler() {
    }

    /**
     * handler agent chain
     *
     * @param properties
     */
    protected void handler(CustomProperties properties) {

    }

    @SneakyThrows
    @Override
    public void run() {
        String workerType = properties.getWorkerType();

        // handler(properties);
        AgentWorkerChain agentWorkerChain = new AgentWorkerChainImpl();
        for (TaskInfoDto taskInfo : TaskInfo.taskInfoConfig) {
            if (taskInfo.getName().equals(workerType)) {
                List<String> agentList = taskInfo.getAgentList();
                for (String agentName : agentList) {
                    try {
                        agentWorkerChain.addWorker(agentName);
                    } catch (ClassNotFoundException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
        }
        agentWorkerChain.run(properties);
    }
}
