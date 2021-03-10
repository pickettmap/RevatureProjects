package com.intellijeep.ui;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.UserDao;
import com.intellijeep.model.AccountType;
import com.intellijeep.model.User;
import com.intellijeep.services.UserService;
import com.intellijeep.util.UIUtility;

import java.util.Scanner;

public class RegistrationMenu extends AbstractMenu{

    private UIUtility uiu;
    private UserService us;

    public RegistrationMenu(User u){
        this.uiu = new UIUtility();
        this.us = new UserService((UserDao) DaoFactory.createDao(User.class));
        this.u = u;
    }

    @Override
    public void showMenu(Scanner scan) {
        do{
            if(uiu.YesorNo(scan.nextLine())) {
                us.changeUserAccountType(AccountType.CUSTOMER,u.getAccountData().getUserID());
                nextMenu = menuFactory.getUserAccountTypeMenu(u);
            }
            else{
                nextMenu = menuFactory.getControlFlowMenu("logout", u);
            }
        }while(nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
