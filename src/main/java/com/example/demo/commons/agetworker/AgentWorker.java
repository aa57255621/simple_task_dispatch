package com.example.demo.commons.agetworker;

import com.example.demo.commons.dto.CustomProperties;

public interface AgentWorker {

    /**
     * 当前agent开始执行
     * @param customProperties
     */
    void execute(CustomProperties customProperties);
}
