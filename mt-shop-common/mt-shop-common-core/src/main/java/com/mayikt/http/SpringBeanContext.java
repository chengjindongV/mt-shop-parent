package com.mayikt.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanContext implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(SpringBeanContext.class);
    protected static ApplicationContext applicationContext;

    public SpringBeanContext() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext1) throws BeansException {
        applicationContext = applicationContext1;
    }

    public static Object getBean(String beanName) {
        if (applicationContext == null) {
            log.error("未初始化Spring上下文");
            return null;
        } else if (!applicationContext.containsBean(beanName)) {
            log.warn("Spring上下文中不存在要查找的对象[{}]", beanName);
            return null;
        } else {
            return applicationContext.getBean(beanName);
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        if (applicationContext == null) {
            log.error("未初始化Spring上下文");
            return null;
        } else {
            return applicationContext.getBean(clazz);
        }
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        if (applicationContext == null) {
            log.error("未初始化Spring上下文");
            return null;
        } else {
            return applicationContext.getBean(name, clazz);
        }
    }

    public String[] getBeanNamesForType(Class<?> type) {
        return applicationContext.getBeanNamesForType(type);
    }
}
