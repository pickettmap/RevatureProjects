package com.intellijeep.ui.customer;

import com.intellijeep.model.Offer;
import com.intellijeep.model.OfferStatus;
import com.intellijeep.model.User;
import com.intellijeep.services.CustomerService;
import com.intellijeep.ui.AbstractMenu;
import com.intellijeep.util.UIUtility;

import java.util.Scanner;

public class OfferMenu extends AbstractMenu {
    private int carID;
    private UIUtility uiu;
    private CustomerService cs;

    public OfferMenu(int carID, User u) {
        this.carID = carID;
        this.u = u;
        this.uiu = new UIUtility();
        this.cs = new CustomerService();
    }

    @Override
    public void showMenu(Scanner scan) {
        do {
            System.out.println("Would you like to place an offer on Car "+carID+"?");
            if (uiu.YesorNo(scan.nextLine())) {
                System.out.println("How much would you like to offer?");
                String response = scan.nextLine();
                int convertedResponse = Integer.parseInt(response);
                Offer o = new Offer(-1,carID,u.getAccountData().getUserID(),convertedResponse, OfferStatus.PENDING);
                o.setOfferID(cs.makeOffer(o));
                System.out.println("Successfully placed offer with offer ID: " +
                        o.getOfferID() + " and amount: $" + o.getAmount());
                nextMenu = menuFactory.getControlFlowMenu("dealership",u);
            } else {
                System.out.println("Would you like to return to the Dealership Lot?");
                if (uiu.YesorNo(scan.nextLine())) {
                    nextMenu = menuFactory.getControlFlowMenu("dealership",u);
                } else {
                    System.out.println("Returning you to Customer Menu");
                    nextMenu = menuFactory.getUserAccountTypeMenu(u);
                }
            }
        } while (nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
