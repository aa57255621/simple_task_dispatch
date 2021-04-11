package com.example.demo;

import com.example.demo.utils.JedisCacheClient;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author lp225484
 */
@Component
public class AsyncTaskPool {
    private Logger logger = LoggerFactory.getLogger(AsyncTaskPool.class);

    /**
     * 任务状态
     */
    public static enum TaskStatus {
        SUCCESS, FAILED, PENDING, INIT;

        public boolean isFinished() {
            return this == SUCCESS || this == FAILED;
        }
    }

    public static class Task {
        @Setter
        private String taskId;

        private Runnable runnable;

        @Getter
        private TaskStatus status = TaskStatus.INIT;

        @Getter
        private Date createTime = new Date();


    }

    private int poolSize = 8;
    private BlockingDeque<Task> taskQueue = new LinkedBlockingDeque<>();

    public AsyncTaskPool() {
        init();
    }

    public AsyncTaskPool(int poolSize) {
        this.poolSize = poolSize;
        init();
    }

    public Task submit(String taskId, Runnable runnable) {
        Task task = new Task();
        task.runnable = runnable;
        task.taskId = taskId;
        taskQueue.add(task);
        return task;
    }

    public void run() {
        while (true) {
            Task task;
            try {
                task = taskQueue.take();
            } catch (InterruptedException e) {
                break;
            }
            try {
                task.status = TaskStatus.PENDING;
                task.runnable.run();
                task.status = TaskStatus.SUCCESS;
            } catch (Exception e) {
                task.status = TaskStatus.FAILED;
                logger.error(e.getMessage());
            } finally {
                JedisCacheClient.expire(task.taskId, task.status.name(), 1);
            }
        }
    }

    public void init() {
        for (int i = 0; i < poolSize; i++) {
            Thread thread = new Thread(this::run);
            thread.setDaemon(true);
            thread.start();
        }
    }
}
