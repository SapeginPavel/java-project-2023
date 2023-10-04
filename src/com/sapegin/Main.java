package com.sapegin;

import com.sapegin.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Shop myShop = new Shop("My shop", new ConsoleView(), new DataBaseHardCode());
        myShop.logIn();
    }
}
