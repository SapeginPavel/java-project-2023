package com.sapegin.dependencies;

import com.sapegin.dependencies.annotation.Inject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BeanFactory {

    private static final BeanFactory BEAN_FACTORY = new BeanFactory();

    private BeanFactory() {
    }

    public static BeanFactory getInstance() {
        return BEAN_FACTORY;
    }

    public <T> T getBean(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends T> gotClass = clazz;

        if (clazz.isInterface()) {
            // some logic
            // меняем интерфейс на класс:
            // gotClass = ...
        }

        T bean = clazz.getDeclaredConstructor().newInstance();

        for (Field field : Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Inject.class)).collect(Collectors.toList())) {
            field.setAccessible(true);
            field.set(bean, BEAN_FACTORY.getBean(field.getType()));
        }

        return bean;
    }
}
