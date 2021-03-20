package com.example.demo.commons.agetworker;

import com.example.demo.commons.dto.CustomProperties;

public interface AgentWorkerChain {

    /**
     * 入参开始执行agent链
     * @param customProperties
     */
    void run(CustomProperties customProperties);

    /**
     * 添加一个agent节点
     * @param agentWorker
     * @return
     */
    AgentWorkerChain addWorker(AgentWorker agentWorker);

    /**
     * 添加多个agent节点
     * @param agentWorkers
     * @return
     */
    AgentWorkerChain addWorkers(AgentWorker ... agentWorkers);
}