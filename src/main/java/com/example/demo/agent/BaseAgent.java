package com.example.demo.agent;

import com.example.demo.commons.agetworker.AgentWorker;
import com.example.demo.commons.dto.CustomProperties;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.BeanNameAware;

@Slf4j
public abstract class BaseAgent implements AgentWorker, BeanNameAware  {

    String currentAgentName;

    @Override
    /**
     * 类实现BeanNameAware，spring会将beanName传给该方法
     */
    public void setBeanName(String name) {
        currentAgentName = name;
    }

    @Override
    public void execute(CustomProperties custom) {
        long start = System.currentTimeMillis();
        System.out.println(custom.getWorkerType() + ": BaseAgent execute start");
        doPreMajor(custom);
        doMajor(custom);
        doPostMajor(custom);
        System.out.println(custom.getWorkerType() + ": agent run success timeCost:"+(System.currentTimeMillis() - start) );
    }

    /**
     * 主任务执行前准备工作
     * @param customProperties
     */
    protected  void doPreMajor(CustomProperties customProperties){

    }

    /**
     * agent主任务执行
     * @param customProperties
     */
    protected  void doMajor(CustomProperties customProperties){

    }

    /**
     * agent执行完后的收尾工作
     * @param customProperties
     */
    protected void doPostMajor(CustomProperties customProperties){

    }
}
