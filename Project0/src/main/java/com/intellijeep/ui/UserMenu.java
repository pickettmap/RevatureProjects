package com.intellijeep.ui;

import com.intellijeep.model.User;

import java.util.Scanner;

public class UserMenu extends AbstractMenu {

    private User u;

    public UserMenu (User u){
        this.u = u;
    }

    @Override
    public void showMenu(Scanner scan) {
        do {
            System.out.println("Welcome, " + u.getUsername() + "!");
            System.out.println("Would you like to Register or Logout?");
            String response = scan.nextLine();
            nextMenu = menuFactory.getControlFlowMenu(response,u);
        } while(nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
