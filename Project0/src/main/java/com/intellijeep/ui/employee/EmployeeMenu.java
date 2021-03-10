package com.intellijeep.ui.employee;

import com.intellijeep.model.User;
import com.intellijeep.services.EmployeeService;
import com.intellijeep.ui.AbstractMenu;

import java.util.Scanner;

public class EmployeeMenu extends AbstractMenu {

    private EmployeeService es;

    public EmployeeMenu(User u) {
        this.es = new EmployeeService();
        this.u = u;
    }

    @Override
    public void showMenu(Scanner scan) {
        do{
            System.out.println("Welcome, " + u.getUsername() + "!");
            System.out.println("Would you like to..." +
                    "1: Alter Lot" +
                    "2: View Pending Offers" +
                    "3: View all Payments" +
                    "4: Logout");
            String response = scan.nextLine();
            int convertedResponse = Integer.parseInt(response);


        }
        while(nextMenu == null);
    }
}
