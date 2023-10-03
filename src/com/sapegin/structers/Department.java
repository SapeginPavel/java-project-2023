package com.sapegin.structers;

public class Department implements HavingName {
    private String name;
    private OpeningHours openingHours;
    private LinkedListWithHavingName<Product> products;

    public Department(String name, OpeningHours openingHours) {
        this.name = name;
        this.openingHours = openingHours;
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

    public LinkedListWithHavingName<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setDepartmentStorage(this);
    }
}
