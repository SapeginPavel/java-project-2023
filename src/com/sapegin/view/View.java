package com.sapegin.view;

import com.sapegin.Shop;

public class View<T> {

    Shop shop;

    NodeLocation<T> welcome;
    NodeLocation<T> showDepartments;
    NodeLocation<T> showProducts;
    NodeLocation<T> goToDepartment;

    public void showWelcome() {

    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
