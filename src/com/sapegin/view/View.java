package com.sapegin.view;

import com.sapegin.NodeLocation;
import com.sapegin.Shop;

import java.util.function.Consumer;

public class View<T> {
    public String nameOfViewForTest = "parent"; //todo: удалить

    Shop shop;

    NodeLocation<T> welcome;
    NodeLocation<T> showDepartments;
    NodeLocation<T> showProducts;
    NodeLocation<T> goToDepartment;
    NodeLocation<T> editDepartment;

    public void showWelcome() {

    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
