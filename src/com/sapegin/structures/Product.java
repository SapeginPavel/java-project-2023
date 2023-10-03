package com.sapegin.structures;

public class Product implements HavingNameAndId {
    private String name;
    private double price;
    private Department departmentStorage;
    private final int ID; //todo: получится ли финализированную проинициализировать?

    public Product(String name, double price, Department departmentStorage, int ID) {
        this.name = name;
        this.price = price;
        this.departmentStorage = departmentStorage;
        this.ID = ID;
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

    @Override
    public int getID() {
        return ID;
    }
}
