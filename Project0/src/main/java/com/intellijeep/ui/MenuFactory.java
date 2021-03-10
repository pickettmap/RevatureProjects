package com.intellijeep.ui;

import com.intellijeep.model.AccountType;
import com.intellijeep.model.User;
import com.intellijeep.ui.customer.*;
import com.intellijeep.ui.employee.AlterLotMenu;
import com.intellijeep.ui.employee.EmployeeMenu;
import com.intellijeep.ui.employee.ManageOffersMenu;
import com.intellijeep.ui.employee.ViewCustomerPaymentsMenu;

public class MenuFactory {
    public AbstractMenu getUserAccountTypeMenu(User u) {
        AccountType accountType = u.getAccountType();
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
            case "dealership":
                return new DealershipLotMenu(u);
            case "garage":
                return new OwnedCarsMenu(u);
            case "alterlot":
                return new AlterLotMenu(u);
            case "welcome":
                return new WelcomeMenu();
            default:
                return null;
        }
    }

    public AbstractMenu getCustomerCarMenus(String menuType, int carID, User u) {
        switch (menuType.toLowerCase()) {
            case "offer":
                return new OfferMenu(carID, u);
            case "payment":
                return new PaymentMenu(carID, u);
            default:
                return null;
        }
    }

    public AbstractMenu getEmployeeCarMenus(String menuType, User u) {
        switch (menuType) {
            case "1":
                return new AlterLotMenu(u);
            case "2":
                return new ManageOffersMenu(u);
            case "3":
                return new ViewCustomerPaymentsMenu(u);
            case "4":
                return new LogOutMenu(u);
            default:
                return null;

        }
    }
}
