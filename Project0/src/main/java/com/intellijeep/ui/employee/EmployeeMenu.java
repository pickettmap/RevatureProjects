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
            System.out.println("Would you like to...\n" +
                    "1: Alter Lot\n" +
                    "2: Manage Current Offers\n" +
                    "3: View all Payments\n" +
                    "4: Logout");

            String response = scan.nextLine();
            if (response.toLowerCase().equals("logout")) {
                nextMenu = menuFactory.getControlFlowMenu("logout",u);
            }
            nextMenu = menuFactory.getEmployeeCarMenus(response, u);

        }
        while(nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
