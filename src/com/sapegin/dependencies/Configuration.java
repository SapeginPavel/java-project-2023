package com.sapegin.dependencies;

import java.util.Map;

public interface Configuration {
    public Map<Class, Class> getInterfaceToImplementations();
}
