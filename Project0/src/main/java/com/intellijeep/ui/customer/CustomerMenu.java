package com.intellijeep.ui.customer;

import com.intellijeep.model.User;
import com.intellijeep.services.CustomerService;
import com.intellijeep.ui.AbstractMenu;

import java.util.Scanner;

public class CustomerMenu extends AbstractMenu {

    private CustomerService cs;

    public CustomerMenu(User u) {
        cs = new CustomerService();
        this.u = u;
    }

    @Override
    public void showMenu(Scanner scan) {
        System.out.println("Welcome, " + u.getUsername() + "!");
        String response;
        do {
            if(cs.isCarOwner(u)) {
                System.out.println("Would you like to...\n" +
                    "1: View cars on lot\n" +
                    "2: Logout");

                response = scan.nextLine();
                switch (response){
                    case "1":
                        nextMenu = menuFactory.getControlFlowMenu("dealership",u);
                        break;
                    case "2":
                        nextMenu = menuFactory.getControlFlowMenu("logout", u);
                        break;
                }
            }
            else{
                System.out.println("Would you like to...\n" +
                    "1: View cars on lot\n" +
                    "2: View cars you own\n" +
                    "3: Logout");
                response = scan.nextLine();
                switch (response) {
                    case "1":
                        nextMenu = menuFactory.getControlFlowMenu("dealership",u);
                        break;
                    case "2":
                        nextMenu = menuFactory.getControlFlowMenu("garage",u);
                        break;
                    case "3":
                        nextMenu = menuFactory.getControlFlowMenu("logout",u);
                        break;
                }
            }
        } while(nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
