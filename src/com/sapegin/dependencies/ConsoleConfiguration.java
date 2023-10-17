package com.sapegin.dependencies;

import com.sapegin.DataBaseHardCode;
import com.sapegin.DataBaseManager;

import java.util.Map;

public class ConsoleConfiguration implements Configuration {
    @Override
    public Map<Class, Class> getInterfaceToImplementations() { //map, которая возвращает для каждого интерфейса нужную имплементацию
        return Map.of(DataBaseManager.class, DataBaseHardCode.class);
    }
}
