package com.sapegin;

import com.sapegin.structers.Department;
import com.sapegin.structers.LinkedListWithHavingName;
import com.sapegin.structers.OpeningHours;
import com.sapegin.structers.Product;

public interface DataBaseManager {

    /** product: */

    LinkedListWithHavingName<Department> getProducts();

    void addNewProduct(Department department, String name, double price);

    void setNewNameForProduct(Product product, String newName);

    void setNewPriceForProduct(Product product, double newPrice);

    void deleteProduct(Department department, Product product);

    /** department: */

    LinkedListWithHavingName<Department> getDepartments();

    void addNewDepartment(String name, OpeningHours openingHours);

    void setNewNameForDepartment(Department department, String newName);

    void setNewTimeOfWorkingDepartment(Department department, OpeningHours openingHours);

    void deleteDepartment(Department department);
}
