package com.sapegin;

import com.sapegin.dependencies.annotation.Inject;
import com.sapegin.view.View;

public class Shop {

    @Inject
    private View view;
    @Inject
    private DataBaseManager dataBaseManager;

    public Shop() {
    }

    public DataBaseManager getDataBaseManager() { //нужно ли как-то его копию делать? Могут ли его заменить? Сменить указатель на другую бд?
        return dataBaseManager;
    }

    public void logIn() {
        view.setShop(this);
        System.out.println("View: " + view.nameOfViewForTest);
        view.showWelcome();
    }
}
