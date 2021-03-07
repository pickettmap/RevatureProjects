package com.intellijeep.ui;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.AccountType;
import com.intellijeep.model.User;
import com.intellijeep.model.UserAccountInfo;
import com.intellijeep.services.UserService;

import java.util.Scanner;

public class RegistrationMenu extends AbstractMenu{

    private UIUtility uiu = new UIUtility();
    private UserService us = new UserService(DaoFactory.createDao(User.class));

    @Override
    public void showMenu(Scanner scan) {
        System.out.println("Would you like to register for a customer account?");
        do{
            if(uiu.YesorNo(scan.nextLine())) {
                us.getCurrentUser().getAccountData().setAccountType(AccountType.CUSTOMER);
                System.out.println(us.getCurrentUser().getAccountData().getAccountType().toString());
                nextMenu = menuFactory.getUserAccountTypeMenu(AccountType.CUSTOMER);
            }
            else{
                nextMenu = menuFactory.getControlFlowMenu("logout");
            }
        }while(nextMenu == null);

        nextMenu.showMenu(scan);
    }
}
