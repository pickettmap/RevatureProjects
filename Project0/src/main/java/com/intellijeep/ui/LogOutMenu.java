package com.intellijeep.ui;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.UserDao;
import com.intellijeep.model.User;
import com.intellijeep.services.UserService;
import sun.rmi.runtime.Log;

import java.util.Scanner;

public class LogOutMenu extends AbstractMenu{

    private UserService us;

    public LogOutMenu(User u){
        this.u = u;
        this.us = new UserService((UserDao) DaoFactory.createDao(User.class));
    }

    @Override
    public void showMenu(Scanner scan) {
        System.out.println("Now logging out");
        nextMenu = menuFactory.getControlFlowMenu("welcome",null);
        nextMenu.showMenu(scan);
    }
}
