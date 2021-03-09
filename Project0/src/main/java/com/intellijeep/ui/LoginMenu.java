package com.intellijeep.ui;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.UserDao;
import com.intellijeep.model.User;
import com.intellijeep.services.UserService;

import java.util.Scanner;

public class LoginMenu extends AbstractMenu{

    private final UserService us;
    private int loginAttempts;

    //User is null
    public LoginMenu(User u) {
        this.us = new UserService((UserDao) DaoFactory.createDao(User.class));
        this.u = u;
        loginAttempts = 3;
    }

    @Override
    public void showMenu(Scanner scan) {
        for(int i = 0; i < loginAttempts; i++) {
            System.out.println("Enter Username: ");
            String username = scan.nextLine();
            System.out.println("Enter Password: ");
            String password = scan.nextLine();

            if(us.login(username,password)) {
                System.out.println("Welcome, " + u.getAccountData().getUsername() + "!");
                System.out.println("Now redirecting you");
                nextMenu = menuFactory.getUserAccountTypeMenu(u);
                nextMenu.showMenu(scan);

            } else {
                System.out.println("Login Failed. You have " + (loginAttempts-i) + " attempts remaining");
            }
        }
        nextMenu = menuFactory.getControlFlowMenu("welcome", null);
        nextMenu.showMenu(scan);
    }

}
