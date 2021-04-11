package com.example.demo.commons.agetworker;

import com.example.demo.commons.dto.CustomProperties;

public interface AgentWorkerChain {

    /**
     * 入参开始执行agent链
     * @param customProperties
     */
    void run(CustomProperties customProperties);

    /**
     * 添加一个agent节点名称
     * @param agentName
     * @return
     */
    AgentWorkerChain addWorker(String agentName) throws ClassNotFoundException;

    /**
     * 添加多个agent节点名称
     * @param agentNames
     * @return
     */
    AgentWorkerChain addWorkers(String  ... agentNames) throws ClassNotFoundException;
}
