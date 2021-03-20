package com.example.demo.commons.agetworker;

import com.example.demo.commons.dto.CustomProperties;

import java.util.ArrayList;
import java.util.List;

public class AgentWorkerChainImpl implements  AgentWorkerChain{
    private List<AgentWorker> workers = new ArrayList<>();
    @Override
    public void run(CustomProperties customProperties) {
        for (AgentWorker worker: workers) {
            worker.execute(customProperties);
        }
    }

    @Override
    public AgentWorkerChain addWorker(AgentWorker agentWorker) {
       /* if(workers.stream().anyMatch(w->w.name().equals(agentWorker.name()))){
            throw new IllegalArgumentException("duplicate worker add to the chain");
        }*/
        workers.add(agentWorker);
        return this;
    }

    @Override
    public AgentWorkerChain addWorkers(AgentWorker... agentWorkers) {
        for (AgentWorker worker: agentWorkers) {
            workers.add(worker);
        }
        return this;
    }
}
