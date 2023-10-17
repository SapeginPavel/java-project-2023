package com.sapegin.dependencies;

import com.sapegin.DataBaseHardCode;
import com.sapegin.DataBaseManager;
import com.sapegin.view.ConsoleView;
import com.sapegin.view.View;

public class ConsoleConfiguration implements Configuration {

    public ConsoleConfiguration() {
        implementations.put(View.class, ConsoleView.class);
        implementations.put(DataBaseManager.class, DataBaseHardCode.class);
    }

    @Override
    public <T> Class getImplementationOfInterface(Class<T> clazz) { //public <T> Class<? extends T> getImplementationOfInterface(Class<T> clazz) {
        return implementations.computeIfAbsent(clazz,  cl -> clazz);
    }
}
