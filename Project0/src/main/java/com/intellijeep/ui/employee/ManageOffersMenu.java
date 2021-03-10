package com.intellijeep.ui.employee;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.OfferDao;
import com.intellijeep.model.CarStatus;
import com.intellijeep.model.Offer;
import com.intellijeep.model.User;
import com.intellijeep.services.CarService;
import com.intellijeep.services.EmployeeService;
import com.intellijeep.ui.AbstractMenu;

import java.util.Scanner;

public class ManageOffersMenu extends AbstractMenu {

    private EmployeeService es;
    private CarService cs;
    public ManageOffersMenu(User u){
        this.u = u;
        this.es = new EmployeeService();
        this.cs = new CarService();
    }

    @Override
    public void showMenu(Scanner scan) {
        do{
            System.out.println("Showing cars with pending offers: ");
            es.viewPendingCars(CarStatus.PENDING);
            System.out.println("Select the car you wish to view or B to go back");
            String response = scan.nextLine();
            if(response.toLowerCase().equals("b")) {
                nextMenu = menuFactory.getUserAccountTypeMenu(u);
            }
            else if(response.matches("[0-9]+")) {
                int convertedResponse = Integer.parseInt(response);
                if(cs.doesCarExist(convertedResponse)) {
                    nextMenu = new ManageCarOfferMenu(u, convertedResponse);
                }
                nextMenu = menuFactory.getUserAccountTypeMenu(u);
            }nextMenu = menuFactory.getUserAccountTypeMenu(u);
        }
        while(nextMenu == null);
        nextMenu.showMenu(scan);

    }
}
