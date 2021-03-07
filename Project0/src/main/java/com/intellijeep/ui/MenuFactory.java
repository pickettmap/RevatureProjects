package com.intellijeep.ui;

import com.intellijeep.model.AccountType;

public class MenuFactory {
    public AbstractMenu getUserAccountTypeMenu(AccountType accountType) {
        switch (accountType) {
            case UNREGISTERED:
                return new RegistrationMenu();
            case USER:
                return new UserMenu();
            case CUSTOMER:
                return new CustomerMenu();
            case EMPLOYEE:
                return new EmployeeMenu();
            case ADMIN:
                return new AdminMenu();
            default:
                return null;
        }
    }

    public AbstractMenu getControlFlowMenu(String menuType) {
        switch (menuType.toLowerCase()) {
            case "sign up":
                return new SignUpMenu();
            case "register":
                return new RegistrationMenu();
            case "login":
                return new LoginMenu();
            case "logout":
                return new LogOutMenu();
            case "welcome":
                return new WelcomeMenu();
            default:
                return null;
        }
    }
}
