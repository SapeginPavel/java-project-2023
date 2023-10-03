package com.sapegin.structers;

public class Product implements HavingName {
    private String name;
    private double price;

    private Department departmentStorage; //продукт можно добавить, но он не будет ни в одном отделе

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Department getDepartmentStorage() {
        return departmentStorage;
    }

    public void setDepartmentStorage(Department departmentStorage) {
        this.departmentStorage = departmentStorage;
    }
}
