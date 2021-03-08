package com.intellijeep.ui;

import java.util.Locale;
import java.util.Scanner;

public class WelcomeMenu extends AbstractMenu{

    @Override
    public void showMenu(Scanner scan) {
        do {
            System.out.println(" ===== Welcome to IntelliJeep =====");
            System.out.println(" ===== Beep Beep Jeep =====");

            System.out.println("Would you like to Sign Up, Login, or Quit?");
            String response = scan.nextLine().toLowerCase();
            if(response.equals("quit")){
                System.exit(0);
            }
            nextMenu = menuFactory.getControlFlowMenu(response, null);
        } while (nextMenu == null);

        nextMenu.showMenu(scan);
    }
}
