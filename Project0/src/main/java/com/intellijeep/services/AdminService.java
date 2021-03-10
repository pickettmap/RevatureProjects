package com.intellijeep.services;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.OfferDao;
import com.intellijeep.model.Car;
import com.intellijeep.model.Offer;
import com.intellijeep.model.User;

//X As the system, I reject all other pending offers for a car when an offer is accepted.
//* As the system, I can calculate the monthly payment. -> loan_amount/payment_term

public class AdminService {
    private OfferDao od;

    AdminService(){
        this.od = (OfferDao) DaoFactory.createDao(Offer.class);
    }

    public boolean rejectPendingOffers(Car c) {
        return od.updateOtherOffers(c);
    }

    public int calculateMonthlyPayment(int loanAmount, int paymentTerm){
        return loanAmount/paymentTerm;
    }
}
