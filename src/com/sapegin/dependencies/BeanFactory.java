package com.sapegin.dependencies;

import com.sapegin.Main;
import com.sapegin.dependencies.annotation.Inject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class BeanFactory {

    ApplicationContext applicationContext;

    public BeanFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T bean;
        if (clazz == Main.class) {
            bean = clazz.getDeclaredConstructor().newInstance(); //todo: начинаем поиск подходящей реализации, а эту строчку удалить
        } else {
            bean = clazz.getDeclaredConstructor().newInstance();
        }

        for (Field field : Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Inject.class)).toList()) {
            field.setAccessible(true);
            Inject annotation = field.getAnnotation(Inject.class);
            field.set(bean, getBean(annotation.clazz()));
        }

        return bean;
    }
}
