package com.sapegin.database;

import com.sapegin.dependencies.annotation.Component;
import com.sapegin.structures.Department;
import com.sapegin.structures.LinkedListWithHavingName;
import com.sapegin.structures.OpeningHours;
import com.sapegin.structures.Product;

import java.util.List;

@Component
public class DataBaseHardCode implements DataBaseManager {

    //это базы данных, которые хранят отделы и продукты
    private LinkedListWithHavingName<Department> departments = new LinkedListWithHavingName<>();
    private LinkedListWithHavingName<Product> products = new LinkedListWithHavingName<>();
    private int departmentID = 1;
    private int productID = 1;

    public DataBaseHardCode() {
        departments.addAll(List.of(
                new Department("Shoes", new OpeningHours(8, 0, 22, 10), departmentID++),
                new Department("Outerwear", new OpeningHours(0, 0, 0, 0), departmentID++),
                new Department("Underwear", new OpeningHours(9, 15, 20, 1), departmentID++),
                new Department("Accessories", new OpeningHours(8, 0, 22, 10), departmentID++),
                new Department("Care_Products", new OpeningHours(7, 30, 23, 5), departmentID++)));

        products.addAll(List.of(
                new Product("Sneakers TimeJump", 1500, departments.getByName("Shoes"), productID++),
                new Product("Sneakers Adidas", 7500, departments.getByName("Shoes"), productID++),
                new Product("Sneakers Nike", 4999.99, departments.getByName("Shoes"), productID++),
                new Product("Hoodie #14", 999.99, departments.getByName("Outerwear"), productID++),
                new Product("Hoodie #1", 1999.99, departments.getByName("Outerwear"), productID++),
                new Product("Hoodie #3", 700, departments.getByName("Outerwear"), productID++),
                new Product("Watch #1", 5000, departments.getByName("Accessories"), productID++),
                new Product("Watch #2", 85000, departments.getByName("Accessories"), productID++),
                new Product("Watch #3", 15000, departments.getByName("Accessories"), productID++),
                new Product("Gold chain", 10000, departments.getByName("Accessories"), productID++),
                new Product("Shoe deodorant", 300, departments.getByName("Care_Products"), productID++),
                new Product("Clothes brush", 219.99, departments.getByName("Care_Products"), productID++)
        ));
    }

    @Override
    public LinkedListWithHavingName<Product> getProducts() {
        LinkedListWithHavingName<Product> productsForGetting = new LinkedListWithHavingName<>();
        productsForGetting.addAll(products);
        return productsForGetting;
    }

    @Override
    public LinkedListWithHavingName<Product> getProductsForDepartment(Department department) {
        LinkedListWithHavingName<Product> ps = new LinkedListWithHavingName<>();
        for (Product p : products) {
            if (p.getDepartmentStorage().getID() == department.getID()) {
                ps.add(p);
            }
        }
        return ps;
    }

    @Override
    public Product getProductByID(int ID) {
        for (Product product : products) {
            if (product.getID() == ID) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean addNewProduct(Department department, String name, double price) {
        products.add(new Product(name, price, department, productID++));
        return true;
    }

    @Override
    public boolean setNewNameForProduct(Product product, String newName) {
        if (product == products.getByID(product.getID())) {
            product.setName(newName);
            return true;
        }
        return false;
    }

    @Override
    public boolean setNewPriceForProduct(Product product, double newPrice) {
        if (product == products.getByID(product.getID())) {
            product.setPrice(newPrice);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int productID, int departmentID) {
        Product p = products.getByID(productID);
        if (p.getDepartmentStorage().getID() == departmentID) {
            products.remove(p);
        }
        return true;
    }

    @Override
    public LinkedListWithHavingName<Department> getDepartments() {
        LinkedListWithHavingName<Department> departmentsForGetting = new LinkedListWithHavingName<>();
        departmentsForGetting.addAll(departments);
        return departmentsForGetting;
    }

    @Override
    public Department getDepartmentByID(int ID) {
        for (Department department : departments) {
            if (department.getID() == ID) {
                return department;
            }
        }
        return null;
    }

    @Override
    public boolean addNewDepartment(String name, OpeningHours openingHours) {
        for (Department d : departments) {
            if (d.getName().equals(name)) {
                return false; //такой отдел уже есть
            }
        }
        departments.add(new Department(name, openingHours, departmentID++));
        return true;
    }

    @Override
    public boolean setNewNameForDepartment(Department department, String newName) {
        if (department == departments.getByID(department.getID())) {
            department.setName(newName);
            return true;
        }
        return false;
    }

    @Override
    public boolean setNewOpeningHoursDepartment(Department department, OpeningHours openingHours) {
        if (department == departments.getByID(department.getID())) {
            department.setOpeningHours(openingHours);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteDepartment(int ID) {
        departments.remove(departments.getByID(ID));
        return true;
    }
}
