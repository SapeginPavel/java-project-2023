package com.sapegin.dependencies;

import com.sapegin.DataBaseHardCode;
import com.sapegin.DataBaseManager;
import com.sapegin.Main;
import com.sapegin.dependencies.annotation.Inject;
import com.sapegin.view.ConsoleView;
import com.sapegin.view.View;

import java.util.List;

public class DefaultConfiguration implements Configuration {

    public DefaultConfiguration() {
        List<Class<?>> allClassesInPackage = Utils.find(defaultPackage.getName());
        System.out.println(allClassesInPackage);

        for (Class<?> c : allClassesInPackage) {
            if (c.isAnnotationPresent(Inject.class)) {

            }
        }
//        defaultPackage.get

        implementations.put(View.class, ConsoleView.class);
        implementations.put(DataBaseManager.class, DataBaseHardCode.class);
    }

    @Override
    public <T> Class getImplementationOf(Class<T> clazz) { //public <T> Class<? extends T> getImplementationOfInterface(Class<T> clazz) {
        return implementations.computeIfAbsent(clazz,  cl -> clazz);
    }
}
