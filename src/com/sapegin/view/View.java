package com.sapegin.view;

import com.sapegin.NodeLocation;
import com.sapegin.Shop;

import java.util.function.Consumer;

public class View<T> {

    Shop shop;

    NodeLocation<T> welcome;
    NodeLocation<T> showDepartments;
    NodeLocation<T> showProducts;

    public void showWelcome() {

    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
