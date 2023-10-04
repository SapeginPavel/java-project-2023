package com.sapegin.structures;

public class Department implements HavingNameAndId {
    private String name;
    private OpeningHours openingHours;
    private final int ID;

    public Department(String name, OpeningHours openingHours, int ID) {
        this.name = name;
        this.openingHours = openingHours;
        this.ID = ID;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "id = " + ID + "; " + name + " [" + openingHours + "]";
    }

    //    public LinkedListWithHavingName<Product> getProducts() {
//        return products;
//    }
//
//    public void addProduct(Product product) {
//        products.add(product);
//        product.setDepartmentStorage(this);
//    }
}
