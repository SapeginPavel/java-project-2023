package com.sapegin.database;

import com.sapegin.structures.Department;
import com.sapegin.structures.LinkedListWithHavingName;
import com.sapegin.structures.OpeningHours;
import com.sapegin.structures.Product;

public interface DataBaseManager {

    /** product: */

    LinkedListWithHavingName<Product> getProducts();

    LinkedListWithHavingName<Product> getProductsForDepartment(Department department);

    Product getProductByID(int ID);

    boolean addNewProduct(Department department, String name, double price);

    boolean setNewNameForProduct(Product product, String newName);

    boolean setNewPriceForProduct(Product product, double newPrice);

    boolean deleteProduct(int productID, int departmentID);

    /** department: */

    LinkedListWithHavingName<Department> getDepartments();

    Department getDepartmentByID(int ID);

    boolean addNewDepartment(String name, OpeningHours openingHours);

    boolean setNewNameForDepartment(Department department, String newName);

    boolean setNewOpeningHoursDepartment(Department department, OpeningHours openingHours);

    boolean deleteDepartment(int ID);
}
