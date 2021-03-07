package com.intellijeep.ui;

import com.intellijeep.model.User;

import java.util.Scanner;

public class CustomerMenu extends AbstractMenu{

    public CustomerMenu(User u) {
        this.u = u;
    }

    @Override
    public void showMenu(Scanner scan) {
        System.out.println("you did it!!!!!!! kyaaaaaaaa");
    }
}
