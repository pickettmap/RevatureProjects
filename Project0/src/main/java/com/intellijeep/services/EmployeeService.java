package com.intellijeep.services;

import com.intellijeep.db.CarDao;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.OfferDao;
import com.intellijeep.db.PaymentDao;
import com.intellijeep.model.*;
import com.intellijeep.util.IntelliJeepArrayList;

//X As an employee, I can add a car to the lot.
//X As an employee, I can accept or reject an offer for a car.
//X As an employee, I can remove a car from the lot.
//X As an employee, I can view all payments.


public class EmployeeService {
    private CarDao carDao;
    private OfferDao offerDao;
    private PaymentDao paymentDao;
    private SystemService ss;

    public EmployeeService() {
        this.carDao = (CarDao) DaoFactory.createDao(Car.class);
        this.offerDao = (OfferDao) DaoFactory.createDao(Offer.class);
        this.paymentDao = (PaymentDao) DaoFactory.createDao(Payment.class);
        this.ss = new SystemService();
    }

    public int addCarToLot(Car car) {
        return carDao.save(car);
    }

    public boolean removeCarFromLot(int carID) {
        if(carDao.removeCarFromLot(carID)){
            return true;
        }
        return false;
    }

    public IntelliJeepArrayList<Car> getCarsByStatus(CarStatus status) {
        return carDao.getByStatus(status);
    }

    public Offer getHighestOffer(int carID) {
        return offerDao.findHighestOffer(carDao.getByID(carID));
    }

    public Boolean isValidOffer(int OfferID) {
        if(offerDao.getByID(OfferID) != null) {
            return true;
        }
        return false;
    }

    //Need to update offers, car, make basic payment.
    public void acceptOffer(int offerID) {
        Offer o = offerDao.getByID(offerID);
        o.setOfferStatus(OfferStatus.ACCEPTED);
        offerDao.update(o);

        int carID = o.getCarID();
        Car c = carDao.getByID(carID);
        c.setCarStatus(CarStatus.CUSTOMER_OWNED);
        carDao.update(c);

        carDao.saveIntoCustomerCar(c, o.getCustomerID());

        createPayment(o);

        ss.rejectPendingOffers(o.getCarID());
    }

    public void createPayment(Offer o) {
        Payment p = new Payment(
                -1,
                o.getCustomerID(),
                o.getCarID(),
                0.0,
                o.getAmount(),
                0,
                0,
                o.getAmount()
        );
        paymentDao.save(p);
    }

    public void rejectOffer(int offerID) {
        Offer o = offerDao.getByID(offerID);
        o.setOfferStatus(OfferStatus.REJECTED);
        offerDao.update(o);
    }

    public void viewPendingCars(CarStatus carStatus) {
        IntelliJeepArrayList<Car> cars = carDao.getByStatus(CarStatus.PENDING);
        System.out.println(cars);
    }

    public void viewAllOffers(int carID) {
        IntelliJeepArrayList<Offer> offers = offerDao.getAllCarOffers(carID);
        System.out.println(offers);
    }
    
}
