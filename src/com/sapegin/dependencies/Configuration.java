package com.sapegin.dependencies;

import java.util.HashMap;
import java.util.Map;

public interface Configuration {
    Map<Class, Class> implementations = new HashMap<>();

    <T> Class<? extends T> getImplementationOf(Class<T> clazz);
}
