package com.intellijeep.model;

public class Payment {
    private int customerID;
    private int carID;
    private double monthlyAmount;
    private double loanAmount;
    private int paymentTerm;
    private int paymentRemaining;

    public Payment(int customerID, int carID, double monthlyAmount, double loanAmount, int paymentTerm, int paymentRemaining) {
        this.customerID = customerID;
        this.carID = carID;
        this.monthlyAmount = monthlyAmount;
        this.loanAmount = loanAmount;
        this.paymentTerm = paymentTerm;
        this.paymentRemaining = paymentRemaining;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public double getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(int monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(int paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public int getPaymentRemaining() {
        return paymentRemaining;
    }

    public void setPaymentRemaining(int paymentRemaining) {
        this.paymentRemaining = paymentRemaining;
    }
}
