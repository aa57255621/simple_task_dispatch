package com.example.demo.controller;

import com.example.demo.AsyncTaskPool;
import com.example.demo.application.EmailEvent;
import com.example.demo.commons.dto.CustomProperties;
import com.example.demo.config.TextOutputGraphUtil;
import com.example.demo.domain.BaseHandler;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.support.RequestHandledEvent;

/**
 * 实现RequestHandledEvent的监听，当有http结束时触发该事件
 *
 * @author lp225484
 */
@Controller
public class TaskController implements ApplicationListener<RequestHandledEvent> {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/sendEvnet")
    @ApiOperation(value = "sendEvnet", notes = "测试监听事件")
    public String sendEvent(String event) {
        EmailEvent emailEvent = new EmailEvent(applicationContext, event);
        applicationContext.publishEvent(emailEvent);
        return "success";
    }

    @PostMapping("/addTask")
    @ApiOperation(value = "addTask", notes = "添加调度任务")
    public String addTask(CustomProperties customProperties) throws InterruptedException {
        BaseHandler baseHandler = new BaseHandler(customProperties);
        AsyncTaskPool asyncTaskPool = new AsyncTaskPool(8);
        AsyncTaskPool.Task task = asyncTaskPool.submit(customProperties.getId(), baseHandler);
        System.out.println("任务状态:" + task.getStatus());

        Thread.sleep(1000);
        System.out.println("一秒后任务状态:" + task.getStatus());
        return "success";
    }

    @GetMapping("/task")
    @ApiOperation(value = "task", notes = "task 任务开始")
    public String addTask() throws InterruptedException {
        CustomProperties customProperties = new CustomProperties();
        customProperties.setName("xxxx");
        customProperties.setWorkerType("worker1");
        customProperties.setId("100001");
        BaseHandler baseHandler = new BaseHandler(customProperties);
        AsyncTaskPool asyncTaskPool = new AsyncTaskPool(8);
        AsyncTaskPool.Task task = asyncTaskPool.submit(customProperties.getId(), baseHandler);
        System.out.println("任务状态:" + task.getStatus());

        Thread.sleep(1000);
        System.out.println("一秒后任务状态:" + task.getStatus());
        return "success";
    }

    @Override
    public void onApplicationEvent(RequestHandledEvent event) {

        System.out.println("eventServer1 监听：" + event.getDescription());
    }
}
