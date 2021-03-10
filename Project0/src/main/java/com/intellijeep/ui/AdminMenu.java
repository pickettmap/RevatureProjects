package com.intellijeep.ui;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.UserDao;
import com.intellijeep.model.AccountType;
import com.intellijeep.model.User;
import com.intellijeep.util.UIUtility;

import java.util.Scanner;

public class AdminMenu extends AbstractMenu{
    private UIUtility uiu;
    private UserDao ud;

    public AdminMenu(User u) {
        this.u = u;
        this.uiu = new UIUtility();
        //TODO: Move this into admin service
        this.ud = (UserDao) DaoFactory.createDao(User.class);
    }

    @Override
    public void showMenu(Scanner scan) {
        do{
            System.out.println("Would you like to hire a new employee?");
            String response = scan.nextLine();
            if(uiu.YesorNo(response)){
                User u = new User();
                System.out.println("Please input a username");
                u.setUsername(scan.nextLine());
                System.out.println("Please input a password");
                u.setPassword(scan.nextLine());
                u.setAccountType(AccountType.EMPLOYEE);
                ud.save(u);
            }
            nextMenu = menuFactory.getControlFlowMenu("logout", u);
        } while (nextMenu==null);
        nextMenu.showMenu(scan);
    }
}
