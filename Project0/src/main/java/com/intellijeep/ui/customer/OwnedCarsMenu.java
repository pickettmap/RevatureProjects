package com.intellijeep.ui.customer;

import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.Car;
import com.intellijeep.model.User;
import com.intellijeep.services.CustomerService;
import com.intellijeep.ui.AbstractMenu;
import com.intellijeep.util.IntelliJeepArrayList;

import java.util.Scanner;

public class OwnedCarsMenu extends AbstractMenu {
    private User u;
    private CustomerService cs;

    public OwnedCarsMenu(User u) {
        this.u = u;
        cs = new CustomerService();
    }

    @Override
    public void showMenu(Scanner scan) {
        do{
            cs.viewOwnedCars(u);
            System.out.println("Enter a Car ID to view payment information or B to Go Back");
            String response = scan.nextLine();

            if(response.toLowerCase().equals("b")) {
                nextMenu = menuFactory.getUserAccountTypeMenu(u);
            } else if(response.matches("[0-9]+")) {
                int convertedResponse = Integer.parseInt(response);
                nextMenu = menuFactory.getCarMenus("payment",convertedResponse,u);
            }
        }
        while(nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
