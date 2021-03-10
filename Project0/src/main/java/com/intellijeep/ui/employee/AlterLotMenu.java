package com.intellijeep.ui.employee;

import com.intellijeep.model.Car;
import com.intellijeep.model.CarStatus;
import com.intellijeep.model.User;
import com.intellijeep.model.info.CarSaleInfo;
import com.intellijeep.model.info.CarSpecInfo;
import com.intellijeep.services.CarService;
import com.intellijeep.services.EmployeeService;
import com.intellijeep.services.UserService;
import com.intellijeep.ui.AbstractMenu;

import java.util.Scanner;

public class AlterLotMenu extends AbstractMenu {

    private EmployeeService es;
    private UserService us;
    private CarService cs;
    private CarSpecInfo carSpecInfo;
    private CarSaleInfo carSaleInfo;

    public AlterLotMenu(User u) {
        this.es = new EmployeeService();
        this.us = new UserService();
        this.cs = new CarService();
        this.u = u;
        carSaleInfo = new CarSaleInfo(-1, CarStatus.OFF_LOT);
    }

    @Override
    public void showMenu(Scanner scan) {
        do{
            System.out.println("Would you like to add a car or remove a car from the lot?");
            String response = scan.nextLine().toLowerCase();

            if(response.equals("add")){
                System.out.println("What is the model?");
                carSpecInfo.setModel(scan.nextLine());

                System.out.println("What is the model year?");
                carSpecInfo.setModelYear(Integer.parseInt(scan.nextLine()));

                carSaleInfo.setCarStatus(CarStatus.ON_LOT);
                Car c = new Car.CarBuilder()
                        .carSaleInfo(carSaleInfo)
                        .carSpecInfo(carSpecInfo)
                        .build();

                c.getCarSaleInfo().setCarID(es.addCarToLot(c));
                System.out.println("Car: " + c.getCarSaleInfo().getCarID() + " has been successfully added to the lot");
                nextMenu = menuFactory.getUserAccountTypeMenu(u);
            }
            else if(response.equals("remove")){
                us.viewLotCars();
                System.out.println("Which car would you like to remove?");
                int carID = Integer.parseInt(scan.nextLine());
                if(cs.doesCarExist(carID)) {
                    if (es.removeCarFromLot(carID)) {
                        System.out.println("Car: " + carID + " has been successfully pulled off the lot");
                    }
                }
                else {
                    System.out.println("That is not a valid car id");
                    nextMenu = menuFactory.getControlFlowMenu("alter",u);
                }
                nextMenu = menuFactory.getUserAccountTypeMenu(u);
            }
        }while (nextMenu == null);
        nextMenu.showMenu(scan);
    }
}
