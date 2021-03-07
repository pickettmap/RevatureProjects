package com.intellijeep.ui;

import com.intellijeep.model.AccountType;
import com.intellijeep.model.User;

public class MenuFactory {
    public AbstractMenu getUserAccountTypeMenu(User u) {
        AccountType accountType = u.getAccountData().getAccountType();
        switch (accountType) {
            case USER:
                return new UserMenu(u);
            case CUSTOMER:
                return new CustomerMenu(u);
            case EMPLOYEE:
                return new EmployeeMenu(u);
            case ADMIN:
                return new AdminMenu(u);
            default:
                return null;
        }
    }

    public AbstractMenu getControlFlowMenu(String menuType, User u) {
        switch (menuType.toLowerCase()) {
            case "sign up":
                return new SignUpMenu();
            case "register":
                return new RegistrationMenu(u);
            case "login":
                return new LoginMenu(u);
            case "logout":
                return new LogOutMenu(u);
            case "welcome":
                return new WelcomeMenu();
            default:
                return null;
        }
    }
}
