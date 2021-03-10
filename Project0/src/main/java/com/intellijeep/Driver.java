package com.intellijeep;

import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.OfferDao;
import com.intellijeep.model.*;
import com.intellijeep.model.info.UserAccountInfo;
import com.intellijeep.ui.AbstractMenu;
import com.intellijeep.ui.MenuFactory;
import com.intellijeep.util.IntelliJeepArrayList;

import java.sql.SQLException;
import java.util.Scanner;

//XX As a user, I can login.
//X As an employee, I can add a car to the lot.
//X As a customer, I can view the cars on the lot.
//X As a customer, I can make an offer for a car.
//X As an employee, I can accept or reject an offer for a car.
//X As the system, I reject all other pending offers for a car when an offer is accepted.
//XX As a user, I can register for a customer account.
//* As an employee, I can remove a car from the lot.
//X As a customer, I can view the cars that I own.
//* As a customer, I can view my remaining payments for a car.
//* As an employee, I can view all payments.
//* As the system, I can calculate the monthly payment.

public class Driver {
    public static void main(String[] args) throws SQLException {
//        AbstractMenu menu;
//        MenuFactory mf = new MenuFactory();
//        menu = mf.getControlFlowMenu("welcome",null);
//
//        Scanner scan = new Scanner(System.in);
//        menu.showMenu(scan);

//        CarDao cd = (CarDao) DaoFactory.createDao(Car.class);
//        CarSaleInfo csi = new CarSaleInfo (-1,CarStatus.ON_LOT);
//        CarSpecInfo csi2 = new CarSpecInfo("suv",2019);
//        Car c = new Car.CarBuilder()
//                .carSaleInfo(csi)
//                .carSpecInfo(csi2)
//                .build();
//        Car c2 = new Car.CarBuilder()
//                .carSaleInfo(csi)
//                .carSpecInfo(csi2)
//                .build();
////        cd.save(c);
////        cd.save(c2);
//
//        System.out.println(cd.getByStatus(CarStatus.ON_LOT));

        UserAccountInfo accountInfo = new UserAccountInfo(3, "admin", "not needed", AccountType.ADMIN);
        User u = new User.UserBuilder().accountInfo(accountInfo).build();

        CarDao d = (CarDao) DaoFactory.createDao(Car.class);
        d.remove(6);

//        IntelliJeepArrayList<Car> cars = d.getOwnedCars(u.getAccountData().getUserID());
//        for(Car c : cars) {
//            System.out.println(c);
//        }


    }
}
