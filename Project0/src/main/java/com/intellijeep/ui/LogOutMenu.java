package com.intellijeep.ui;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.User;
import com.intellijeep.services.UserService;

import java.util.Scanner;

public class LogOutMenu extends AbstractMenu{

    private UserService us = new UserService(DaoFactory.createDao(User.class));

    @Override
    public void showMenu(Scanner scan) {
        System.out.println("Now logging out");
        us.setCurrentUser(null);
        nextMenu = menuFactory.getControlFlowMenu("welcome");
        nextMenu.showMenu(scan);
    }
}
