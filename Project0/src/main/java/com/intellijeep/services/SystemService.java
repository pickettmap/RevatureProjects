package com.intellijeep.services;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.OfferDao;
import com.intellijeep.model.Car;
import com.intellijeep.model.Offer;

//X As the system, I reject all other pending offers for a car when an offer is accepted.
//* As the system, I can calculate the monthly payment. -> loan_amount/payment_term

public class SystemService {
    private OfferDao od;

    public SystemService(){
        this.od = (OfferDao) DaoFactory.createDao(Offer.class);
    }

    public void rejectPendingOffers(int carID) {
        od.rejectPendingOffers(carID);
    }

    public double calculateMonthlyPayment(double loanAmount, int paymentTerm){
        return loanAmount/paymentTerm;
    }

}
