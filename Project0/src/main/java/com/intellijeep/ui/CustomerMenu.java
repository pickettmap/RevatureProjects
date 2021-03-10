package com.intellijeep.ui;

import com.intellijeep.model.User;
import com.intellijeep.services.CustomerService;

import java.util.Scanner;

public class CustomerMenu extends AbstractMenu{

    private CustomerService cs;

    public CustomerMenu(User u) {
        cs = new CustomerService();
        this.u = u;
    }

    @Override
    public void showMenu(Scanner scan) {
        System.out.println("Welcome, " + u.getAccountData().getUsername() + "!");
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
                    case "2":
                        nextMenu = menuFactory.getControlFlowMenu("logout", u);
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
                    case "2":
                        nextMenu = menuFactory.getControlFlowMenu("garage",u);
                    case "3":
                        nextMenu = menuFactory.getControlFlowMenu("logout",u);
                }
            }
        } while(nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
