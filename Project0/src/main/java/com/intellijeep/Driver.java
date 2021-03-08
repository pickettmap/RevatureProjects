package com.intellijeep;

import com.intellijeep.config.ConnectionUtil;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.*;
import com.intellijeep.services.UserService;
import com.intellijeep.ui.AbstractMenu;
import com.intellijeep.ui.MenuFactory;
import com.intellijeep.ui.WelcomeMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
