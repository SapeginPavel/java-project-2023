package com.sapegin;

import com.sapegin.dependencies.BeanFactory;
import com.sapegin.view.ConsoleView;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Shop myShop = BeanFactory.getInstance().getBean(Shop.class);
        myShop.logIn();
    }
}
