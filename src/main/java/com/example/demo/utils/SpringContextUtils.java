package com.example.demo.utils;

import com.example.demo.config.hotdeploy.HotDeploy;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author : lp225484
 * @date : 2021/04/11
 * @description:
 */
@Slf4j
public class SpringContextUtils {
    private static Properties applicationContext;

    Logger logger = LoggerFactory.getLogger(SpringContextUtils.class);


    static {
        // 过滤器早于spring @Value注解，用PropertiesLoaderUtils读取配置
        try {
            applicationContext = PropertiesLoaderUtils.loadAllProperties("application.properties");
            Boolean agentCustomLoader = Boolean.parseBoolean(applicationContext.getProperty("app.agentCustomLoader"));
            HotDeploy.agentWorkerHotDeploy = agentCustomLoader;
            if (agentCustomLoader) {
                System.out.println(" current agent open custom class loader");
                System.out.println("当前开启agent动态加载，可以直接替换agent包下的*Agent.class热部署");
            } else {
                System.out.println(" current agent class spring loader");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getApplicationConfig(String key) {
        return applicationContext.getProperty(key);
    }

}
