package com.example.demo.commons.agetworker;

import com.example.demo.commons.dto.CustomProperties;
import com.example.demo.config.hotdeploy.ExcludeScan;
import com.example.demo.config.hotdeploy.HotDeploy;
import com.example.demo.config.loader.CustomClassLoader;
import com.example.demo.utils.SpringBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
@ExcludeScan
public class AgentWorkerChainImpl implements AgentWorkerChain {
    Logger logger = LoggerFactory.getLogger(AgentWorkerChainImpl.class);
    private List<Class<?>> workers = new ArrayList<>();

    @Override
    public void run(CustomProperties customProperties) {
        for (Class<?> worker : workers) {
            Object agent = null;
            try {
                Method execute = worker.getMethod("execute", CustomProperties.class);
                execute.invoke(worker.newInstance(), new Object[]{customProperties});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public AgentWorkerChain addWorker(String agentName) throws ClassNotFoundException {
        if (HotDeploy.agentWorkerHotDeploy) {
            workers.add(CustomClassLoader.getCustomClassLoader().findClass("com.example.demo.agent." + agentName));
        } else {
            workers.add(SpringBeanUtil.getBean(agentName.replaceFirst(agentName.substring(0, 1), agentName.substring(0, 1).toLowerCase())).getClass());
        }
        return this;
    }


    @Override
    public AgentWorkerChain addWorkers(String... agentNames) throws ClassNotFoundException {
        for (String agentName : agentNames) {
            if (HotDeploy.agentWorkerHotDeploy) {
                workers.add(CustomClassLoader.getCustomClassLoader().findClass("com.example.demo.agent." + agentName));
            } else {
                workers.add(SpringBeanUtil.getBean(agentName.replaceFirst(agentName.substring(0, 1), agentName.substring(0, 1).toLowerCase())).getClass());
            }
        }
        return this;
    }
}
