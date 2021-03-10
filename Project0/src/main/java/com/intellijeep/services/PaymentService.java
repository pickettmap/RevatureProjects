package com.intellijeep.services;

import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.PaymentDao;
import com.intellijeep.model.Car;
import com.intellijeep.model.Payment;
import com.intellijeep.model.User;

public class PaymentService {
    private PaymentDao paymentDao;

    public PaymentService() {
        this.paymentDao = (PaymentDao) DaoFactory.createDao(Payment.class);
    }

    public Payment makePayment() {
        return null;
    }

    public int getRemainingPayment(Car c, User u){
        return paymentDao.getRemainingPaymentByCarID(u.getUserID(),c.getCarID());
    }
}
