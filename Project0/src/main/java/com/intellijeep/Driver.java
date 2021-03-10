package com.intellijeep;
import com.intellijeep.ui.AbstractMenu;
import com.intellijeep.ui.MenuFactory;

import java.sql.SQLException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws SQLException {
        AbstractMenu menu;
        MenuFactory mf = new MenuFactory();
        menu = mf.getControlFlowMenu("welcome",null);

        Scanner scan = new Scanner(System.in);
        menu.showMenu(scan);
    }
}
