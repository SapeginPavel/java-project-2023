package com.sapegin.dependencies;

import com.sapegin.Main;
import com.sapegin.dependencies.annotation.Inject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class BeanFactory {

    ApplicationContext applicationContext;

    public BeanFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> clazz) throws Exception {

        //ещё надо где-то проверять аннотацию @Component

        T bean;
        Class<T> cl = getImplementationOf(clazz);
        bean = cl.getDeclaredConstructor().newInstance();

        for (Field field : Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Inject.class)).toList()) {
            field.setAccessible(true);
            Inject annotation = field.getAnnotation(Inject.class);
            if (annotation.clazz() == Main.class) {
                field.set(bean, getBean(field.getType()));
            } else {
                field.set(bean, getBean(annotation.clazz()));
            }
        }

        return bean;
    }

    private <T> Class<T> getImplementationOf(Class<?> clazz) throws Exception {
        if (clazz.isInterface()) {
            List<Class<?>> allClasses = Utils.findAllClasses();
            for (Class<?> cl : allClasses) {
                if (clazz.isAssignableFrom(cl)) {
                    return (Class<T>) cl;
                }
            }
        } else {
            return (Class<T>) clazz;
        }
        throw new Exception(String.format("Does not exist implementation for Interface %s", clazz));
    }
}
