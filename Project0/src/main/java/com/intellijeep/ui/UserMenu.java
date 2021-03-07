package com.intellijeep.ui;

import java.util.Scanner;

public class UserMenu extends AbstractMenu {
    @Override
    public void showMenu(Scanner scan) {
        do {
            System.out.println("Would you like to Register or Log Out?");
            nextMenu = menuFactory.getControlFlowMenu(scan.nextLine());
        } while(nextMenu == null);

        nextMenu.showMenu(scan);
    }
}
