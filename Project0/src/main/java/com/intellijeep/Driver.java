package com.intellijeep;

import com.intellijeep.config.ConnectionUtil;
import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.model.*;
import com.intellijeep.model.info.CarSaleInfo;
import com.intellijeep.model.info.CarSpecInfo;
import com.intellijeep.model.info.UserAccountInfo;
import com.intellijeep.services.UserService;
import com.intellijeep.ui.AbstractMenu;
import com.intellijeep.ui.MenuFactory;
import com.intellijeep.ui.WelcomeMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//X As a user, I can login.
//X As an employee, I can add a car to the lot.
//* As a customer, I can view the cars on the lot.
//* As a customer, I can make an offer for a car.
//* As an employee, I can accept or reject an offer for a car.
//* As the system, I reject all other pending offers for a car when an offer is accepted.
//* As a user, I can register for a customer account.
//* As an employee, I can remove a car from the lot.
//* As a customer, I can view the cars that I own.
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

        CarDao cd = (CarDao) DaoFactory.createDao(Car.class);
        CarSaleInfo csi = new CarSaleInfo (-1,CarStatus.ON_LOT);
        CarSpecInfo csi2 = new CarSpecInfo("suv",2019);
        Car c = new Car.CarBuilder()
                .carSaleInfo(csi)
                .carSpecInfo(csi2)
                .build();
        Car c2 = new Car.CarBuilder()
                .carSaleInfo(csi)
                .carSpecInfo(csi2)
                .build();
//        cd.save(c);
//        cd.save(c2);

        System.out.println(cd.getByStatus(CarStatus.ON_LOT));
    }
}
