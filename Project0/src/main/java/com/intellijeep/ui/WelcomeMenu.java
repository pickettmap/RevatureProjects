package com.intellijeep.ui;

import java.util.Locale;
import java.util.Scanner;

public class WelcomeMenu extends AbstractMenu{

    @Override
    public void showMenu(Scanner scan) {
        System.out.println("===== Welcome to IntelliJeep =====");
        System.out.println("Beep Beep Jeep");

        System.out.println("Would you like to Sign Up or Login?");
        String response = scan.nextLine().toLowerCase();
        System.out.println(response);

        nextMenu = menuFactory.getControlFlowMenu(response);
        nextMenu.showMenu(scan);
    }
}
