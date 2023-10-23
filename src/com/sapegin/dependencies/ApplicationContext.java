package com.sapegin.dependencies;

import com.sapegin.dependencies.annotation.Singleton;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private BeanFactory beanFactory;
    private final Map<Class<?>, Object> beans = new ConcurrentHashMap<>();

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public <T> T getBean(Class<T> clazz) throws Exception {
        if (beans.containsKey(clazz) && clazz.isAnnotationPresent(Singleton.class)) {
            return (T) beans.get(clazz);
        }

        T bean = beanFactory.getBean(clazz);

        if (clazz.isAnnotationPresent(Singleton.class)) {
            beans.put(clazz, bean);
        }
        return bean;
    }
}
