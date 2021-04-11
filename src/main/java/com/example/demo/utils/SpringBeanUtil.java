package com.example.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @author lp225484
 */
@Component
public class SpringBeanUtil implements BeanFactoryAware {
    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringBeanUtil.beanFactory = beanFactory;
    }

    public static <T> T getBean(String beanName) {
        if (beanFactory != null) {
            return (T) beanFactory.getBean(beanName);
        }
        return null;
    }

    public static <T> T getBean(Class<T> requiredType) {
        if (beanFactory != null) {
            return (T) beanFactory.getBean(requiredType);
        }
        return null;
    }
}
