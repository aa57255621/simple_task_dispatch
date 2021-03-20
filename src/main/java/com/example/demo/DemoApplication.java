package com.example.demo;

import com.example.demo.commons.dto.CustomProperties;
import com.example.demo.domain.BaseHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.demo" )
public class DemoApplication {

	/*@Autowired
	private AsyncTaskPool asyncTaskPool;*/
	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
		DemoApplication demoApplication = new DemoApplication();
		// demoApplication.test();
		//baseHandler.process(customProperties);
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
