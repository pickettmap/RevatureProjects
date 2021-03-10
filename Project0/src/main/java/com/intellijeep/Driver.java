package com.intellijeep;

import com.intellijeep.ui.AbstractMenu;
import com.intellijeep.ui.MenuFactory;

import java.sql.SQLException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws SQLException {
        AbstractMenu menu;
        MenuFactory mf = new MenuFactory();
        menu = mf.getControlFlowMenu("welcome",null);

        Scanner scan = new Scanner(System.in);
        menu.showMenu(scan);


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
//
//        User accountInfo = new User(3, "admin", "not needed", AccountType.ADMIN);
//        User u = new User.UserBuilder().accountInfo(accountInfo).build();

//        CarDao d = (CarDao) DaoFactory.createDao(Car.class);
//        d.remove(6);

//        PaymentDao pd = (PaymentDao) DaoFactory.createDao(Payment.class);
//        Payment p = new Payment(1,2,3,45,6,7);
//        pd.save(p);

//        IntelliJeepArrayList<Car> cars = d.getOwnedCars(u.getAccountData().getUserID());
//        for(Car c : cars) {
//            System.out.println(c);
//        }


    }
}
