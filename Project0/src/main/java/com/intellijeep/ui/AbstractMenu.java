package com.intellijeep.ui;

import com.intellijeep.model.User;

import java.util.Scanner;

public abstract class AbstractMenu {

    public abstract void showMenu(Scanner scan);

    protected MenuFactory menuFactory = new MenuFactory();

    protected AbstractMenu nextMenu;
    protected User u;
}
