package com.intellijeep.ui.customer;

import com.intellijeep.model.Car;
import com.intellijeep.model.Offer;
import com.intellijeep.model.Payment;
import com.intellijeep.model.User;
import com.intellijeep.services.CarService;
import com.intellijeep.services.CustomerService;
import com.intellijeep.services.PaymentService;
import com.intellijeep.services.SystemService;
import com.intellijeep.ui.AbstractMenu;

import java.util.Scanner;

public class PaymentMenu extends AbstractMenu {

    private CustomerService customerService;
    private CarService carService;
    private PaymentService paymentService;
    private SystemService systemService;
    private Car c;
    private Offer o;
    private Payment p;

    public PaymentMenu(int carID, User u) {
        this.customerService = new CustomerService();
        this.carService = new CarService();
        this.paymentService = new PaymentService();
        this.systemService = new SystemService();
        this.c = carService.getCarByID(carID);
        this.u = u;
        this.o = customerService.viewCurrentOffer(carID,u.getUserID());
        this.p = paymentService.getPayment(c, u);
    }

    @Override
    public void showMenu(Scanner scan) {
        do{
            if(paymentService.noPaymentPlanSet(c.getCarID(),u.getUserID())) {
                System.out.println("You have taken a loan of: $" + o.getAmount());
                System.out.println("Would you like to select a 1, 2, or 5 year payment plan?");
                Integer response = Integer.parseInt(scan.nextLine());
                switch (response) {
                    case 1:
                        p.setPaymentTerm(12);
                        p.setMonthlyAmount(systemService.calculateMonthlyPayment(p.getLoanAmount(),12));
                        p.setPaymentRemaining(12);
                        paymentService.updatePaymentInfo(p);
                        break;
                    case 2:
                        p.setPaymentTerm(24);
                        p.setMonthlyAmount(systemService.calculateMonthlyPayment(p.getLoanAmount(),24));
                        p.setPaymentRemaining(24);
                        paymentService.updatePaymentInfo(p);
                        break;
                    case 5:
                        p.setPaymentTerm(60);
                        p.setMonthlyAmount(systemService.calculateMonthlyPayment(p.getLoanAmount(),60));
                        p.setPaymentRemaining(60);
                        paymentService.updatePaymentInfo(p);
                        break;
                    default:
                        System.out.println("Not a valid payment plan");
                }
                System.out.println("Payment plan selected successfully");
                System.out.println("Returning to garage");
                nextMenu = menuFactory.getControlFlowMenu("garage",u);
            }
            else {
                System.out.println("Payment Info for Car: " + c.getCarID());
                System.out.println("Would you like to...\n" +
                        "1: View remaining payments for this car \n" +
                        "2: Make payment on this car \n" +
                        "3: Go Back");
                String response = scan.nextLine();


                switch (Integer.parseInt(response)) {
                    case 1:
                        System.out.println(paymentService.getRemainingPayment(c, u));
                        break;
                    case 2:
                        Payment newPayment = paymentService.makePayment(c, u);
                        System.out.println("You have successfully made a payment");
                        System.out.println("You have $" + newPayment.getLoanBalance()+ " left to pay within " +
                                newPayment.getPaymentRemaining() + " months");
                        break;
                    default:
                        nextMenu = menuFactory.getControlFlowMenu("garage", u);
                }
                nextMenu = menuFactory.getControlFlowMenu("garage", u);
            }
        }
        while(nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
