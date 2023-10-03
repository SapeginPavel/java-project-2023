package com.sapegin;

import com.sapegin.structures.Department;
import com.sapegin.structures.LinkedListWithHavingName;
import com.sapegin.structures.OpeningHours;
import com.sapegin.structures.Product;

public interface DataBaseManager {

    /** product: */

    LinkedListWithHavingName<Product> getProducts();

    boolean addNewProduct(Department department, String name, double price);

    boolean setNewNameForProduct(Product product, String newName);

    boolean setNewPriceForProduct(Product product, double newPrice);

    boolean deleteProduct(Product product);

    /** department: */

    LinkedListWithHavingName<Department> getDepartments();

    boolean addNewDepartment(String name, OpeningHours openingHours);

    boolean setNewNameForDepartment(Department department, String newName);

    boolean setNewTimeOfWorkingDepartment(Department department, OpeningHours openingHours);

    boolean deleteDepartment(Department department);
}
