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

    //returns true if payment plan does not exist
    public Boolean noPaymentPlanSet(int carID, int customerID) {
        if(paymentDao.getPaymentTermByCarID(customerID,carID) == 0){
            return true;
        }
        return false;
    }

    public int getRemainingPayment(Car c, User u){
        return paymentDao.getRemainingPaymentByCarID(u.getUserID(),c.getCarID());
    }

    public Payment getPayment(Car c, User u) {
        return paymentDao.getByCarCustomer(c.getCarID(),u.getUserID());
    }

    public void updatePaymentInfo(Payment p) {
        paymentDao.update(p);
    }

    public double payLoan(Payment p) {
        if(p.getLoanAmount() < p.getMonthlyAmount()) {
            return p.getLoanAmount() + p.getLoanBalance();
        }
        else {
            return p.getLoanBalance()+p.getMonthlyAmount();
        }
    }

    public Payment makePayment(Car c, User u) {
        Payment p = paymentDao.getByCarCustomer(c.getCarID(), u.getUserID());
        p.setLoanBalance(payLoan(p));
        int remainingPayment = p.getPaymentTerm();
        remainingPayment -= 1;
        p.setPaymentRemaining(remainingPayment);

        paymentDao.updateAfterPayment(p);

        return p;

    }
}
