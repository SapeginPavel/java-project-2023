package com.sapegin;

import com.sapegin.dependencies.ApplicationContext;
import com.sapegin.dependencies.BeanFactory;
import com.sapegin.dependencies.Utils;
import com.sapegin.view.ConsoleView;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        Shop myShop = BeanFactory.getInstance().getBean(Shop.class);
//        myShop.logIn();
        Main test = new Main();
        ApplicationContext applicationContext = test.run();
        Shop shop = applicationContext.getBean(Shop.class);
        shop.logIn();
    }

    public ApplicationContext run() {
//        List<Class<?>> allClassesInPackage = Utils.find(this.getClass().getPackage().getName());
        ApplicationContext applicationContext = new ApplicationContext();
        BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);
        return applicationContext;
    }
}
