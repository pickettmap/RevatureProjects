package com.intellijeep.ui;

import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.Car;
import com.intellijeep.model.User;
import com.intellijeep.services.CustomerService;

import java.util.Scanner;

public class CustomerMenu extends AbstractMenu{

    private CustomerService cs;

    public CustomerMenu(User u) {
        cs = new CustomerService((CarDao) DaoFactory.createDao(Car.class));
        this.u = u;
    }

    @Override
    public void showMenu(Scanner scan) {
        System.out.println("Welcome, " + u.getAccountData().getUsername() + "!");

    }
}
