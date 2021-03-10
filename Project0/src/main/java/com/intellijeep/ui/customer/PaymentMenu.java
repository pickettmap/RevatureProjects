package com.intellijeep.ui.customer;

import com.intellijeep.model.Car;
import com.intellijeep.model.User;
import com.intellijeep.services.CarService;
import com.intellijeep.services.CustomerService;
import com.intellijeep.services.PaymentService;
import com.intellijeep.ui.AbstractMenu;

import java.util.Scanner;

public class PaymentMenu extends AbstractMenu {

    private CustomerService customerService;
    private CarService carService;
    private PaymentService paymentService;
    private Car c;

    public PaymentMenu(int carID, User u) {
        this.customerService = new CustomerService();
        this.carService = new CarService();
        this.paymentService = new PaymentService();
        this.c = carService.getCarByID(carID);
        this.u = u;
    }

    @Override
    public void showMenu(Scanner scan) {
        do{
            System.out.println("Payment Info for Car: " + c.getCarSaleInfo().getCarID());
            System.out.println("Would you like to..." +
                    "1: View remaining payments for this car \n" +
                    "2: Make payment on this car \n" +
                    "3: Go Back");
            String response = scan.nextLine();

            switch (Integer.parseInt(response)) {
                case 1:
                    System.out.println(paymentService.getRemainingPayment(c,u));
                    break;
                case 2:
                    //TODO: Need to be able to buy car
                case 3:
                    nextMenu = menuFactory.getControlFlowMenu("garage",u);
            }
        }
        while(nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
