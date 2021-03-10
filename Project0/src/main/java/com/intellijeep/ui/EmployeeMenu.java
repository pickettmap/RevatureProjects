package com.intellijeep.ui;

import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.Car;
import com.intellijeep.model.User;
import com.intellijeep.services.CarService;
import com.intellijeep.services.EmployeeService;

import java.util.Scanner;

public class EmployeeMenu extends AbstractMenu{

    private EmployeeService es;

    public EmployeeMenu(User u) {
        this.es = new EmployeeService();
        this.u = u;
    }

    @Override
    public void showMenu(Scanner scan) {

    }
}
