package com.sapegin;

import com.sapegin.view.View;

public class Shop {

    private String name;
    private final View view;
    private final DataBaseManager dataBaseManager;

    public Shop(String name, View view, DataBaseManager dataBaseManager) {
        this.name = name;
        this.view = view;
        this.dataBaseManager = dataBaseManager;
        view.setShop(this);
    }

    public DataBaseManager getDataBaseManager() { //нужно ли как-то его копию делать? Могут ли его заменить? Сменить указатель на другую бд?
        return dataBaseManager;
    }

    public void logIn() {
        view.showWelcome();
    }
}
