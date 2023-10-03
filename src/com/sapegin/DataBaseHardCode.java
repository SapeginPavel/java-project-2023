package com.sapegin;

import com.sapegin.structers.Department;
import com.sapegin.structers.LinkedListWithHavingName;
import com.sapegin.structers.OpeningHours;
import com.sapegin.structers.Product;

public class DataBaseHardCode implements DataBaseManager {

    //это базы данных, которые хранят отделы и продукты
    LinkedListWithHavingName<Department> departments = new LinkedListWithHavingName<>();
    LinkedListWithHavingName<Product> products = new LinkedListWithHavingName<>();

    public DataBaseHardCode() {

    }

    @Override
    public LinkedListWithHavingName<Department> getProducts() {
        return null;
    }

    @Override
    public void addNewProduct(Department department, String name, double price) {

    }

    @Override
    public void setNewNameForProduct(Product product, String newName) {

    }

    @Override
    public void setNewPriceForProduct(Product product, double newPrice) {

    }

    @Override
    public void deleteProduct(Department department, Product product) {

    }

    @Override
    public LinkedListWithHavingName<Department> getDepartments() {
        return null;
    }

    @Override
    public void addNewDepartment(String name, OpeningHours openingHours) {

    }

    @Override
    public void setNewNameForDepartment(Department department, String newName) {

    }

    @Override
    public void setNewTimeOfWorkingDepartment(Department department, OpeningHours openingHours) {

    }

    @Override
    public void deleteDepartment(Department department) {

    }
}
