package com.sapegin.dependencies;

import com.sapegin.Main;

import java.util.HashMap;
import java.util.Map;

public interface Configuration {

    Package defaultPackage = Main.class.getPackage();
    Map<Class, Class> implementations = new HashMap<>();

    <T> Class<? extends T> getImplementationOf(Class<T> clazz);
}
