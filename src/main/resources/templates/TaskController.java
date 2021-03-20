/*
package com.example.demo;

import com.example.demo.commons.dto.CustomProperties;
import com.example.demo.domain.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.*;

*/
/**
 * 消息进入服务，首先进入本地队列，用线程池取出任务开始消费
 *//*

//@Component
public class TaskController {
    */
/** 缓冲队列，线程池执行任务存入该队列 **//*

    LinkedBlockingQueue msgQueue = new LinkedBlockingQueue();

    Map<String, BaseHandler> checkMap = new ConcurrentHashMap<>();

    @Autowired
    private BaseHandler baseHandler;

    */
/** 线程最小常驻线程 **//*

    private final int corePoolSize = 2;
    */
/** 线程池中最大线程 **//*

    private final int maximumPoolSize = 20;
    */
/** 当线程数量大于核心时，空闲线程在终止前等待新任务的最大时间 **//*

    private final long keepAliveTime = 300;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 50;

    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(WORK_QUEUE_SIZE), this.handler);

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            msgQueue.offer((BaseHandler)r);
        }
    };

    */
/**
     * 添加task进入队列执行
     * @param custom
     * @throws InterruptedException
     *//*

    public void acceptTask(CustomProperties custom) throws InterruptedException {
        if(!checkMap.containsKey(custom.getId())){
            BaseHandler baseHandler = new BaseHandler(custom);
            checkMap.put(custom.getId(), baseHandler);
            threadPool.execute(baseHandler);
        }
    }

    */
/**
     * 调度线程池，定时把队列的任务丢入任务线程池执行
     *//*

    final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);

    final ScheduledFuture scheduledFuture = scheduledExecutor.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            if(!msgQueue.isEmpty()){
                if(threadPool.getQueue().size() < WORK_QUEUE_SIZE){
                    BaseHandler baseHandler = (BaseHandler) msgQueue.poll();
                    threadPool.execute(baseHandler);
                }
            }
        }
    },0,1,TimeUnit.SECONDS);




}
*/
