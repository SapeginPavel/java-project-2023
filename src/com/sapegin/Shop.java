package com.sapegin;

import com.sapegin.dependencies.annotation.Component;
import com.sapegin.dependencies.annotation.Inject;
import com.sapegin.dependencies.annotation.Singleton;
import com.sapegin.view.ConsoleView;
import com.sapegin.view.View;

@Singleton
public class Shop {

    @Inject(clazz = ConsoleView.class)
    @Singleton
    private View view;
    @Inject(clazz = DataBaseHardCode.class)
    @Singleton
    private DataBaseManager dataBaseManager;

    public DataBaseManager getDataBaseManager() { //нужно ли как-то его копию делать? Могут ли его заменить? Сменить указатель на другую бд?
        return dataBaseManager;
    }

    public void logIn() {
        view.setShop(this);
        view.showWelcome();
    }
}
