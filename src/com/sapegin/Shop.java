package com.sapegin;

import com.sapegin.view.View;

public class Shop {

    private String name;
    private final View view;
    private final DataBaseManager dataBaseManager = new DataBaseHardCode();

    public Shop(String name, View view) {
        this.name = name;
        this.view = view;
    }

    public DataBaseManager getDataBaseManager() { //нужно ли как-то его копию делать? Могут ли его заменить? Сменить указатель на другую бд?
        return dataBaseManager;
    }

    public void logIn() {
        view.showWelcome();
    }
}
