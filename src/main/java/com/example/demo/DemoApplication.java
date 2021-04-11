package com.example.demo;

import com.example.demo.commons.dto.CustomProperties;
import com.example.demo.config.hotdeploy.AgentCustomLoaderController;
import com.example.demo.domain.BaseHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author lp225484
 */
@SpringBootApplication(scanBasePackages = "com.example.demo" )
@ComponentScan(excludeFilters = {
			@ComponentScan.Filter(type = FilterType.CUSTOM,classes = {AgentCustomLoaderController.class})}
)
public class DemoApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
		DemoApplication demoApplication = new DemoApplication();
	}

	public void test() throws InterruptedException {
		CustomProperties customProperties = new CustomProperties();
		customProperties.setName("xxxx");
		customProperties.setWorkerType("worker1");
		customProperties.setId("100001");
		BaseHandler baseHandler = new BaseHandler(customProperties);
		AsyncTaskPool asyncTaskPool = new AsyncTaskPool(8);
		AsyncTaskPool.Task task = asyncTaskPool.submit(customProperties.getId(), baseHandler);
		System.out.println("任务状态:"+task.getStatus());

		Thread.sleep(1000);
		System.out.println("一秒后任务状态:"+task.getStatus());

	}

}
