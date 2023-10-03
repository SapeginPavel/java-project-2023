package com.sapegin.structers;

public class Department {
    String name;
    OpeningHours openingHours;
//    LinkedListWithHavingName<Product>

    public Department(String name, OpeningHours openingHours) {
        this.name = name;
        this.openingHours = openingHours;
    }

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
}
