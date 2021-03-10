package com.intellijeep.ui.employee;

import com.intellijeep.model.User;
import com.intellijeep.services.CarService;
import com.intellijeep.services.EmployeeService;
import com.intellijeep.ui.AbstractMenu;

import java.util.Scanner;

public class ManageCarOfferMenu extends AbstractMenu {

    private int carID;
    private EmployeeService es;
    private CarService cs;

    public ManageCarOfferMenu(User u, int carID) {
        this.u = u;
        this.carID = carID;
        this.es = new EmployeeService();
    }


    @Override
    public void showMenu(Scanner scan) {
        do{
            es.viewAllOffers(carID);
            System.out.println("Would you like to..." +
                    "1: Accept the max offer\n" +
                    "2: Manually accept offer by offerID\n" +
                    "3: Manually reject offer by offerID\n" +
                    "4: Go Back");
            String response = scan.nextLine();
            int convertedResponse = Integer.parseInt(response);

            switch (convertedResponse) {
                case 1:
                    es.acceptOffer(es.getHighestOffer(carID).getOfferID());
                    nextMenu = menuFactory.getUserAccountTypeMenu(u);
                    break;
                case 2:
                    System.out.println("Please enter offerID");
                    int id = Integer.parseInt(scan.nextLine());
                    if(es.isValidOffer(id)) {
                        es.acceptOffer(id);
                    }
                    nextMenu = menuFactory.getUserAccountTypeMenu(u);
                    break;
                case 3:
                    System.out.println("Please enter offerID");
                    int id2 = Integer.parseInt(scan.nextLine());
                    if(es.isValidOffer(id2)) {
                        es.rejectOffer(id2);
                    }
                    nextMenu = menuFactory.getUserAccountTypeMenu(u);
                    break;
                case 4:
                    nextMenu = new ManageOffersMenu(u);
                default:
                    nextMenu = menuFactory.getUserAccountTypeMenu(u);
            }
        } while(nextMenu!=null);
    }
}
