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
            System.out.println("Would you like to Register or Logout?");
            nextMenu = menuFactory.getControlFlowMenu(scan.nextLine(),u);
        } while(nextMenu == null);

        nextMenu.showMenu(scan);
    }
}
