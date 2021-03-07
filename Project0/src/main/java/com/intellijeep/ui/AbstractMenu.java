package com.intellijeep.ui;

import java.util.Scanner;

public abstract class AbstractMenu {

    public abstract void showMenu(Scanner scan);

    protected MenuFactory menuFactory = new MenuFactory();

    protected AbstractMenu nextMenu;
}
