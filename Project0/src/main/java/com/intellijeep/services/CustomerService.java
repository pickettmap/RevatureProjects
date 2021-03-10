package com.intellijeep.services;

import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.OfferDao;
import com.intellijeep.db.PaymentDao;
import com.intellijeep.model.*;
import com.intellijeep.util.IntelliJeepArrayList;

//X As a customer, I can view the cars on the lot.
//X As a customer, I can make an offer for a car.
//X As a customer, I can view the cars that I own.
//X As a customer, I can view my remaining payments for a car.
//X As a customer, I can make payments for a car

public class CustomerService {

    private CarDao carDao;
    private PaymentDao paymentDao;
    private OfferDao offerDao;

    public CustomerService() {
        this.paymentDao = (PaymentDao) DaoFactory.createDao(Payment.class);
        this.carDao = (CarDao) DaoFactory.createDao(Car.class);
        this.offerDao = (OfferDao) DaoFactory.createDao(Offer.class);
    }

    public Boolean isCarOwner(User u){
        if(carDao.getOwnedCars(u.getAccountData().getUserID()) != null) {
            return true;
        }
        return false;
    }

    public void viewOwnedCars(User u) {
        IntelliJeepArrayList <Car> c = carDao.getOwnedCars(u.getAccountData().getUserID());
        System.out.println(c);
    }

    public void viewRemainingPayment(User u, Car c) {
        System.out.println();
    }

    public int makeOffer(Offer o) {
        return offerDao.save(o);
    }

}
