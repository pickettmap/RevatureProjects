package com.intellijeep.ui.customer;

import com.intellijeep.model.Car;
import com.intellijeep.model.User;
import com.intellijeep.services.CarService;
import com.intellijeep.services.CustomerService;
import com.intellijeep.services.UserService;
import com.intellijeep.ui.AbstractMenu;
import com.intellijeep.util.IntelliJeepArrayList;

import java.util.Scanner;

public class DealershipLotMenu extends AbstractMenu {

    private CustomerService customerService;
    private CarService carService;
    private UserService userService;
    private IntelliJeepArrayList<Car> carList;

    public DealershipLotMenu(User u) {
        this.customerService = new CustomerService();
        this.carService = new CarService();
        this.userService = new UserService();
        this.u = u;
    }

    @Override
    public void showMenu(Scanner scan) {
        do {
            System.out.println("Enter corresponding Car ID to make an offer or B to Go Back");
            userService.viewLotCars();
            String response = scan.nextLine();
            if (response.toLowerCase().equals("b")) {
                nextMenu = menuFactory.getUserAccountTypeMenu(u);
            } else if(response.matches("[0-9]+")){
                int convertedResponse = Integer.parseInt(response);
                if (carService.doesCarExist(convertedResponse)) {
                    nextMenu = menuFactory.getCarMenus("offer",convertedResponse, u);
                }
            }
        } while (nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
