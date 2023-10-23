package com.sapegin.dependencies;

import com.sapegin.dependencies.annotation.Inject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class BeanFactory {

    private static final BeanFactory BEAN_FACTORY = new BeanFactory();
    private final Configuration configuration;

    private BeanFactory() {
        this.configuration = new DefaultConfiguration();
    }

    public static BeanFactory getInstance() {
        return BEAN_FACTORY;
    }



    public <T> T getBean(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends T> implementationClass = configuration.getImplementationOf(clazz);
        T bean = implementationClass.getDeclaredConstructor().newInstance();

        for (Field field : Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Inject.class)).toList()) {
            field.setAccessible(true);
            field.set(bean, BEAN_FACTORY.getBean(field.getType()));
        }

        return bean;
    }
}
