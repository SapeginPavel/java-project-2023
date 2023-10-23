package com.sapegin;

import com.sapegin.dependencies.ApplicationContext;
import com.sapegin.dependencies.BeanFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        Main session = new Main();
        ApplicationContext applicationContext = session.run();
        Shop shop = applicationContext.getBean(Shop.class);
        shop.logIn();
    }

    public ApplicationContext run() {
        ApplicationContext applicationContext = new ApplicationContext();
        BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);
        return applicationContext;
    }
}
