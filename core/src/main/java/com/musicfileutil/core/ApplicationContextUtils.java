package com.musicfileutil.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 应用上下文工具
 *
 * @author Yi Chen
 * @date 2023-12-27 21:50
 * @since 1.0
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private static volatile ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    // 使用 synchronized 关键字确保线程安全
    public static synchronized <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static synchronized <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

}
