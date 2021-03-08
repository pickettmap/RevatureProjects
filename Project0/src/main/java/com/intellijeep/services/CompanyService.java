package com.intellijeep.services;

import com.intellijeep.model.*;
import com.intellijeep.util.IntelliJeepArrayList;

public class CompanyService {

    private static IntelliJeepArrayList<User> customerCollection;
    private static IntelliJeepArrayList<User> employeeCollection;
    private static IntelliJeepArrayList<Payment> companyPayments;

    public CompanyService(){
        customerCollection = new IntelliJeepArrayList<User>(User.class);
        employeeCollection = new IntelliJeepArrayList<User>(User.class);

    }

    public IntelliJeepArrayList<User> getCustomers(){
        return customerCollection;
    }

    public IntelliJeepArrayList<User> getEmployees(){
        return employeeCollection;
    }

    public void makeEmployee(User u) {
        if(u.getAccountData().getAccountType().equals(AccountType.EMPLOYEE)){
            employeeCollection.add(u);
        }
    }
}
