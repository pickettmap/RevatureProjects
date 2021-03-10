package com.intellijeep.ui;

import com.intellijeep.model.*;
import com.intellijeep.model.User;
import com.intellijeep.services.UserService;

import java.util.Scanner;

public class SignUpMenu extends AbstractMenu{

    private final UserService us;

    private final User newUser; //id, username, password, accountType

    public SignUpMenu() {
        this.us = new UserService();
        this.newUser = new User();
    }

    Boolean validUser = false;
    //TODO: Implement input validation (Utilize IVU and SignUpService)
    @Override
    public void showMenu(Scanner scan) {
        do {
            System.out.println("Please provide Username");
            newUser.setUsername(scan.nextLine());
        }
        while(us.doesUsernameExist(newUser.getUsername()));

        System.out.println("Please provide Password");
        newUser.setPassword(scan.nextLine());

        newUser.setAccountType(AccountType.USER);
        newUser.setUserID(us.makeUser(newUser));

        System.out.println("Successfully Created " + newUser.getUsername());
        System.out.println("Now redirecting you");

        nextMenu = menuFactory.getUserAccountTypeMenu(us.findUserByUserID(newUser.getUserID()));
        nextMenu.showMenu(scan);
    }
}
