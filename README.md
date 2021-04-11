# simple_task_dispatch
#一个简单的任务调度系统
使用：
    通过配置task-config.json实现调度任务的配置

逻辑代码参考Test1Agent.java

agent 包内的节点类，支持动态装载，服务不需要发布，直接替换class完成装载

开启agent热部署：
app.agentCustomLoader = true

agent支持热部署添加注解：@ExcludeScan

服务启动后地址：
http://localhost:8080/swagger-ui.html

